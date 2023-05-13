package com.dwiki.newsapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiki.newsapp.R
import com.dwiki.newsapp.adapter.CategoryAdapter
import com.dwiki.newsapp.adapter.SourceAdapter
import com.dwiki.newsapp.databinding.ActivitySourceBinding
import com.dwiki.newsapp.viewmodel.SourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySourceBinding
    private lateinit var sourceAdapter: SourceAdapter
    private val viewModel:SourceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val catName = bundle?.getString("category")

        viewModel.callApiSource(catName!!)
        viewModel.liveDataSource.observe(this){
            it.map {
                Log.d("SourceActivity", it.name)
            }
            sourceAdapter = SourceAdapter(it)
            binding.rvSource.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = sourceAdapter
            }
            sourceAdapter.onClick = {   source ->
                val intent = Intent(this@SourceActivity,ArticleActivity::class.java)
                val sourceItem = source.id
                val bundle = Bundle()
                bundle.putString("source",sourceItem)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}