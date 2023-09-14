package com.example.exameniib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarAlmacen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_almacen)

        val almacenId = intent.getStringExtra("idAProducto")

        val txtNombre = findViewById<EditText>(R.id.input_almacen_name)
        val txtPrecio = findViewById<EditText>(R.id.input_precio_almacen)
        val chkIsOpen = findViewById<CheckBox>(R.id.chk_is_open)
        val btnActualizarAl = findViewById<Button>(R.id.btn_actualizar_almacenm)

        val db = Firebase.firestore
        val almacenRef = almacenId?.let { db.collection("almacen").document(it) }

        almacenRef?.get()
            ?.addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val almacenData = documentSnapshot.data
                    if (almacenData != null) {
                        val nombre = almacenData["storeName"] as? String
                        val precio = almacenData["storeValue"] as? Double
                        val isOpen = almacenData["isOpen"] as? Boolean
                        if (nombre != null && isOpen != null) {
                            txtNombre.setText(nombre)
                            txtPrecio.setText(precio.toString())
                            chkIsOpen.isChecked = isOpen
                        }
                    }
                }
            }
            ?.addOnFailureListener {  }



        btnActualizarAl.setOnClickListener {
            if (almacenId != null) {
                val db1 = Firebase.firestore
                val almacenRef1 = db1.collection("almacen").document(almacenId)
                val nuevoNombre = txtNombre.text.toString()
                val nuevoPrecio = txtPrecio.text.toString().toDouble()
                val estaAbierto = chkIsOpen.isChecked

                val nuevosDatos = hashMapOf(
                    "storeName" to nuevoNombre,
                    "storeValue" to nuevoPrecio,
                    "isOpen" to estaAbierto
                )

                almacenRef1.update(nuevosDatos as Map<String, Any>)
                    .addOnSuccessListener {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener { }
            }
        }
    }
}