package com.jokopriyono.jonews.ui

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.jokopriyono.jonews.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        animated_svg_view.postDelayed({
            animated_svg_view.start()
            loading.postDelayed({ loading.visibility = VISIBLE }, 2000)
        }, 500)
    }
}