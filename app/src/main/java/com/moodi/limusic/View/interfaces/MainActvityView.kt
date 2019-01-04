package com.moodi.limusic.View.interfaces

import com.moodi.limusic.model.data.Song
import java.lang.Exception

interface MainActvityView{
    fun onSongFetchSucceed(songs: ArrayList<Song>?)
    fun onSongFetchFailed(exception: String)
}