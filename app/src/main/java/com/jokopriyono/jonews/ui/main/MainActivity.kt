package com.jokopriyono.jonews.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.jokopriyono.jonews.R
import com.jokopriyono.jonews.adapter.NewsAdapter
import com.jokopriyono.jonews.data.ApiRepository
import com.jokopriyono.jonews.data.response.Article
import com.jokopriyono.jonews.data.response.TopHeadlines
import com.jokopriyono.jonews.ui.detail.DetailActivity
import com.jokopriyono.jonews.ui.splash.SplashScreenActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainView, TabLayout.OnTabSelectedListener {
    private lateinit var presenter: MainPresenter
    private var topHeadlines: TopHeadlines? = null
    private var selectedCategory = SplashScreenActivity.DEFAULT_CATEGORY

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
            showNews(it)
        }

        tab_layout.addOnTabSelectedListener(this)
        swipe_layout.setOnRefreshListener { presenter.getTopHidelines(selectedCategory) }
    }

    override fun showNews(topHeadlines: TopHeadlines) {
        this.topHeadlines = topHeadlines
        recycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            if (recycler.adapter == null) {
                adapter = NewsAdapter(topHeadlines.articles) { article: Article -> intentToDetail(article) }
            } else {
                val adapt = adapter as NewsAdapter
                adapt.articles = topHeadlines.articles
                adapt.notifyDataSetChanged()
            }
        }
    }

    override fun onTabReselected(tab: TabLayout.Tab) {}

    override fun onTabSelected(tab: TabLayout.Tab) {
        selectedCategory = tab.text.toString().toLowerCase()
        presenter.getTopHidelines(selectedCategory)
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {}

    private fun intentToDetail(article: Article) {
        startActivity<DetailActivity>(DetailActivity.INTENT_ARTICLE to article)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        topHeadlines?.let { outState.putParcelable(SplashScreenActivity.INTENT_DATA, it) }
        super.onSaveInstanceState(outState)
    }

    override fun showAlert(m: String, category: String) {
        alert(m) {
            positiveButton(m) {
                presenter.getTopHidelines(category)
                it.dismiss()
            }
        }.show()
    }

    override fun showLoading() {
        swipe_layout.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_layout.isRefreshing = false
    }
}
