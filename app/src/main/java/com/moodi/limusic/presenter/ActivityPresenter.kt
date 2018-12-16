package com.moodi.limusic.presenter

import com.moodi.limusic.View.interfaces.IActivityView
import com.moodi.limusic.asyncTasks.FetchSongs
import com.moodi.limusic.asyncTasks.GetPageContent
import com.moodi.limusic.model.Song

class ActivityPresenter(private val iActivityView: IActivityView) : IActivityPre,
    GetPageContent.GetPageContentCallback,
    FetchSongs.FetchSongsCallback
{

    override fun onFetchingStart(url: String) {
        GetPageContent(this).execute(url)
    }

    override fun onGetPageCompelet(content: String) {
        FetchSongs(this).execute(content)
    }

    override fun onGetPageFailed(exception: Exception) {
        iActivityView.onActivityStart(null)
    }

    override fun onSongsAvailable(songs: ArrayList<Song>) {
        iActivityView.onActivityStart(songs)
    }
}