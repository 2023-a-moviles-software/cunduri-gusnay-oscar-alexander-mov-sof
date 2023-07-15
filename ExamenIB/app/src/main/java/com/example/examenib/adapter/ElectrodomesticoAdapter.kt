package com.example.examenib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.R
import com.example.examenib.models.Almacen
import com.example.examenib.models.Electrodomestico

class ElectrodomesticoAdapter(
    private val electrodomesticoList: MutableList<Electrodomestico>
) : RecyclerView.Adapter<ElectrodomesticoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectrodomesticoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ElectrodomesticoViewHolder(
            layoutInflater.inflate(
                R.layout.item_electrodomestico,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = electrodomesticoList.size

    override fun onBindViewHolder(holder: ElectrodomesticoViewHolder, position: Int) {
        val item = electrodomesticoList[position]
        holder.render(item)
    }


}