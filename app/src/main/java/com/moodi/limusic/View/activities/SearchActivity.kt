package com.moodi.limusic.View.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.moodi.limusic.R
import kotlinx.android.synthetic.main.activity_main.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        uiInit()
    }

    private fun uiInit() {
        toolbarInit()
    }

    private fun toolbarInit() {
        setSupportActionBar(toolbar)
    }
}
