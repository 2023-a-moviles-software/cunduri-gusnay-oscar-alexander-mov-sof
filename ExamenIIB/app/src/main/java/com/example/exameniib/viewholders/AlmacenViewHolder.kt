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
import com.example.exameniib.ListaProductos
import com.example.exameniib.R
import com.example.exameniib.adapters.AlmacenAdapter
import com.example.exameniib.models.Almacen
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AlmacenViewHolder(view: View) : RecyclerView.ViewHolder(view),
    View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

    private val nombreAlmacen = view.findViewById<TextView>(R.id.txt_name)
    private val ubicacionAlmacen = view.findViewById<TextView>(R.id.txt_Direccion)
    private val isOpenAlmacen = view.findViewById<TextView>(R.id.txt_isOpen)
    private var currentAlmacen: Almacen? = null
    private val arreglo: ArrayList<Almacen> = arrayListOf()


    init {
        view.setOnCreateContextMenuListener(this)
    }

    companion object {
        private lateinit var adapter: AlmacenAdapter


        fun setAdapter(almacenAdapter: AlmacenAdapter) {
            adapter = almacenAdapter
        }

    }

    @SuppressLint("SetTextI18n")
    fun render(almacen: Almacen) {
        currentAlmacen = almacen
        nombreAlmacen.text = almacen.storeName
        ubicacionAlmacen.text = almacen.storeLocation
        isOpenAlmacen.text = if (almacen.isOpen == true) "Abierto" else "Cerrado"

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = MenuInflater(v?.context)
        inflater.inflate(R.menu.menu_tools, menu)

        if (menu != null) {
            for (i in 0 until menu.size()) {
                menu.getItem(i).setOnMenuItemClickListener(this)
            }
        }
    }


    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ver_productos -> {
                 val idAux = currentAlmacen?.id
                 val nombreAlmacen = nombreAlmacen.text
                 val intent = Intent(itemView.context, ListaProductos::class.java)
                 intent.putExtra("idAProducto", idAux)
                 intent.putExtra("nombre", nombreAlmacen)
                 itemView.context.startActivity(intent)
                true
            }

            R.id.editar_almacen -> {
                val idAux = currentAlmacen?.id
                  val intent = Intent(itemView.context, ActualizarAlmacen::class.java)
                intent.putExtra("idAProducto", idAux)
                itemView.context.startActivity(intent)
                true
            }

            R.id.eliminar_almacen -> {
                currentAlmacen?.let { almacen ->
                    val almacenId = almacen.id
                    eliminarAlmacenEnFirestore(almacenId)
                    adapter.clearData()
                    consultarAlmacenes()
                    adapter.updateData(arreglo)
                }
                true
            }

            else -> false
        }
    }

    private fun eliminarAlmacenEnFirestore(almacenId: String?) {
        if (almacenId != null) {
            val db = Firebase.firestore
            db.collection("almacen")
                .document(almacenId)
                .delete()
                .addOnSuccessListener {

                }
                .addOnFailureListener { }
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

                    val nuevoAlmacen = Almacen(almacenId, storeName, storeLocation, isOpen, storeValue)
                    arreglo.add(nuevoAlmacen)
                }
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {  }
    }
}

