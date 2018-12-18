package com.moodi.limusic.asyncTasks

import android.os.AsyncTask
import com.moodi.limusic.model.utils.HtmlUtils
import com.moodi.limusic.model.data.Song

class FetchSongs(val mCallback: FetchSongsCallback) : AsyncTask<String, Unit, ArrayList<Song>>() {
    private val D = true
    private val TAG = "FetchSongs"

    interface FetchSongsCallback {
        fun onSongsAvailable(songs: ArrayList<Song>)
    }

    override fun doInBackground(vararg params: String?): ArrayList<Song>? {
        HtmlUtils().run {
            return fetchSongData(splitSongs(params[0]!!)!!)
        }
    }

    override fun onPostExecute(result: ArrayList<Song>?) {
        super.onPostExecute(result)
        mCallback.onSongsAvailable(result!!)
    }



}