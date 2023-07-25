package com.example.a02_twitch_deberrecyclerview.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.a02_twitch_deberrecyclerview.R
import com.example.a02_twitch_deberrecyclerview.models.Categoria

class CategoriaViewHolder(view:View):RecyclerView.ViewHolder(view){

    val image = view.findViewById<ImageView>(R.id.iv_icategoria_image)
    val nombreCategoria = view.findViewById<TextView>(R.id.tv_nombre_categoria)
    val espectadores = view.findViewById<TextView>(R.id.tv_vistas_categoria)
    fun render(categoria: Categoria){
        Glide.with(image.context).load(categoria.imageURL).centerCrop().into(image)
        nombreCategoria.text = categoria.nombreCategoria
        espectadores.text = categoria.vistas
    }
}