package com.example.a02_twitch_deberrecyclerview.providers

import com.example.a02_twitch_deberrecyclerview.models.Categoria
import com.example.a02_twitch_deberrecyclerview.models.Video

class VideoProvider {

    companion object {
        val listaVideosSiguiendo: List<Video> = arrayListOf(
            Video(
                "https://i.ytimg.com/vi/B7E6NyWnwbI/maxresdefault.jpg",
                "VALORANT",
                "https://static-cdn.jtvnw.net/jtv_user_pictures/375c4db6-af2a-489e-842a-5e5b3ce287a2-profile_image-70x70.png",
                "LCQ Pacific - Day 4",
                "VALORANT",
                arrayOf("VCTPacific", "VCT")
            ),
            Video(
                "https://static-cdn.jtvnw.net/previews-ttv/live_user_horcus-640x360.jpg",
                "HORCUS",
                "https://static-cdn.jtvnw.net/jtv_user_pictures/f60cc94c-c494-46ce-b6eb-8ded08a292fd-profile_image-70x70.png",
                "LCQ Pacific - Day 4",
                "VALORANT",
                arrayOf("Español", "España", "Valorant")
            ),
            Video(
                "https://static-cdn.jtvnw.net/previews-ttv/live_user_horcus-640x360.jpg",
                "HORCUS",
                "https://static-cdn.jtvnw.net/jtv_user_pictures/f60cc94c-c494-46ce-b6eb-8ded08a292fd-profile_image-70x70.png",
                "LCQ Pacific - Day 4",
                "VALORANT",
                arrayOf("Español", "España", "Valorant")
            )
        )

        val listaCategoriasSiguiendo: List<Categoria> = arrayListOf(
            Categoria(
                "https://static-cdn.jtvnw.net/ttv-boxart/516575-285x380.jpg",
                "VALORANT",
                "235k"
            ),
            Categoria(
                "https://static-cdn.jtvnw.net/ttv-boxart/509658-285x380.jpg",
                "Just Chati...",
                "23.1k"
            ),
            Categoria(
                "https://static-cdn.jtvnw.net/ttv-boxart/518203-285x380.jpg",
                "Deportes",
                "9.7k"
            )
        )
    }
}