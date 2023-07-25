package com.example.a02_twitch_deberrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_twitch_deberrecyclerview.R
import com.example.a02_twitch_deberrecyclerview.models.Video

class VideoAdapter(
    val listaVideo: List<Video>
) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return VideoViewHolder(layoutInflater.inflate(R.layout.item_video, parent, false))
    }

    override fun getItemCount(): Int = listaVideo.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = listaVideo[position]
        holder.render(item)
    }
}