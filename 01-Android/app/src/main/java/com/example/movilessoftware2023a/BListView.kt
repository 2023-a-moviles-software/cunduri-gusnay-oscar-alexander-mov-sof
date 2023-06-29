package com.example.movilessoftware2023a

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class BListView : AppCompatActivity() {

    val arreglo = BBaseDatosMemoria.arregloBEntrnador
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        //Adaptador, se usa para iterables

        val listView = findViewById<ListView>(R.id.lv_list_view)

        val adaptador = ArrayAdapter(
            this, //Conxtext
            android.R.layout.simple_list_item_1, //layout.xml que va a usar
            arreglo
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonListView = findViewById<Button>(R.id.btn_anadir_list_view)

        botonListView.setOnClickListener {
            añadirEntrenador(adaptador)
        }

        registerForContextMenu(listView)
    }

    fun añadirEntrenador(adaptador: ArrayAdapter<Bentrenador>) {
        arreglo.add(
            Bentrenador(4, "Pepin", "Desc 4")
        )
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater

        inflater.inflate(R.menu.menu, menu)

        val infor = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = infor.position

        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                return true
            }

            R.id.mi_eliminar -> {
                abrirDialogo()
                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
            }
        )
        builder.setNegativeButton("Cancelar", null)

        val opcinoes = resources.getStringArray(
            R.array.string_array_opciones
        )

        val seleccionPrevia = booleanArrayOf(
            true,
            false,
            false
        )

        builder.setMultiChoiceItems(
            opcinoes,
            seleccionPrevia,
            { dialog, which, isChecked ->
                "Dio click en el item ${which}"
            }
        )

        val dialogo = builder.create()
        dialogo.show()
    }


}