package com.jokopriyono.jonews.ui.splash

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.jokopriyono.jonews.R
import com.jokopriyono.jonews.data.ApiRepository
import com.jokopriyono.jonews.data.response.TopHeadlines
import com.jokopriyono.jonews.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity(), SplashView {
    companion object {
        const val INTENT_DATA = "data"
    }

    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = SplashPresenter(this, apiRepository, gson, this)

        playAnimation()
        presenter.getTopHidelines()
    }

    override fun playAnimation() {
        animated_svg_view.postDelayed({
            animated_svg_view.start()
            loading.postDelayed({ loading.visibility = VISIBLE }, 2000)
        }, 500)
    }

    override fun showAlert() {
        alert("Masalah internet") {
            positiveButton("Coba lagi") {
                presenter.getTopHidelines()
                it.dismiss()
            }
        }.show()
    }

    override fun intentToMain(data: TopHeadlines) {
        startActivity<MainActivity>(INTENT_DATA to data)
        finish()
    }
}