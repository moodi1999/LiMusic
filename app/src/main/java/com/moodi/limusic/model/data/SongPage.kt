package com.moodi.limusic.model.data

data class SongPage(
    val pageContent: String,
    val mp3_128: String,
    val mp3_320: String,
    val fa_songName: String,
    val fa_artistName: String,
    val en_songName: String,
    val en_artistName: String
//    val comments: ArrayList<String>,
)