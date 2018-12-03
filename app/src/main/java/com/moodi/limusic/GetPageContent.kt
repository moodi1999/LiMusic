package com.moodi.limusic

import android.os.AsyncTask

interface GetPageContentCallback {
    fun onGetPageCompelet(content: String)
    fun onGetPageFailed()
}
class GetPageContent(mCallback: GetPageContentCallback) : AsyncTask<String, Unit, String>() {

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }

    override fun doInBackground(vararg params: String?): String {
        
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}