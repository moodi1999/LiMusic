package com.moodi.limusic.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.moodi.limusic.GetPageContent
import com.moodi.limusic.GetPageContentCallback
import com.moodi.limusic.R

class MainActivity : AppCompatActivity() , GetPageContentCallback{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GetPageContent(this).execute("http://printatesfasfasfsdafstpage.com/")
    }

    override fun onGetPageCompelet(content: String) {
        println(content)
    }

    override fun onGetPageFailed() {

    }
}
