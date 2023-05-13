package com.dwiki.newsapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.dwiki.newsapp.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val url =  bundle?.getString("url")
        binding.webNews.apply {
            webViewClient = WebViewClient()
            loadUrl(url.toString())
            settings.javaScriptEnabled = true
            settings.setSupportZoom(true)
        }
    }
}