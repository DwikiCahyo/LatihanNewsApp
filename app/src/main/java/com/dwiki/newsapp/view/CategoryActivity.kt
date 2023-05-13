package com.dwiki.newsapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiki.newsapp.R
import com.dwiki.newsapp.adapter.CategoryAdapter
import com.dwiki.newsapp.databinding.ActivityCategoryBinding
import com.dwiki.newsapp.model.Category

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding:ActivityCategoryBinding
    lateinit var categoryAdapter : CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListCategory()
    }

    private fun setListCategory() {
        val listCategory = arrayListOf(
            Category("BUSINESS"),
            Category("ENTERTAINMENT"),
            Category("GENERAL"),
            Category("HEALTH"),
            Category("SCIENCE"),
            Category("SPORTS"),
            Category("TECHNOLOGY")
        )
        categoryAdapter = CategoryAdapter(listCategory)
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
            categoryAdapter.onClick = {
                val intent = Intent(this@CategoryActivity, SourceActivity::class.java)
                val categoryItem = it.name
                val bundle = Bundle()
                bundle.putString("category", categoryItem)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}