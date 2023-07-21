package com.example.a02_twitch_deberrecyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_twitch_deberrecyclerview.models.Categoria

class CategoriaAdapter(
    val listaCategorias: List<Categoria>
):RecyclerView.Adapter<CategoriaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = listaCategorias.size


    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}