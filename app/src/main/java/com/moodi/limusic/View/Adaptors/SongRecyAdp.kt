package com.moodi.limusic.View.Adaptors

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moodi.limusic.R
import com.moodi.limusic.model.data.Song
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_view_item.view.*
import kotlinx.android.synthetic.main.song_recy_item.view.*
import java.text.FieldPosition

class SongRecyAdp(val context: Context, val songs: ArrayList<Song>) : RecyclerView.Adapter<SongRecyAdp.SViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.song_recy_item, p0, false)
        return SViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(viewh: SViewHolder, position: Int) {
        val song = songs[position]
        viewh.title.text = song.title
        viewh.views.text = song.views
        viewh.cardView.setOnClickListener {
            println("song clicked")
        }
        println(song.imgUrl)
        Picasso.with(context!!).load(song.imgUrl).into(viewh.songImage)
    }

    inner class SViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.song_card_view
        val title = itemView.song_title
        val views = itemView.song_views
        val songImage = itemView.song_image_view
        init {
            cardView.preventCornerOverlap = false
            itemView.song_image_card.bringToFront()
            itemView.song_image_view.bringToFront()
        }
    }

}