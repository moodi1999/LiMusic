package com.moodi.limusic.asyncTasks

import android.os.AsyncTask
import android.util.Log
import com.moodi.limusic.model.storage.storage
import com.moodi.limusic.model.utils.HtmlUtils
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException


class GetPageContent(val mCallback: GetPageContentCallback) : AsyncTask<String, Unit, Array<String>>() {
    private val TAG = "GetPageContent"
    private val D = false

    interface GetPageContentCallback {
        fun onGetPageCompelet(content: String)
        fun onGetPageFailed(exception: String)
    }

    override fun doInBackground(vararg urls: String?): Array<String> {
        return HtmlUtils().GetWebPageHtmlDoc(urls[0]!!)
    }

    override fun onPostExecute(result: Array<String>) {
        super.onPostExecute(result)
        if (result[0] == storage.OK) {
            mCallback.onGetPageCompelet(result[1])
        } else {
            mCallback.onGetPageFailed(result[1])
        }
    }

}