package com.moodi.limusic.model

import java.io.Serializable

data class objser(val song: ArrayList<Song>) : Serializable{
    companion object {
        val serialVersionUID: Long = 20161120L
    }
}