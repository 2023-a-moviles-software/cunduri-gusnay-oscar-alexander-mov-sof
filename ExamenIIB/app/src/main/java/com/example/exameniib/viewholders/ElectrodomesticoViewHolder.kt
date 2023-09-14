package com.example.exameniib.viewholders

import android.annotation.SuppressLint
import android.content.Intent
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exameniib.ActualizarAlmacen
import com.example.exameniib.ActualizarEletrodomestico
import com.example.exameniib.R
import com.example.exameniib.adapters.AlmacenAdapter
import com.example.exameniib.adapters.ElectrodomesticoAdapter
import com.example.exameniib.models.Almacen
import com.example.exameniib.models.Electrodomestico
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ElectrodomesticoViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    val txtNombreProducto = view.findViewById<TextView>(R.id.txt_nombre_producto)
    val txtCantidad = view.findViewById<TextView>(R.id.txt_cantidad)
    val txtprecio = view.findViewById<TextView>(R.id.txt_precio_producto)
    val txtCategoria = view.findViewById<TextView>(R.id.txt_categoria)
    private var currentElectrodomestico: Electrodomestico? = null
    private val arreglo: ArrayList<Electrodomestico> = arrayListOf()
    init {
        view.setOnCreateContextMenuListener(this)
    }

    companion object {
        private lateinit var adapter: ElectrodomesticoAdapter


        fun setAdapter(electroAdapter: ElectrodomesticoAdapter) {
            adapter = electroAdapter
        }

    }

    @SuppressLint("SetTextI18n")
    fun render(electrodomestico: Electrodomestico) {
        currentElectrodomestico = electrodomestico
        txtNombreProducto.text = electrodomestico.productName
        txtCantidad.text = "Cantidad: ${electrodomestico.cantidad}"
        txtprecio.text = electrodomestico.price.toString()
        txtCategoria.text = "Categoria ${electrodomestico.productType}"
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = MenuInflater(v?.context)
        inflater.inflate(R.menu.menu_producto, menu)

        if (menu != null) {
            for (i in 0 until menu.size()) {
                menu.getItem(i).setOnMenuItemClickListener(this)
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editar_producto_tool -> {
                val idAux = currentElectrodomestico?.id
                val idAlmacen = currentElectrodomestico?.almacenId
                val intent = Intent(itemView.context, ActualizarEletrodomestico::class.java)
                intent.putExtra("idProducto", idAux)
                intent.putExtra("idAlmacen", idAlmacen)
                itemView.context.startActivity(intent)
                true
            }

            R.id.eliminar_producto_tool -> {
                val elecID = currentElectrodomestico?.id
                eliminarAlmacenEnFirestore(elecID)
                consultarElectrodomesticosPorAlmacen(currentElectrodomestico?.almacenId)
                adapter.updateData(arreglo)
                true
            }

            else -> false
        }
    }

    private fun eliminarAlmacenEnFirestore(electroID: String?) {
        if (electroID != null) {
            val db = Firebase.firestore
            db.collection("electrodomesticos")
                .document(electroID)
                .delete()
                .addOnSuccessListener {

                }
                .addOnFailureListener { }
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