package com.moodi.limusic.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.moodi.limusic.GetPageContent
import com.moodi.limusic.R
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() , GetPageContent.GetPageContentCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GetPageContent(this).execute("http://google.com/")
    }

    override fun onGetPageCompelet(content: String) {
        println(content)
    }

    override fun onGetPageFailed(exception: Exception) {

    }
}
