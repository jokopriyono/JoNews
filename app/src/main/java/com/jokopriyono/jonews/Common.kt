package com.jokopriyono.jonews

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.text.SimpleDateFormat
import java.util.*

object Common {
    /**
     * Default false, true if you had internet connection
     */
    fun checkInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var netInfo: NetworkInfo? = null
        return if (netInfo == null) {
            netInfo = cm.activeNetworkInfo
            if (netInfo == null) return false
            when (netInfo.type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                else -> false
            }
        } else false
    }

    /**
     * Return simple string, example '31/12/1997'
     */
    fun getSimpleDate(d: Date): String {
        return try {
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(d)
        } catch (e: java.lang.Exception) {
            ""
        }
    }

    /**
     * Return Date in ISO 8601 date format.
     * for more info : http://tutorials.jenkov.com/java-internationalization/simpledateformat.html
     */
    fun stringToDateTime(s: String): Date {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault()).parse(s)
    }
}