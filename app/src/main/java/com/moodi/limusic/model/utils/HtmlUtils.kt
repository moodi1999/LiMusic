package com.moodi.limusic.model.utils

import android.util.Log
import com.moodi.limusic.model.data.Song
import com.moodi.limusic.model.storage.storage
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException
import java.util.regex.Pattern

/**
 * do all things about the html document that we have download
 * look at methods document
 */
class HtmlUtils {
    private val D = true
    private val TAG = "HtmlUtils"

    /**
     * this method need a url for connecting to web
     * and getting the page document and will
     * return a status log.
     * [storage.OK] : page content is in the app as a [String]
     * [storage.FAILED] : something failed and method will return the
     * exception as string.
     */
    fun GetWebPageHtmlDoc(url: String): Array<String>{
        val content = StringBuilder()

        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            var readChars: Int
            val inputBuffer = CharArray(500)
            while (true) {
                readChars = reader.read(inputBuffer)

                if (readChars < 0) {
                    break
                }
                if (readChars > 0) {
                    content.append(String(inputBuffer, 0, readChars))
                }
            }

            reader.close()
            return arrayOf(storage.OK, content.toString())

        } catch (e: MalformedURLException) {
            if (D) Log.i(TAG, "doInBackground : MalformedURLException is :=: ${e}")
            return arrayOf(storage.FAILED, e.toString())
        } catch (e: FileNotFoundException) {
            if (D) Log.i(TAG, "doInBackground : FileNotFoundException is :=: ${e}")
            return arrayOf(storage.FAILED, e.toString())
        } catch (e: UnknownHostException) {
            if (D) Log.i(TAG, "doInBackground : UnknownHostException is :=: ${e}")
            return arrayOf(storage.FAILED, e.toString())
        } catch (e: Exception) {
            if (D) Log.i(TAG, "doInBackground : Exception is :=: ${e}")
            return arrayOf(storage.FAILED, e.toString())
        }
    }


    /**
     * this method will split each song
     * and return them as a [List] of their
     * content.
     */
    fun splitSongs(htmlDoc: String): MutableList<String>? {
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
                patterMs(storage.PT_TITLE, songC)
            } catch (e: Exception) {
                try {
                    patterMs(storage.PT_TITLE2, songC) // find title in another line
                } catch (e: Exception) {
                    storage.NOT_FOUND
                }
            }

            title = try {
                title.split(storage.SP_TITLE)[1]  // حذف ؛دانلود؛ در متن
            } catch (e: Exception) {
                title
            }

            title = try {
                title.split(" جدید")[0] + title.split(" جدید")[1]
            } catch (e: Exception) {
                title
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