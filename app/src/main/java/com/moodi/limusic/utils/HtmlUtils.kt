package com.moodi.limusic.utils

import android.util.Log
import com.moodi.limusic.model.Song
import com.moodi.limusic.storage.storage
import java.util.regex.Pattern

/**
 * do all things about the html document that we have download
 * look at methods document
 */
class HtmlUtils(val htmlDoc: String) {
    private val D = false
    private val TAG = "HtmlUtils"


    /**
     * this method will split each song
     * and return them as a [List] of their
     * content.
     */
    fun splitSongs(): MutableList<String>? {
        var songsArray: MutableList<String>? = null
        try {
            songsArray = (htmlDoc.split(storage.SP_SONG)).toMutableList()

            val lastSong =
                songsArray[songsArray.lastIndex].split(storage.SP_SONG_LAST)[0] // split the last part to get last song data

            songsArray[songsArray.lastIndex] = lastSong
            songsArray.removeAt(0)
            if (D) Log.i(TAG, "splitSongs : songsArray is :=: ${songsArray.size}")

        } catch (e: Exception) {
            e.printStackTrace()
            songsArray = null
        } finally {
            return songsArray!!
        }
    }

    /**
     * looking in the every splits for song data title, link, img ...
     * return [ArrayList]
     */
    fun fetchSongData(songsContent: MutableList<String>): ArrayList<Song> {

        val songsArr: ArrayList<Song> = ArrayList()
        var link: String
        var title: String
        var category: String
        var date: String
        var imgUrl: String
        var views: String

        for (songC in songsContent) {

            link = try {
                patterMs(storage.PT_LINK, songC)
            } catch (e: Exception) {
                storage.NOT_FOUND
            }
            title = try {
                title = patterMs(storage.PT_TITLE, songC)
                try {
                    title.split(storage.SP_TITLE)[1]  // حذف ؛دانلود؛ در متن
                } catch (e: Exception) {
                    title
                }
            } catch (e: Exception) {
                try {
                    patterMs(storage.PT_TITLE2, songC) // find title in another line
                } catch (e: Exception) {
                    storage.NOT_FOUND
                }
            }
            try {
                category = ""
                val matcher = Pattern.compile(storage.PT_CATEGORIES).matcher(songC)
                while (matcher.find()) {
                    category += matcher.group(1) + ", "
                }
            } catch (e: Exception) {
                category = storage.NOT_FOUND
            }
            date = try {
                patterMs(storage.PT_DATE, songC)
            } catch (e: Exception) {
                storage.NOT_FOUND
            }
            imgUrl = try {
                patterMs(storage.PT_IMG_URL, songC)
            } catch (e: Exception) {
                storage.NOT_FOUND
            }
            views = try {
                patterMs(storage.PT_VIEWS, songC)
            } catch (e: Exception) {
                storage.NOT_FOUND
            }

            if (D) Log.i(TAG, "fetchSongData : songsContent INDEX is :=: ${songsContent.indexOf(songC)}")
            if (D) Log.i(TAG, "fetchSongData: title is $title")
            if (D) Log.i(TAG, "fetchSongData: category is $category")
            if (D) Log.i(TAG, "fetchSongData : views is :=: ${views}")
            if (D) Log.i(TAG, "fetchSongData : link is :=: ${link}")
            if (D) Log.i(TAG, "fetchSongData : imgUrl is :=: ${imgUrl}")
            if (D) Log.i(TAG, "fetchSongData : date is :=: ${date}")

            songsArr.add(Song(link, title, category, date, imgUrl, views))
        }

        return songsArr
    }

    private fun patterMs(regex: String, text: String): String {
        val matcher = Pattern.compile(regex).matcher(text)
        matcher.find()
        return matcher.group(1)
    }
}