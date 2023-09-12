package com.example.examenib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenib.adapter.AlmacenAdapter
import com.example.examenib.adapter.AlmacenViewHolder
import com.example.examenib.models.Almacen
import com.example.examenib.models.Electrodomestico
import com.example.examenib.providers.AlmacenProvider

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AlmacenAdapter
    lateinit var dbHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = SQLiteHelper(this)


        initRecyclerView(dbHelper.getAllAlmacenes())

        val botonCrearAlmacen = findViewById<Button>(R.id.btn_crear_almacen)

        botonCrearAlmacen.setOnClickListener {
            val intent = Intent(this, CreateAlmacen::class.java)
            startActivityForResult(intent, CREATE_ALMACEN)
        }
    }

    fun initRecyclerView(listAlmacenes: List<Almacen>) {
        dbHelper = SQLiteHelper(this)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_almacenes)
        adapter = AlmacenAdapter(listAlmacenes, dbHelper)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        AlmacenViewHolder.setAdapter(adapter)
        ActualizarAlmacen.setAdapter(adapter)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        dbHelper = SQLiteHelper(this)

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_ALMACEN && resultCode == Activity.RESULT_OK && data != null) {
            val nombreAlmacen = data.getStringExtra("nombre")
            val direccionAlmacen = data.getStringExtra("direccion")
            val precioAlmacen = data.getDoubleExtra("precio", 0.0)

            val nuevoAlmacen = Almacen(
                id = null,
                storeName = nombreAlmacen,
                storeLocation = direccionAlmacen,
                isOpen = false,
                storeValue = precioAlmacen
            )
            dbHelper.addAlmacen(nuevoAlmacen)

            // Obtén la lista actualizada de almacenes y actualiza el adaptador
            val listAlmacenes = dbHelper.getAllAlmacenes()
            adapter.updateData(listAlmacenes)
        }
    }


    companion object {
        private const val CREATE_ALMACEN = 1
    }


}