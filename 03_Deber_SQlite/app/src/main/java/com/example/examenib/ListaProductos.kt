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
import com.example.examenib.models.Electrodomestico

class ListaProductos : AppCompatActivity() {

    private lateinit var adapter: ElectrodomesticoAdapter
    private lateinit var dbHelper: SQLiteHelper
    private var idAlmacen: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)

        val nombreAlmacen = intent.getStringExtra("nombre")
        val idAlmacenStr = intent.getStringExtra("idAProducto")
        idAlmacen = idAlmacenStr?.split(" ")?.get(1)?.toInt() ?: -1

        val tb = findViewById<TextView>(R.id.txttest)
        tb.text = nombreAlmacen
        dbHelper = SQLiteHelper(this)
        idAlmacen?.let { dbHelper.getElectrodomesticosByAlmacenId(it) }?.let { initRecyclerView(it) }
        ElectrodomesticoViewHolder.setAlmacenID(idAlmacen)

        val btnCrearProducto = findViewById<Button>(R.id.btn_crear_nuevo_producto)


        btnCrearProducto.setOnClickListener {
            val intent = Intent(this, CreateElectrodomestico::class.java)
            startActivityForResult(intent, CREATE_PRODUCT)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ListaProductos.CREATE_PRODUCT && resultCode == Activity.RESULT_OK && data != null) {

            dbHelper = SQLiteHelper(this)
            val nombreEl = data.getStringExtra("nombreE")
            val precioEl = data.getStringExtra("precioE")
            val cantidadEl = data.getStringExtra("CantidadE")

            val nuevoProducto = Electrodomestico(
                null,
                nombreEl.toString(),
                precioEl.toString().toDouble(),
                cantidadEl.toString().toInt(),
                "",
                false
            )

            dbHelper.addElectrodomesticoToAlmacen(nuevoProducto, idAlmacen)

            adapter.updateData( dbHelper.getElectrodomesticosByAlmacenId(idAlmacen))

        }
    }

    fun initRecyclerView(listaElectrodomesticos: List<Electrodomestico>) {
        dbHelper = SQLiteHelper(this)
        val recyclerViewElctrodomesticos = findViewById<RecyclerView>(R.id.rv_lista_productos)
        adapter = ElectrodomesticoAdapter(listaElectrodomesticos, dbHelper)
        recyclerViewElctrodomesticos.layoutManager = LinearLayoutManager(this)
        recyclerViewElctrodomesticos.adapter = adapter
        ElectrodomesticoViewHolder.setAdapter(adapter)
        ActualizarElectrodomestico.setAdapter(adapter)
    }


    companion object {
        private const val CREATE_PRODUCT = 1
    }
}


