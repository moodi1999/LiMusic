package com.moodi.limusic.presenter

import com.moodi.limusic.View.interfaces.MainActvityView
import com.moodi.limusic.presenter.asyncTasks.FetchSongs
import com.moodi.limusic.presenter.asyncTasks.GetPageContent
import com.moodi.limusic.model.data.Song

class MainActivityPresenter(private val mainActvityView: MainActvityView) :
    IActivityPre,
    GetPageContent.GetPageContentCallback,
    FetchSongs.FetchSongsCallback
{
    // IActivityPre
    override fun onFetchingStart(url: String) {
        GetPageContent(this).execute(url)
    }

    // GetPageContentCallbacks
    override fun onGetPageCompelet(content: String) {
        FetchSongs(this).execute(content)
    }

    override fun onGetPageFailed(exception: String) {
        mainActvityView.onSongFetchFailed(exception)
    }

    // FetchSongsCallbacks
    override fun onSongsAvailable(songs: ArrayList<Song>) {
        mainActvityView.onSongFetchSucceed(songs)
    }
}