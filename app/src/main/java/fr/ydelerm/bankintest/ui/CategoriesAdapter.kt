package fr.ydelerm.bankintest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fr.ydelerm.bankintest.R
import fr.ydelerm.bankintest.databinding.CategoriesListItemBinding
import fr.ydelerm.bankintest.model.Category

class CategoriesAdapter(
    private val categories: List<Category>,
    private val categoryClickListener: CategoryClickListener?
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(
        @NonNull val binding: CategoriesListItemBinding,
        categoryClickListener: CategoryClickListener?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.category?.let {
                    categoryClickListener?.onCategoryClicked(it)
                }
            }
        }

        fun bind(item: Category) {
            binding.apply {
                category = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<CategoriesListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.categories_list_item,
            parent,
            false
        )
        return CategoryViewHolder(binding, categoryClickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}