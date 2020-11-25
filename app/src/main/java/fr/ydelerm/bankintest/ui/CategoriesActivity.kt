package fr.ydelerm.bankintest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import fr.ydelerm.bankintest.BankinApplication
import fr.ydelerm.bankintest.R
import fr.ydelerm.bankintest.model.Category
import fr.ydelerm.bankintest.viewmodel.CategoriesViewModel
import kotlinx.android.synthetic.main.activity_categories.*

class CategoriesActivity : AppCompatActivity(){

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

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isSaveEnabled = true

        tripViewModel.getCategories().observe(this) {
            buttonRefresh.visibility = View.GONE //boolToVisibility(Status.ERROR == it.status)
            textviewError.visibility = View.GONE //boolToVisibility(Status.ERROR == it.status)
            swipeContainer.isRefreshing = false //(Status.LOADING == it.status)

            val categoriesAdapter = CategoriesAdapter(it ?: ArrayList(), tripViewModel.getCategoryClickListener(this))
            recyclerView.swapAdapter(categoriesAdapter, true)

            /*if (Status.ERROR == it.status) {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }*/
        }

        buttonRefresh.setOnClickListener { tripViewModel.refreshData() }
        swipeContainer.setOnRefreshListener { tripViewModel.refreshData() }

        tripViewModel.refreshData()
    }
}