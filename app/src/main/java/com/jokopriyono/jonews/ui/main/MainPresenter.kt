package com.jokopriyono.jonews.ui.main

import android.content.Context
import com.google.gson.Gson
import com.jokopriyono.jonews.Common
import com.jokopriyono.jonews.R
import com.jokopriyono.jonews.data.ApiRepository
import com.jokopriyono.jonews.data.JsonApi
import com.jokopriyono.jonews.data.response.Article
import com.jokopriyono.jonews.data.response.TopHeadlines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter(
    private val mainView: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: Context
) {
    fun getTopHidelines(category: String) {
        if (Common.checkInternet(context)) {
            mainView.showLoading()
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val data =
                        gson.fromJson(
                            apiRepository.doRequest(JsonApi.getTopHeadlines(category)).await(), TopHeadlines::class.java
                        )
                    data?.let {
                        setDefaultCategory(it, category)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    mainView.hideLoading()
                    mainView.showAlert(e.localizedMessage, category)
                }
            }
        } else mainView.showAlert(context.getString(R.string.error_connection), category)
    }

    private fun setDefaultCategory(data: TopHeadlines, category: String) {
        for (article: Article in data.articles) {
            article.category = category
        }
        mainView.showNews(data)
        mainView.hideLoading()
    }
}