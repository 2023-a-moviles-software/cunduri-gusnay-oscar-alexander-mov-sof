package com.example.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examenib.adapter.AlmacenAdapter
import com.example.examenib.adapter.ElectrodomesticoAdapter

class ActualizarElectrodomestico : AppCompatActivity() {

    private lateinit var dbHelper: SQLiteHelper

    companion object {
        private lateinit var adapter: ElectrodomesticoAdapter

        fun setAdapter(almacenAdapter: ElectrodomesticoAdapter) {
            adapter = almacenAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_electrodomestico)

        dbHelper = SQLiteHelper(this)

        val idElectro = intent.getStringExtra("idE")?.toInt()

        val txtNombre = findViewById<EditText>(R.id.txt_actualizar_nombre_input)
        val txtPrecio = findViewById<EditText>(R.id.txt_actualizar_precio_input)
        val txtCantidad = findViewById<EditText>(R.id.txt_actualizar_cantidad_input)
        val btnActualizar = findViewById<Button>(R.id.guardar_producto_actualizado)

        val electrodomesticoYAlmacen =
            idElectro?.let { dbHelper.getElectrodomesticoYAlmacenById(it) }
        val electrodomestico = electrodomesticoYAlmacen?.electrodomestico
        val storeId = electrodomesticoYAlmacen?.storeId

        txtNombre.setText(electrodomestico?.productName)
        txtPrecio.setText(electrodomestico?.price.toString())
        txtCantidad.setText(electrodomestico?.cantidad.toString())

        btnActualizar.setOnClickListener {
            if (electrodomesticoYAlmacen != null) {

                dbHelper.updateElectrodomestico(
                    idElectro,
                    txtNombre.text.toString(),
                    txtPrecio.text.toString().toDouble(),
                    txtCantidad.text.toString().toInt()
                )
                val listaElectro = dbHelper.getElectrodomesticosByAlmacenId(storeId)
                adapter.updateData(listaElectro)
                finish()
            }
        }
    }
}