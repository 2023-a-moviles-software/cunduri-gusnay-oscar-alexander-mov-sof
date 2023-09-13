package com.example.exameniib

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exameniib.adapters.AlmacenAdapter
import com.example.exameniib.models.Almacen
import com.example.exameniib.viewholders.AlmacenViewHolder
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AlmacenAdapter
    private val arreglo: ArrayList<Almacen> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_almacenes)
        adapter = AlmacenAdapter(arreglo)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        AlmacenViewHolder.setAdapter(adapter)

        consultarAlmacenes()


        val botonCrearAlmacen = findViewById<Button>(R.id.btn_crear_almacen)

        botonCrearAlmacen.setOnClickListener {
            val intent = Intent(this, CreateAlmacen::class.java)
            startActivity(intent)
        }
    }

    private fun consultarAlmacenes() {
        val db = Firebase.firestore
        val almacenRef = db.collection("almacen")

        almacenRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val almacenId = document.id
                    val storeName = document.getString("storeName")
                    val storeLocation = document.getString("storeLocation")
                    val isOpen = document.getBoolean("isOpen") ?: false
                    val storeValue = document.getDouble("storeValue") ?: 0.0

                    val nuevoAlmacen =
                        Almacen(almacenId, storeName, storeLocation, isOpen, storeValue)
                    arreglo.add(nuevoAlmacen)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { }
    }
}