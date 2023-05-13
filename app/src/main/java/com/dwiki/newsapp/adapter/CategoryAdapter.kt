package com.dwiki.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dwiki.newsapp.databinding.ItemCategoryBinding
import com.dwiki.newsapp.model.Category

class CategoryAdapter(private val listCategory:List<Category>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var onClick : ((Category) -> Unit)? = null

    class ViewHolder(var binding:ItemCategoryBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val category = listCategory[position]
        holder.binding.categoryName.text = category.name
        holder.binding.itemCategory.setOnClickListener {
            Toast.makeText(holder.itemView.context,category.name,Toast.LENGTH_SHORT).show()
            onClick?.invoke(category)
        }
    }

    override fun getItemCount(): Int = listCategory.size
}