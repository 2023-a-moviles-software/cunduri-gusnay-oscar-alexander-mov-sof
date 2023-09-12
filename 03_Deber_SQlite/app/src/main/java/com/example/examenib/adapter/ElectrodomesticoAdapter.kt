package com.example.examenib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.R
import com.example.examenib.SQLiteHelper
import com.example.examenib.models.Electrodomestico

class ElectrodomesticoAdapter(
    private var electrodomesticoList: List<Electrodomestico>,
    private var dbHelper: SQLiteHelper
) : RecyclerView.Adapter<ElectrodomesticoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectrodomesticoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ElectrodomesticoViewHolder(
            layoutInflater.inflate(
                R.layout.item_electrodomestico, parent, false
            ), dbHelper)
    }

    override fun getItemCount(): Int = electrodomesticoList.size

    override fun onBindViewHolder(holder: ElectrodomesticoViewHolder, position: Int) {
        val item = electrodomesticoList[position]
        holder.render(item)
    }

    fun updateData(newData: List<Electrodomestico>) {
        electrodomesticoList = newData
        notifyDataSetChanged()
    }

}