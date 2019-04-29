package com.jokopriyono.jonews.ui.splash

import android.content.Context
import com.google.gson.Gson
import com.jokopriyono.jonews.Common
import com.jokopriyono.jonews.R
import com.jokopriyono.jonews.data.ApiRepository
import com.jokopriyono.jonews.data.JsonApi
import com.jokopriyono.jonews.data.response.TopHeadlines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashPresenter(
    private val splashView: SplashView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: Context
) {
    fun getTopHidelines() {
        if (Common.checkInternet(context)) {
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    val data =
                        gson.fromJson(
                            apiRepository.doRequest(JsonApi.getTopHeadlines()).await(), TopHeadlines::class.java
                        )
                    data?.let {
                        splashView.intentToMain(it)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } else splashView.showAlert(context.getString(R.string.error_connection))
    }
}