import models.dao.AlmacenDAO
import models.dto.Almacen
import models.dto.Electrodomesticos
import java.io.File
import java.util.*


fun main(args: Array<String>) {

    menu()

}

fun menu() {
    val scanner = Scanner(System.`in`)
    val opcion: Int
    val almacenFile = File("src/main/almacenes.json")
    val almacenDAO = AlmacenDAO(almacenFile)

    val electrodomesticosFile = File("src/main/electrodomesticos.json")
    val electrodomesticosDAO = ElectrodomesticosDAO(electrodomesticosFile)

    do {
        println("========= CRUD ALMACEN ELECTRODOMESTICOS =========")
        println("Seleccione una opcion")
        println("1. Almacenes")
        println("2. Productos")
        val opcion: Int = scanner.nextInt()

        when (opcion) {
            1 -> {
                println(" ==== ALMACENES ==== ")
                almacenDAO.obtenerAlmacenes()
                println("1. Crear Almacen")
                println("2. Actualizar datos del Almacen")
                println("3. Agregar productos al Almacen")
                println("4. Eliminar almacen")

                val opcion2: Int = scanner.nextInt()

                when (opcion2) {
                    1 -> {
                        val id: Int
                        val nombre: String
                        val ubicacion: String
                        val precio: Double


                        println("Ingrese la id")
                        id = scanner.nextInt()
                        scanner.nextLine()

                        println("Ingrese el nombre")
                        nombre = scanner.nextLine()

                        println("Ingrese la ubicacion")
                        ubicacion = scanner.nextLine()

                        scanner.nextLine()

                        println("Ingrese el precio del almacen")
                        val precioString: String = scanner.nextLine()
                        precio = precioString.toDouble()


                        val almacen = Almacen(
                            storeID = id,
                            storeName = nombre,
                            storeLocation = ubicacion,
                            products = mutableListOf(),
                            isOpen = true,
                            storeValue = precio
                        )
                        almacenDAO.crearAlmacen(almacen)
                    }

                    2 -> {
                        scanner.nextLine()

                        println("Ingrese la id del almacen")
                        val idAlma: Int = scanner.nextInt()
                        val almacenEncontrado = almacenDAO.obtenerAlmacenPorID(idAlma)

                        scanner.nextLine()

                        println("Ingrese el nuevo nombre")
                        val nombre: String = scanner.nextLine()

                        println("Ingrese la nueva ubicacion")
                        val ubicacion: String = scanner.nextLine()


                        if (almacenEncontrado != null) {
                            val almacenActualizado = Almacen(
                                almacenEncontrado.storeID,
                                nombre,
                                ubicacion,
                                almacenEncontrado.products,
                                almacenEncontrado.isOpen,
                                almacenEncontrado.storeValue
                            )
                            almacenDAO.actualizarAlmacen(almacenActualizado)
                        }
                    }

                    3 -> {
                        println("Ingrese la id del almacen")
                        val idAlma: Int = scanner.nextInt()
                        scanner.nextLine()
                        val almacenEncontrado = almacenDAO.obtenerAlmacenPorID(idAlma)
                        val listaProductos: MutableList<Electrodomesticos> = if (almacenEncontrado != null) {
                            almacenDAO.getProducts(almacenEncontrado.storeID).toMutableList()
                        } else {
                            mutableListOf()
                        }

                        electrodomesticosDAO.obtenerElectrodomesticos()

                        println("Ingrese la id del producto que desea agregar")
                        val electId: Int = scanner.nextInt()
                        scanner.nextLine()

                        val electrodomestico = electrodomesticosDAO.obtenerElectrodomesticoPorID(electId)
                        if (electrodomestico != null) listaProductos.add(electrodomestico)

                        if (almacenEncontrado != null) {
                            val almacenActualizado = Almacen(
                                almacenEncontrado.storeID,
                                almacenEncontrado.storeName,
                                almacenEncontrado.storeLocation,
                                listaProductos,
                                almacenEncontrado.isOpen,
                                almacenEncontrado.storeValue
                            )
                            almacenDAO.actualizarAlmacen(almacenActualizado)
                        }
                    }

                    4 -> {
                        println("Ingrese la id del almacen que desea eliminar")
                        scanner.nextLine()
                        val idAlma: Int = scanner.nextInt()
                        almacenDAO.eliminarAlmacen(idAlma)
                    }

                    else -> println("Regresando al menú principal")
                }
            }

            2 -> {
                println(" ==== PRODUCTOS EXISTENTES ==== ")
                electrodomesticosDAO.obtenerElectrodomesticos()
                println("1. Crear Producto")
                println("2. Actualizar Producto")
                println("3. Eliminar producto")

                val opcion2: Int = scanner.nextInt()

                when (opcion2) {
                    1 -> {
                        println("Ingrese la id")
                        val id: Int = scanner.nextInt()
                        scanner.nextLine()
                        println("Ingrese el nombre")
                        val nombre = scanner.nextLine()
                        println("Ingrese el precio")
                        val precioString: String = scanner.nextLine()
                        val precio: Double = precioString.toDouble()


                        val electrodomestico = Electrodomesticos(
                            id, nombre, precio, true, nombre[0]
                        )
                        electrodomesticosDAO.crearElectrodomestico(electrodomestico)
                    }

                    2 -> {
                        println("Ingrese la id del producto")

                        val idProducto: Int = scanner.nextInt()
                        val productoEncontrado = electrodomesticosDAO.obtenerElectrodomesticoPorID(idProducto)

                        scanner.nextLine()

                        println("Ingrese el nuevo precio")
                        val precioString: String = scanner.nextLine()
                        val precio: Double = precioString.toDouble()

                        println("Sigue disponible? Y/N")
                        val aux: String = scanner.nextLine().toUpperCase()

                        var isAvailable: Boolean = true

                        if (aux == "Y") isAvailable = true
                        else if (aux == "N") isAvailable = false

                        if (productoEncontrado != null) {
                            val electroActualizado = Electrodomesticos(
                                productoEncontrado.productID,
                                productoEncontrado.productName,
                                precio,
                                isAvailable,
                                productoEncontrado.productType
                            )
                            electrodomesticosDAO.actualizarElectrodomestico(electroActualizado)
                        }
                    }

                    3 -> {
                        println("Ingrese la id del producto que desea eliminar")
                        scanner.nextLine()
                        val idProducto: Int = scanner.nextInt()
                        electrodomesticosDAO.eliminarElectrodomestico(
                            idProducto
                        )
                    }

                    else -> println("Regresando al menú principal")
                }
            }
        }

    } while (opcion == 1 || opcion == 2)

}