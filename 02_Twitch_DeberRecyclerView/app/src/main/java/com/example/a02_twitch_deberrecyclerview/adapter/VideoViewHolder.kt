package com.example.a02_twitch_deberrecyclerview.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a02_twitch_deberrecyclerview.R
import com.example.a02_twitch_deberrecyclerview.models.Video

class VideoViewHolder(val view: View): RecyclerView.ViewHolder(view){

    val vistaPrevia = view.findViewById<ImageView>(R.id.iv_vista_previa)
    val iconoCanal = view.findViewById<ImageView>(R.id.iv_icono_canal)
    val txtNombreCanl = view.findViewById<TextView>(R.id.tv_nombre_canal)
    val txtDescripcion = view.findViewById<TextView>(R.id.tv_descripcion_stream)
    val txtJuego = view.findViewById<TextView>(R.id.tv_juego_stream)
    val txtViewers = view.findViewById<TextView>(R.id.tv_counter_viewers)
    fun render(video: Video){
        txtNombreCanl.text = video.nombreCanal
        txtDescripcion.text = video.descripcionStream
        txtJuego.text = video.juego
        Glide.with(vistaPrevia.context).load(video.imgLink).centerCrop().into(vistaPrevia)
        Glide.with(iconoCanal.context).load(video.iconoCanal).into(iconoCanal)
        txtViewers.text = video.views
    }
}