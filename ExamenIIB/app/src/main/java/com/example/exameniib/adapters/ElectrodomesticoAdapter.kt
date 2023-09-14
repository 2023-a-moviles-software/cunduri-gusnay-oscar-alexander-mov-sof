package com.example.exameniib.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exameniib.R
import com.example.exameniib.models.Electrodomestico
import com.example.exameniib.viewholders.ElectrodomesticoViewHolder

class ElectrodomesticoAdapter(
    private var electrodomesticoList: MutableList<Electrodomestico>,
) : RecyclerView.Adapter<ElectrodomesticoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectrodomesticoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ElectrodomesticoViewHolder(
            layoutInflater.inflate(
                R.layout.item_electrodomestico, parent, false
            ))
    }

    override fun getItemCount(): Int = electrodomesticoList.size

    override fun onBindViewHolder(holder: ElectrodomesticoViewHolder, position: Int) {
        val item = electrodomesticoList[position]
        holder.render(item)
    }

    fun updateData(newData: MutableList<Electrodomestico>) {
        electrodomesticoList = newData
        notifyDataSetChanged()
    }

}