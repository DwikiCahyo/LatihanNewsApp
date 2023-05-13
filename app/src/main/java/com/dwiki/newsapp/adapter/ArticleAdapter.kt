package com.dwiki.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwiki.newsapp.databinding.ItemArticleBinding
import com.dwiki.newsapp.model.Article
import com.dwiki.newsapp.model.Source

class ArticleAdapter(var listArticle : List<Article>): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    var onClick : ((Article) -> Unit)? = null
    class ViewHolder(var binding: ItemArticleBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listArticle.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = listArticle[position]
        holder.binding.articleTitle.text = article.title
        Glide.with(holder.itemView).load(article.urlToImage).into(holder.binding.articleImage)
        holder.binding.cvArticle.setOnClickListener {
            onClick?.invoke(article)
        }

    }
}