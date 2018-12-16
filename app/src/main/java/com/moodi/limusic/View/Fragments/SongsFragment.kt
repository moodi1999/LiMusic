package com.moodi.limusic.View.Fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.moodi.limusic.R
import com.moodi.limusic.model.Song
import com.moodi.limusic.model.objser

class SongsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_songs, container, false)

        return view
    }

    override fun onResume() {
        super.onResume()
            println("not got here")
        if (arguments != null) {
            println("in if resume")

            val m = arguments?.getString("m")
            println(m)
            val arg = arguments?.getSerializable("Songs")
            var title = (arg as objser)
            println(title.song.get(0).title + "frag")
        }
    }


}
