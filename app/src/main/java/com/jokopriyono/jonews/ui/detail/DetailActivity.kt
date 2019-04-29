package com.jokopriyono.jonews.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jokopriyono.jonews.Common
import com.jokopriyono.jonews.R
import com.jokopriyono.jonews.data.response.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.browse

class DetailActivity : AppCompatActivity() {
    companion object {
        const val INTENT_ARTICLE = "article"
    }

    private var article: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        article = if (savedInstanceState == null) intent.getParcelableExtra(INTENT_ARTICLE)
        else savedInstanceState.getParcelable(INTENT_ARTICLE)

        article?.let {
            txt_title.text = it.title
            txt_source.text = it.source.name
            txt_author.text = it.author
            txt_date.text = Common.getSimpleDate(Common.stringToDateTime(it.publishedAt))
            Picasso.get().load(it.urlToImage).centerCrop().fit().into(img_news)
            val text = it.content + " .."
            txt_desc.text = text

            btn_read_more.setOnClickListener { _ ->
                browse(it.url, true)
            }
        }

        toolbar.setNavigationOnClickListener { finish() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        article?.let { outState.putParcelable(INTENT_ARTICLE, it) }
        super.onSaveInstanceState(outState)
    }
}