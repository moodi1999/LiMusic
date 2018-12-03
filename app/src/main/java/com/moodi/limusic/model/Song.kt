package com.moodi.limusic.model

/**
 * it's data model class that hold the song data
 */
data class Song(
    val link: String,
    val title: String,
    val category: String,
    val date: String,
    val imgUrl: String,
    val fa_songName: String,
    val fa_artistName: String,
    val en_songName: String,
    val en_artistName: String,
    val songPage: SongPage? = null
)