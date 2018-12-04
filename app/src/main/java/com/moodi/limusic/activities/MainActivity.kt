package com.moodi.limusic.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.moodi.limusic.asyncTasks.FetchSongs
import com.moodi.limusic.asyncTasks.GetPageContent
import com.moodi.limusic.R
import com.moodi.limusic.model.Song
import com.moodi.limusic.storage.storage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , GetPageContent.GetPageContentCallback , FetchSongs.FetchSongsCallback{
    private val TAG = "MainActivity"
    private val D = true
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getDate()
    }

    private fun getDate() {
        GetPageContent(this).execute(storage.MAIN_URL)
    }

    override fun onGetPageCompelet(content: String) {
        FetchSongs(this).execute(content)
    }

    override fun onGetPageFailed(exception: Exception) {
        if (D) Log.i(TAG, "onGetPageFailed: ");
    }

    override fun onSongsAvailable(songs: ArrayList<Song>) {
        if (D) Log.i(TAG, "onSongsAvailable: ");
    }
}
