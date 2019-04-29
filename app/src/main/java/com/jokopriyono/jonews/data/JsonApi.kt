package com.jokopriyono.jonews.data

import android.net.Uri
import com.jokopriyono.jonews.BuildConfig

object JsonApi {
    private fun getBaseUrl(): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon().build().toString()
    }

    fun getTopHeadlines(category: String): String {
        return Uri.parse(getBaseUrl())
            .buildUpon()
            .appendPath("top-headlines")
            .appendQueryParameter("country", "id")
            .appendQueryParameter("category", category)
            .appendQueryParameter("apiKey", BuildConfig.API_KEY)
            .build().toString()
    }
}