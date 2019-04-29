package com.jokopriyono.jonews.ui.splash

import com.jokopriyono.jonews.data.response.TopHeadlines

interface SplashView {
    fun playAnimation()
    fun showAlert(m: String)
    fun intentToMain(data: TopHeadlines)
    fun t(message: String)
}