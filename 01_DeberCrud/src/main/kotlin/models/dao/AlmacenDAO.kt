package models.dao

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import models.dto.Almacen
import models.dto.Electrodomesticos
import java.io.File

class AlmacenDAO(private val archivo: File) {
    private val gson: Gson = Gson()
    private var almacenes: MutableList<Almacen> = mutableListOf()

    init {
        if (archivo.exists()) {
            val json = archivo.readText()
            val tipoDatos = object : TypeToken<MutableList<Almacen>>() {}.type
            almacenes = gson.fromJson(json, tipoDatos) ?: mutableListOf()
        }
    }

    fun crearAlmacen(almacen: Almacen) {
        almacenes.add(almacen)
        guardarDatos()
    }

    fun obtenerAlmacenes() {
        imprimirListaAlmacenes(almacenes.toList())
    }

    fun obtenerAlmacenPorID(storeID: Int): Almacen? {
        return almacenes.find { it.storeID == storeID }
    }

    fun actualizarAlmacen(almacen: Almacen) {
        val index = almacenes.indexOfFirst { it.storeID == almacen.storeID }
        if (index != -1) {
            almacenes[index] = almacen
            guardarDatos()
        }
    }

    fun eliminarAlmacen(storeID: Int) {
        almacenes.removeAll { it.storeID == storeID }
        guardarDatos()
    }

    private fun guardarDatos() {
        val json = gson.toJson(almacenes)
        archivo.writeText(json)
    }

    fun getProducts(storeID: Int): List<Electrodomesticos> {
        val almacen = obtenerAlmacenPorID(storeID)
        return almacen?.products ?: emptyList()
    }

    fun imprimirAlmacen(almacenEncontrado: Almacen) {
        println("ID: ${almacenEncontrado.storeID}" + ", Nombre: ${almacenEncontrado.storeName}" + ", Ubicación: ${almacenEncontrado.storeLocation}" + ", Valor del almacén: ${almacenEncontrado.storeValue}")
        println("Lista de Productos")
        imprimirListaElectrodomesticos(almacenEncontrado.products)
        println("Abierto: ${almacenEncontrado.isOpen}")
        println()
    }

    fun imprimirListaAlmacenes(almacenes: List<Almacen>) {
        var i:Int = 1
        for (almacen in almacenes) {
            println("= Almacen $i")
            imprimirAlmacen(almacen)
            i++
        }
    }

    fun imprimirProducto(producto: Electrodomesticos) {
        println("\tID: ${producto.productID}" + ", Nombre: ${producto.productName}" + ", Precio: ${producto.price}" + ", Disponible: ${producto.isAvailable}" + ", Categoria: ${producto.productType}")
    }

    fun imprimirListaElectrodomesticos(electrodomesticosEncontrados: List<Electrodomesticos>) {
        if (electrodomesticosEncontrados.isNotEmpty()) {
            for (productoSele in electrodomesticosEncontrados) {
                imprimirProducto(productoSele)
            }
        } else {
            println("\tSin productos")
        }
    }

}