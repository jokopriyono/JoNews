package com.jokopriyono.jonews.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.jokopriyono.jonews.NewsAdapter
import com.jokopriyono.jonews.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = NewsAdapter()
        }
        for (i in 1..3) tab_layout.addTab(tab_layout.newTab().setText(R.string.app_name))
    }
}
