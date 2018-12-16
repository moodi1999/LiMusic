package com.moodi.limusic.View.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.moodi.limusic.R
import com.moodi.limusic.View.Activi.MainActivity
import com.moodi.limusic.model.data.Song
import kotlinx.android.synthetic.main.fragment_songs.view.*

class SongsFragment : Fragment(), MainActivity.ISongsPD {

    override fun onSongRecived(model: ArrayList<Song>) {
        println("blank frag" + model[0].title + "yes you are")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_songs, container, false)
        (activity as MainActivity).setAboutDataListener(this)
        val recycler = view.song_fr_recyclerview



        return view
    }

}
