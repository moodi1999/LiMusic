package com.moodi.limusic.View.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.moodi.limusic.R
import com.moodi.limusic.View.activities.MainActivity
import com.moodi.limusic.View.Adaptors.SongRecyAdp
import com.moodi.limusic.model.data.Song
import com.moodi.limusic.model.storage.storage
import kotlinx.android.synthetic.main.fragment_songs.view.*

class SongsFragment : Fragment(), MainActivity.OnPassDataToFragment {

    private val TAG = "SongsFragment"

    internal lateinit var recycler: RecyclerView
    internal lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_songs, container, false)

        (activity as MainActivity).fragmentInstance = this

        recycler = view.song_fr_recyclerview
        recycler.layoutManager = GridLayoutManager(activity?.baseContext, 1)
        recycler.setHasFixedSize(true)

        return view
    }


//    MainActivity.OnPassDataToFragment
//    coming from MainActivity
    override fun Received(songs: ArrayList<Song>) {
        recycler.adapter = SongRecyAdp(activity!!.baseContext, songs)
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        if (storage.recentSong != null) {
            recycler.adapter = SongRecyAdp(activity!!.baseContext, storage.recentSong!!)
        }
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }
}
