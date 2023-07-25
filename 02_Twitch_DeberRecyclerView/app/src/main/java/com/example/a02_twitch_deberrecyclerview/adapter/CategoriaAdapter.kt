package com.example.a02_twitch_deberrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a02_twitch_deberrecyclerview.R
import com.example.a02_twitch_deberrecyclerview.models.Categoria

class CategoriaAdapter(
    val listaCategorias: List<Categoria>
):RecyclerView.Adapter<CategoriaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriaViewHolder(layoutInflater.inflate(R.layout.item_categoria, parent, false))

    }

    override fun getItemCount(): Int = listaCategorias.size


    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val item = listaCategorias[position]
        holder.render(item)
    }

}