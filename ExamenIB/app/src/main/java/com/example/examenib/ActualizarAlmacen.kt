package com.example.examenib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.example.examenib.adapter.AlmacenAdapter
import com.example.examenib.providers.AlmacenProvider

class ActualizarAlmacen : AppCompatActivity() {

    companion object {
        private lateinit var adapter: AlmacenAdapter

        fun setAdapter(almacenAdapter: AlmacenAdapter) {
            adapter = almacenAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_almacen)

        val idAlmacen = intent.getStringExtra("idAProducto")?.toInt()

        val almacen = AlmacenProvider.almacenesList.find { almacen -> almacen.storeID == idAlmacen }

        val txtNombre = findViewById<EditText>(R.id.input_almacen_name)
        val txtPrecio = findViewById<EditText>(R.id.input_precio_almacen)
        val chkIsOpen = findViewById<CheckBox>(R.id.chk_is_open)

        val btnActualizarAl = findViewById<Button>(R.id.btn_actualizar_almacenm)

        if (almacen != null) {
            txtNombre.setText(almacen.storeName)
            txtPrecio.setText(almacen.storeValue.toString())
            chkIsOpen.isChecked = almacen.isOpen
        }

        btnActualizarAl.setOnClickListener {
            if (almacen != null) {
                almacen.storeName = txtNombre.text.toString()
                almacen.storeValue = txtPrecio.text.toString().toDouble()
                almacen.isOpen = chkIsOpen.isChecked
                adapter.notifyDataSetChanged()
                finish()
            }
        }

    }
}