package com.moodi.limusic.View.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.moodi.limusic.presenter.MainActivityPresenter
import com.moodi.limusic.presenter.IActivityPre
import com.moodi.limusic.R
import com.moodi.limusic.View.Adaptors.ViewPageAdaptor
import com.moodi.limusic.View.Fragments.SongsFragment
import com.moodi.limusic.View.interfaces.MainActvityView
import com.moodi.limusic.model.data.Song
import com.moodi.limusic.model.storage.storage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    MainActvityView {

    private val TAG = "MainActivity"
    private val D = true

    private lateinit var iActivityPre: IActivityPre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // make presenter by passing the view
        iActivityPre = MainActivityPresenter(this)

        uiInit()
        getDate(storage.MAIN_URL)
    }

    private fun getDate(url: String) {
        iActivityPre.onFetchingStart(url)
    }

    //UI
    private fun uiInit() {
        viewPagerInit()
        toolbarInit()
    }

    private fun toolbarInit() {
        setSupportActionBar(toolbar)
    }

    private fun viewPagerInit() {  // View pager and Tab Layout

        val viewPad = ViewPageAdaptor(supportFragmentManager)
        viewPad.addFragment(SongsFragment(), "اخیر")
        viewPad.addFragment(SongsFragment(), "دسته بندی")
        viewPad.addFragment(SongsFragment(), "محبوب")
        viewpager.offscreenPageLimit = 0
        viewpager.adapter = viewPad
        tab_bar_layout.setViewPager(viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainactivity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.itemId.let {
            when (it) {
                R.id.app_bar_search ->
                    Log.i(TAG, "onOptionsItemSelected: search clicked")
                else ->
                    return false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //  view impl
    override fun onSongFetchSucceed(songs: ArrayList<Song>?) {
        fragmentInstance?.Received(songs!!)
    }
    override fun onSongFetchFailed(exception: String){

    }

    // passing data to Fragment interface
    interface OnPassDataToFragment {
        fun Received(songs: ArrayList<Song>)
    }
    var fragmentInstance: OnPassDataToFragment? = null

}
