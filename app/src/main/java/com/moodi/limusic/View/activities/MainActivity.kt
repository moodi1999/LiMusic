package com.moodi.limusic.View.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.moodi.limusic.presenter.ActivityPresenter
import com.moodi.limusic.presenter.IActivityPre
import com.moodi.limusic.R
import com.moodi.limusic.View.Adaptors.ViewPageAdaptor
import com.moodi.limusic.View.Fragments.SongsFragment
import com.moodi.limusic.View.interfaces.IActivityView
import com.moodi.limusic.model.data.Song
import com.moodi.limusic.model.storage.storage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    IActivityView {

    private val TAG = "MainActivity"
    private val D = true

    private lateinit var iActivityPre: IActivityPre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iActivityPre = ActivityPresenter(this)

        uiInit()
        getDate(storage.MAIN_URL)
    }

    private fun getDate(url: String) {
        iActivityPre.onFetchingStart(url)
    }

    //UI
    private fun uiInit() {
        viewPagerInit()
    }

    private fun viewPagerInit() {  // View pager and Tab Layout

        val viewPad = ViewPageAdaptor(supportFragmentManager)
        viewPad.addFragment(SongsFragment(), "Songs")
        viewpager.adapter = viewPad
        tab_bar_layout.setViewPager(viewpager)
    }

    //  view impl
    override fun MainActivityView(songs: ArrayList<Song>?) {
        mSongs?.onSongsReceived(songs!!)
    }

    // passing data to Fragment interface
    private var mSongs: onSongsArrReceived? = null
    interface onSongsArrReceived {
        fun onSongsReceived(songs: ArrayList<Song>)
    }
    fun setMSongs(listener: onSongsArrReceived) {
        this.mSongs = listener
    }
}