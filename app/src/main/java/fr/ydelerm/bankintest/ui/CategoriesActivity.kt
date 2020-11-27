package fr.ydelerm.bankintest.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.ydelerm.bankintest.BankinApplication
import fr.ydelerm.bankintest.R
import fr.ydelerm.bankintest.viewmodel.CategoriesViewModel
import fr.ydelerm.bankintest.vo.Status
import kotlinx.android.synthetic.main.activity_categories.*

class CategoriesActivity : AppCompatActivity() {

    companion object {
        const val PARAM_SELECTED_CATEGORY_ID = "selectedCategoryId"
    }

    private var selectedCategoryId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as BankinApplication).appGraph.inject(this)
        setContentView(R.layout.activity_categories)
        if (intent.hasExtra(PARAM_SELECTED_CATEGORY_ID)) {
            selectedCategoryId = intent.getIntExtra(PARAM_SELECTED_CATEGORY_ID, 0)
        }

        val tripViewModel = ViewModelProvider(this).get(CategoriesViewModel::class.java)
        tripViewModel.selectedCategoryId = selectedCategoryId

        supportActionBar?.setDisplayHomeAsUpEnabled(tripViewModel.isDisplayHomeAsUpEnabled())

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isSaveEnabled = true

        bindUi(tripViewModel)

        tripViewModel.refreshData()
    }

    private fun bindUi(tripViewModel: CategoriesViewModel) {
        tripViewModel.getCategories().observe(this) {
            val categoriesAdapter =
                CategoriesAdapter(it ?: ArrayList(), tripViewModel.getCategoryClickListener(this))
            recyclerView.swapAdapter(categoriesAdapter, true)
        }

        tripViewModel.getRequestStatus().observe(this) {
            if (Status.ERROR == it) {
                Toast.makeText(this, R.string.error_occurred, Toast.LENGTH_SHORT).show()
            }
        }

        tripViewModel.getErrorUiVisibility().observe(this) {
            buttonRefresh.visibility = it
            textviewError.visibility = it
        }

        tripViewModel.isLoading().observe(this) {
            swipeContainer.isRefreshing = it
        }

        buttonRefresh.setOnClickListener { tripViewModel.refreshData() }
        swipeContainer.setOnRefreshListener { tripViewModel.refreshData() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}