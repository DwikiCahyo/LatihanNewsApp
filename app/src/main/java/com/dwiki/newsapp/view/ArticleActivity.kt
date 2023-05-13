package com.dwiki.newsapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiki.newsapp.adapter.ArticleAdapter
import com.dwiki.newsapp.databinding.ActivityArticleBinding
import com.dwiki.newsapp.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleActivity : AppCompatActivity() {
    private lateinit var binding:ActivityArticleBinding
    private lateinit var articleAdapter: ArticleAdapter
    private val viewModel:ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val sourceName = bundle?.getString("source")

//        binding.tvTest.text = sourceName
        viewModel.callApiArticle(sourceName!!)
        viewModel.liveDataArticle.observe(this){

            it.map {
                Log.d("ArticleActivity",it.title)
            }
            articleAdapter = ArticleAdapter(it)
            binding.rvArticle.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = articleAdapter
            }
            articleAdapter.onClick = {
                val intent = Intent(this@ArticleActivity, DetailArticleActivity::class.java)
                val bundle = Bundle()
                bundle.putString("url",it.url)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

    }
}