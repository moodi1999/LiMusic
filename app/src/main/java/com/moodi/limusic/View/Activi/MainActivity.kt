package com.moodi.limusic.View.Activi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.moodi.limusic.presenter.ActivityPresenter
import com.moodi.limusic.presenter.IActivityPre
import com.moodi.limusic.R
import com.moodi.limusic.View.Adaptors.ViewPageAdaptor
import com.moodi.limusic.View.Fragments.SongsFragment
import com.moodi.limusic.View.interfaces.IActivityView
import com.moodi.limusic.model.Song
import com.moodi.limusic.model.objser
import com.moodi.limusic.storage.storage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity(),
    IActivityView {

    private val TAG = "MainActivity"
    private val D = true

    private lateinit var iActivityPre: IActivityPre

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            getDate("http://pop-music.ir/page/3")
        }
        iActivityPre = ActivityPresenter(this)
        uiInit()
//        getDate(storage.MAIN_URL)
    }

    private fun getDate(st: String) {
        iActivityPre.onFetchingStart(st)
    }

    //UI
    private fun uiInit() {
        //viewPagerInit(null)
    }

    private fun viewPagerInit(songs: ArrayList<Song>?) {  // View pager and Tab Layout

        val viewPad = ViewPageAdaptor(supportFragmentManager)
        /*val fragment = SongsFragment()
        if (songs != null) {
            val ser = objser(songs)
            fragment.arguments = Bundle().apply {
                putSerializable("Songs", ser)
                putString("m", "m")
            }

            println("got here")
        }*/

        var fragment = BlankFragment()
        if (songs != null) {
            fragment = BlankFragment.newInstance(objser(songs))
        }

        viewPad.addFragment(fragment, "Songs")
        viewpager.adapter = viewPad
        tab_bar_layout.setViewPager(viewpager)
    }

    //    view impl
    override fun onActivityStart(songs: ArrayList<Song>?) {
        println(songs?.get(0)?.title)
        viewPagerInit(songs)
    }

}
