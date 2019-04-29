package com.jokopriyono.jonews.ui.main

import com.jokopriyono.jonews.data.response.TopHeadlines

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showAlert(m: String, category: String)
    fun showNews(topHeadlines: TopHeadlines)
}