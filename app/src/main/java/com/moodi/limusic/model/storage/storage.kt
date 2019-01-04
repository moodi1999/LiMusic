package com.moodi.limusic.model.storage

import com.moodi.limusic.model.data.Song
import java.io.Serializable

object storage : Serializable {

    val MAIN_URL = "http://pop-music.ir/"

    // recent song arr
    var recentSong: ArrayList<Song>? = null

    // split main song content
    val SP_SONG = "<article class=\"post\">"
    val SP_SONG_LAST = "<div class=\"latestforum\">"



    // Split, matchers and patterns for fetch data from the html document
    val PT_LINK = "<h2><a href=\"(.*?)\" rel=\"bookmark\" title=\""
    val SP_TITLE = "دانلود"
    val PT_TITLE = "title=\"(.*?)\" src=\""
    val PT_TITLE2 = "\"bookmark\" title=\"(.*?)\">"
    val PT_CATEGORIES = "rel=\"category tag\">(.*?)</a>"
    val PT_DATE = "<span class=\"date\">(.*?)</span>"
    val PT_IMG_URL = "src=\"(.*?)\""
    val PT_VIEWS = "class=\"view\">(.*?)</span>"


    // Errors
    val NOT_FOUND = "Not Found!"
    val FAILED = "Failed"

    val OK = "OK"
}