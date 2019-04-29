package com.jokopriyono.jonews.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jokopriyono.jonews.Common
import com.jokopriyono.jonews.R
import com.jokopriyono.jonews.data.response.Article
import com.squareup.picasso.Picasso
import org.jetbrains.anko.backgroundColor

class NewsAdapter(
    var articles: List<Article>,
    var color: String,
    private val clickListener: (Article) -> Unit
) : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(articles[position], color, clickListener)
    }

}

class NewsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_news, parent, false)) {
    private var imgNews: ImageView? = null
    private var txtTitle: TextView? = null
    private var txtDate: TextView? = null
    private var txtCategory: TextView? = null
    private var cardView: CardView? = null

    init {
        imgNews = itemView.findViewById(R.id.img_news)
        txtTitle = itemView.findViewById(R.id.txt_title)
        txtDate = itemView.findViewById(R.id.txt_date)
        txtCategory = itemView.findViewById(R.id.txt_category)
        cardView = itemView.findViewById(R.id.card_view_news)
    }

    fun bind(article: Article, color: String, clickListener: (Article) -> Unit) {
        txtCategory?.backgroundColor = Color.parseColor(color)
        imgNews?.let { Picasso.get().load(article.urlToImage).centerCrop().fit().into(it) }
        txtCategory?.text = article.category
        txtDate?.text = Common.getSimpleDate(Common.stringToDateTime(article.publishedAt))
        txtTitle?.let { it.text = article.title }
        cardView?.setOnClickListener { clickListener(article) }
    }
}