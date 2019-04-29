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
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity

class SplashScreenActivity : AppCompatActivity(), SplashView {
    companion object {
        const val INTENT_DATA = "data"
        const val DEFAULT_CATEGORY = "business"
    }

    private lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter = SplashPresenter(this, ApiRepository(), Gson(), this)

        playAnimation()
        presenter.getTopHidelines(DEFAULT_CATEGORY)
    }

    override fun playAnimation() {
        animated_svg_view.postDelayed({
            animated_svg_view.start()
            loading.postDelayed({ loading.visibility = VISIBLE }, 2000)
        }, 500)
    }

    override fun t(message: String) {
        longToast(message)
        finish()
    }
    override fun showAlert(m: String) {
        alert(m) {
            isCancelable = false
            positiveButton(getString(R.string.try_again)) {
                presenter.getTopHidelines(DEFAULT_CATEGORY)
                it.dismiss()
            }
        }.show()
    }

    override fun intentToMain(data: TopHeadlines) {
        startActivity<MainActivity>(INTENT_DATA to data)
        finish()
    }
}