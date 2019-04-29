package com.jokopriyono.jonews.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.jokopriyono.jonews.R
import com.jokopriyono.jonews.adapter.NewsAdapter
import com.jokopriyono.jonews.data.ApiRepository
import com.jokopriyono.jonews.data.response.Article
import com.jokopriyono.jonews.data.response.TopHeadlines
import com.jokopriyono.jonews.ui.detail.DetailActivity
import com.jokopriyono.jonews.ui.splash.SplashScreenActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter
    private var topHeadlines: TopHeadlines? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topHeadlines = if (savedInstanceState == null) intent.getParcelableExtra(SplashScreenActivity.INTENT_DATA)
        else savedInstanceState.getParcelable(SplashScreenActivity.INTENT_DATA)

        presenter = MainPresenter(this, ApiRepository(), Gson(), this)

        for (cat: String in resources.getStringArray(R.array.categories)) {
            tab_layout.addTab(tab_layout.newTab().setText(cat))
        }

        topHeadlines?.let {
            recycler.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = NewsAdapter(it.articles) { article: Article -> intentToDetail(article) }
            }
        }

    }

    private fun intentToDetail(article: Article) {
        startActivity<DetailActivity>(DetailActivity.INTENT_ARTICLE to article)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        topHeadlines?.let { outState.putParcelable(SplashScreenActivity.INTENT_DATA, it) }
        super.onSaveInstanceState(outState)
    }

    override fun showLoading() {
        swipe_layout.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_layout.isRefreshing = false
    }
}
