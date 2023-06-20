import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import models.dto.Electrodomesticos
import java.io.File

class ElectrodomesticosDAO(private val archivo: File) {
    private val gson: Gson = Gson()
    private var electrodomesticos: MutableList<Electrodomesticos> = mutableListOf()

    init {
        if (archivo.exists()) {
            val json = archivo.readText()
            val tipoDatos = object : TypeToken<MutableList<Electrodomesticos>>() {}.type
            electrodomesticos = gson.fromJson(json, tipoDatos) ?: mutableListOf()
        }
    }

    fun crearElectrodomestico(electrodomestico: Electrodomesticos) {
        electrodomesticos.add(electrodomestico)
        guardarDatos()
    }

    fun obtenerElectrodomesticos() {
        imprimirListaElectrodomesticos(electrodomesticos.toList())
    }

    fun obtenerElectrodomesticoPorID(productID: Int): Electrodomesticos? {
        return electrodomesticos.find { it.productID == productID }
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

    fun actualizarElectrodomestico(electrodomestico: Electrodomesticos) {
        val index = electrodomesticos.indexOfFirst { it.productID == electrodomestico.productID }
        if (index != -1) {
            electrodomesticos[index] = electrodomestico
            guardarDatos()
        }
    }

    fun eliminarElectrodomestico(productID: Int) {
        electrodomesticos.removeAll { it.productID == productID }
        guardarDatos()
    }

    private fun guardarDatos() {
        val json = gson.toJson(electrodomesticos)
        archivo.writeText(json)
    }
}