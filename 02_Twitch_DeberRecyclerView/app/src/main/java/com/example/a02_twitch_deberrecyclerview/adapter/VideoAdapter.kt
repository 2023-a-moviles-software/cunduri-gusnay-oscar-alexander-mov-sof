package com.example.a02_twitch_deberrecyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_twitch_deberrecyclerview.models.Video

class VideoAdapter(
    val listaVideo: List<Video>
) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = listaVideo.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}