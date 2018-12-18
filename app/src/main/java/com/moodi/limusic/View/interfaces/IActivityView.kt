package com.moodi.limusic.View.interfaces

import com.moodi.limusic.model.data.Song

interface IActivityView{
    fun MainActivityView(songs: ArrayList<Song>?)
}