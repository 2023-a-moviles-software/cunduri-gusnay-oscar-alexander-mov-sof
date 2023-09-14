package com.example.exameniib

import android.R.id
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exameniib.adapters.ElectrodomesticoAdapter
import com.example.exameniib.models.Electrodomestico
import com.example.exameniib.viewholders.ElectrodomesticoViewHolder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListaProductos : AppCompatActivity() {

    private lateinit var adapter: ElectrodomesticoAdapter
    private val arreglo: ArrayList<Electrodomestico> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_productos)

        val nombreAlmacen = intent.getStringExtra("nombre")
        val idAlmacenStr = intent.getStringExtra("idAProducto")

        consultarElectrodomesticosPorAlmacen(idAlmacenStr)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_lista_productos)
        adapter = ElectrodomesticoAdapter(arreglo)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        ElectrodomesticoViewHolder.setAdapter(adapter)

        val tb = findViewById<TextView>(R.id.txttest)
        tb.text = "Almacen"

        val btnCrearProducto = findViewById<Button>(R.id.btn_crear_nuevo_producto)


        btnCrearProducto.setOnClickListener {
            val intent = Intent(this, CreateElectrodomestico::class.java)
            intent.putExtra("idAlmacenStr", idAlmacenStr)
            startActivity(intent)

        }
    }

    private fun consultarElectrodomesticosPorAlmacen(almacenId: String?) {
        val db = Firebase.firestore
        val electrodomesticosRef = db.collection("electrodomesticos")
            .whereEqualTo("almacenId", almacenId)

        electrodomesticosRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val electrodomesticoId = document.id
                    val productName = document.getString("productName")
                    val productType = document.getString("productType")
                    val price = document.getDouble("price") ?: 0.0
                    val cantidad = document.getLong("cantidad")?.toInt() ?: 0

                    val nuevoElectrodomestico =
                        Electrodomestico(electrodomesticoId, productName, productType, price, cantidad, almacenId)
                    arreglo.add(nuevoElectrodomestico)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { }
    }



}