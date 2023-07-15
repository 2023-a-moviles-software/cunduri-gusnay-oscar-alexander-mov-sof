package com.example.examenib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.adapter.ElectrodomesticoAdapter
import com.example.examenib.adapter.ElectrodomesticoViewHolder
import com.example.examenib.models.Almacen
import com.example.examenib.models.Electrodomestico
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

        val btnCrearProducto = findViewById<Button>(R.id.btn_crear_nuevo_producto)


        btnCrearProducto.setOnClickListener{
            val intent = Intent(this, CreateElectrodomestico::class.java)
            startActivityForResult(intent, CREATE_PRODUCT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ListaProductos.CREATE_PRODUCT && resultCode == Activity.RESULT_OK && data != null) {

            val nombreEl = data.getStringExtra("nombreE")
            val precioEl = data.getStringExtra("precioE")
            val cantidadEl = data.getStringExtra("CantidadE")

            val nuevoProducto = Electrodomestico(
                null,
                nombreEl.toString(),
                precioEl.toString().toDouble(),
                cantidadEl.toString().toInt(),
                null,
                null

            )

            val idAlmacen = intent.getStringExtra("idAProducto")
            val id = idAlmacen?.split(" ")?.get(1)?.toInt()
            val almacen = AlmacenProvider.almacenesList.find { almacen -> almacen.storeID == id }
            val listaElectro = almacen?.products
            if (listaElectro != null) {
                listaElectro.add(nuevoProducto)
            }

            adapter.notifyDataSetChanged()

        }
    }

    fun initRecyclerView(){
        val recyclerViewElctrodomesticos = findViewById<RecyclerView>(R.id.rv_lista_productos)

        val idAlmacen = intent.getStringExtra("idAProducto")
        val id = idAlmacen?.split(" ")?.get(1)?.toInt()

        if (id != null) {
            idAlmacenAux = id
        }

        val listaElectro = id?.let { getAlmacenById(it)?.products }

            adapter = listaElectro?.let { ElectrodomesticoAdapter(it) }!!
            recyclerViewElctrodomesticos.layoutManager = LinearLayoutManager(this)
            recyclerViewElctrodomesticos.adapter = adapter
            ElectrodomesticoViewHolder.setAdapter(adapter)
            ActualizarElectrodomestico.setAdapter(adapter)


    }

    fun getAlmacenById(id: Int): Almacen?{
        return AlmacenProvider.almacenesList.find { almacen -> almacen.storeID == id }
    }

    companion object {
        private const val CREATE_PRODUCT = 1

        var idAlmacenAux: Int = 0


    }

}