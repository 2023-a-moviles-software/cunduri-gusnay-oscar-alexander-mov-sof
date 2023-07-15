package com.example.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.adapter.ElectrodomesticoAdapter
import com.example.examenib.models.Almacen
import com.example.examenib.providers.AlmacenProvider

class ListaProductos : AppCompatActivity() {

    private lateinit var adapter: ElectrodomesticoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)

        val nombreAlmacen = intent.getStringExtra("nombre")

        val tb = findViewById<TextView>(R.id.txttest)
        tb.text = nombreAlmacen

        initRecyclerView()
    }

    fun initRecyclerView(){
        val recyclerViewElctrodomesticos = findViewById<RecyclerView>(R.id.rv_lista_productos)

        val idAlmacen = intent.getStringExtra("idAProducto")
        val id = idAlmacen?.split(" ")?.get(1)?.toInt()

        if(id?.let { getAlmacenById(it)?.products?.isEmpty() } == false){
            adapter = id?.let { getAlmacenById(it)?.products?.let { ElectrodomesticoAdapter(it) } }!!
            recyclerViewElctrodomesticos.layoutManager = LinearLayoutManager(this)
            recyclerViewElctrodomesticos.adapter = adapter
        }

    }

    fun getAlmacenById(id: Int): Almacen?{
        return AlmacenProvider.almacenesList.find { almacen -> almacen.storeID == id }
    }



}