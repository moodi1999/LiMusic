package com.moodi.limusic

import android.os.AsyncTask
import com.moodi.limusic.model.Song
import com.moodi.limusic.storage.storage
import java.io.Serializable

class FetchSongs() : AsyncTask<String, Unit, ArrayList<Song>>() {

    interface FetchSongsCallback {
        fun onSongsAvailable(songs: ArrayList<Song>)
    }

    override fun doInBackground(vararg params: String?): ArrayList<Song> {

    }

    override fun onPostExecute(result: ArrayList<Song>?) {
        super.onPostExecute(result)

    }

    /**
     * this method will split each song
     * and return them as a [List] of their
     * content.
     */
    private fun splitSongs(content: String): MutableList<String> {
        var songsArray: MutableList<String>? = null
        try {
            songsArray = (content.split(storage.SP_SONG)).toMutableList()
            songsArray = songsArray
        } catch (e: Exception) {
            e.printStackTrace()
            songsArray = null
        }finally {
            return songsArray!!
        }
    }
}