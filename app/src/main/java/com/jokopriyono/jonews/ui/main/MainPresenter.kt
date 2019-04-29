package com.jokopriyono.jonews.ui.main

import android.content.Context
import com.google.gson.Gson
import com.jokopriyono.jonews.data.ApiRepository

class MainPresenter(
    private val mainView: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: Context
) {
    fun updateTopHeadlines() {

    }
}