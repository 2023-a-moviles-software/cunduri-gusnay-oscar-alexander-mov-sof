package com.example.a02_twitch_deberrecyclerview.models

class Video(
    val imgLink : String,
    val nombreCanal: String,
    val iconoCanal: String,
    val descripcionStream: String,
    val juego: String,
    val categorias: Array<String>
) {
    init {
        this.imgLink
        this.nombreCanal
        this.iconoCanal
        this.descripcionStream
        this.juego
        this.categorias
    }
}