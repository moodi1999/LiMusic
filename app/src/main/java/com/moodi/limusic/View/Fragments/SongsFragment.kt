package com.moodi.limusic.View.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.moodi.limusic.R
import com.moodi.limusic.View.Activi.MainActivity
import com.moodi.limusic.View.Adaptors.SongRecyAdp
import com.moodi.limusic.model.data.Song
import kotlinx.android.synthetic.main.fragment_songs.view.*

class SongsFragment : Fragment(), MainActivity.onSongsArrReceived {

    override fun onSongsReceived(songs: ArrayList<Song>) {
        println("blank frag" + songs[0].title + "yes you are")
        recycler.adapter = SongRecyAdp(activity!!.applicationContext, songs)
    }

    internal lateinit var recycler: RecyclerView
    internal lateinit var view: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_songs, container, false)
        (activity as MainActivity).setAboutDataListener(this)
        recycler = view.song_fr_recyclerview
        recycler.layoutManager = GridLayoutManager(activity?.applicationContext, 1)
        recycler.setHasFixedSize(true)

        return view
    }

}
