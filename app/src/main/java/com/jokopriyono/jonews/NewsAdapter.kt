package com.jokopriyono.jonews

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.imageResource

class NewsAdapter : RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val pos = position+1
        for (i in 0..10 step 4) if (i==position || i==pos) holder.bind(true)
    }

}

class NewsViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_news, parent, false)) {
    private var imgNews: ImageView? = null

    init {
        imgNews = itemView.findViewById(R.id.img_news)
    }

    fun bind(b: Boolean) {
        if (b) imgNews?.imageResource = R.drawable.the
    }
}