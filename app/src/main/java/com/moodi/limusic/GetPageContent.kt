package com.moodi.limusic

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException

interface GetPageContentCallback {
    fun onGetPageCompelet(content: String)
    fun onGetPageFailed()
}

class GetPageContent(val mCallback: GetPageContentCallback) : AsyncTask<String, Unit, String>() {
    private val TAG = "GetPageContent"
    private val D = false

    private val FAILED = "Failed"

    override fun doInBackground(vararg urls: String?): String {
        val content = StringBuilder()

        try {
            val connection = URL(urls[0]).openConnection() as HttpURLConnection
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
            return content.toString()

        } catch (malf: MalformedURLException) {
            if (D) Log.i(TAG, "doInBackground : malf is :=: ${malf}")
            mCallback.onGetPageFailed()
            return FAILED
        } catch (e: FileNotFoundException) {
            if (D) Log.i(TAG, "doInBackground : FileNotFoundException is :=: ${e}")
            return FAILED
        } catch (e: UnknownHostException) {
            if (D) Log.i(TAG, "doInBackground : UnknownHostException is :=: ${e}")
            return FAILED
        } catch (e: Exception) {
            if (D) Log.i(TAG, "doInBackground : Exception is :=: ${e}")
            return FAILED
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != FAILED) {
            mCallback.onGetPageCompelet(result!!)
        }
    }

}