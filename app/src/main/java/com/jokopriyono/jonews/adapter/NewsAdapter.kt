package com.jokopriyono.jonews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jokopriyono.jonews.Common
import com.jokopriyono.jonews.R
import com.jokopriyono.jonews.data.response.Article
import com.squareup.picasso.Picasso

class NewsAdapter(private val articles: List<Article>) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position])
    }

}

class NewsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_news, parent, false)) {
    private var imgNews: ImageView? = null
    private var txtTitle: TextView? = null
    private var txtDate: TextView? = null

    init {
        imgNews = itemView.findViewById(R.id.img_news)
        txtTitle = itemView.findViewById(R.id.txt_title)
        txtDate = itemView.findViewById(R.id.txt_date)
    }

    fun bind(article: Article) {
        imgNews?.let { Picasso.get().load(article.urlToImage).centerCrop().fit().into(it) }
        txtDate?.text =
            Common.getSimpleDate(Common.stringToDateTime(article.publishedAt))
        txtTitle?.let { it.text = article.title }
    }
}