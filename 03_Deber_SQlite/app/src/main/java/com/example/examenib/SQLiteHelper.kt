package com.example.examenib

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.examenib.models.Almacen
import com.example.examenib.models.Electrodomestico
import com.example.examenib.models.ElectrodomesticoYAlmacen

class SQLiteHelper(context: Context) : SQLiteOpenHelper(
    context,
    "almacen.db",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val tablaAlmacen = "CREATE TABLE almacen (" +
                "storeID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "storeName TEXT, " +
                "storeLocation TEXT, " +
                "isOpen INTEGER, " +
                "storeValue REAL" +
                ");"

        val tablaElectrodomesticos = "CREATE TABLE electrodomesticos (" +
                "productID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "productName TEXT, " +
                "price REAL, " +
                "cantidad INTEGER, " +
                "productType TEXT, " +
                "isAvailable INTEGER, " +
                "storeID INTEGER, " +
                "FOREIGN KEY(storeID) REFERENCES almacen(storeID)" +
                ");"

        db!!.execSQL(tablaAlmacen)
        db!!.execSQL(tablaElectrodomesticos)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado1 = "DROP TABLE IF EXISTS electrodomesticos"
        val ordenBorrado2 = "DROP TABLE IF EXISTS almacen"

        db!!.execSQL(ordenBorrado1)
        db!!.execSQL(ordenBorrado2)

        onCreate(db)
    }

    fun addAlmacen(almacen: Almacen) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("storeName", almacen.storeName)
        values.put("storeLocation", almacen.storeLocation)
        values.put("isOpen", if (almacen.isOpen) 1 else 0)
        values.put("storeValue", almacen.storeValue)

        // Insertar el registro en la tabla "almacenes"
        val newRowId = db.insert("almacen", null, values)
        db.close()

        if (newRowId != -1L) {
            // La inserción fue exitosa, newRowId contiene el ID de la fila recién insertada
            Log.d("SQLite", "Registro creado con ID: $newRowId")
        } else {
            // Ocurrió un error en la inserción
            Log.e("SQLite", "Error al insertar el registro en la base de datos")
        }
    }

    // Función para agregar un nuevo Electrodomestico
    fun addElectrodomestico(electrodomestico: Electrodomestico, almacenID: Int) {
        val values = ContentValues()
        val db = this.writableDatabase
        values.put("productName", electrodomestico.productName)
        values.put("price", electrodomestico.price)
        values.put("cantidad", electrodomestico.cantidad)
        values.put("productType", electrodomestico.productType.toString())
        values.put("isAvailable", if (electrodomestico.isAvailable == true) 1 else 0)
        values.put("storeID", almacenID)

        // Insertar el registro en la tabla "electrodomesticos"
        db.insert("electrodomesticos", null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllAlmacenes(): List<Almacen> {
        val almacenes = mutableListOf<Almacen>()
        val db = this.readableDatabase
        val query =
            "SELECT * FROM almacen" // El nombre de la tabla debe ser "almacen", no "almacenes"

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id =
                    cursor.getInt(cursor.getColumnIndex("storeID")) // El nombre de la columna es "storeID"
                val storeName = cursor.getString(cursor.getColumnIndex("storeName"))
                val storeLocation = cursor.getString(cursor.getColumnIndex("storeLocation"))
                val isOpen = cursor.getInt(cursor.getColumnIndex("isOpen")) == 1
                val storeValue = cursor.getDouble(cursor.getColumnIndex("storeValue"))

                val almacen = Almacen(id, storeName, storeLocation, isOpen, storeValue)
                almacenes.add(almacen)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return almacenes
    }

    fun deleteAlmacen(almacenId: Int) {
        val db = this.writableDatabase

        // Define la cláusula WHERE para seleccionar el almacén que deseas eliminar por su ID
        val whereClause = "storeID = ?"
        val whereArgs = arrayOf(almacenId.toString())
        val rowsDeleted = db.delete("almacen", whereClause, whereArgs)

        db.close()
    }

    fun updateAlmacen(id: Int, storeValue: Double, storeName: String, isOpen: Boolean) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("storeValue", storeValue)
        values.put("storeName", storeName)
        values.put("isOpen", if (isOpen) 1 else 0)
        db.update("almacen", values, "storeID = ?", arrayOf(id.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun getAlmacenById(id: Int): Almacen? {
        val db = this.readableDatabase
        val query = "SELECT * FROM almacen WHERE storeID = ?"
        val cursor = db.rawQuery(query, arrayOf(id.toString()))

        var almacen: Almacen? = null

        if (cursor.moveToFirst()) {
            val storeName = cursor.getString(cursor.getColumnIndex("storeName"))
            val storeLocation = cursor.getString(cursor.getColumnIndex("storeLocation"))
            val isOpen = cursor.getInt(cursor.getColumnIndex("isOpen")) == 1
            val storeValue = cursor.getDouble(cursor.getColumnIndex("storeValue"))

            almacen = Almacen(id, storeName, storeLocation, isOpen, storeValue)
        }

        cursor.close()
        db.close()

        return almacen
    }

    @SuppressLint("Range")
    fun getElectrodomesticosByAlmacenId(almacenId: Int?): List<Electrodomestico> {
        val electrodomesticos = mutableListOf<Electrodomestico>()
        val db = this.readableDatabase
        val query = "SELECT * FROM electrodomesticos WHERE storeID = ?"
        val cursor = db.rawQuery(query, arrayOf(almacenId.toString()))

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("productID"))
                val productName = cursor.getString(cursor.getColumnIndex("productName"))
                val price = cursor.getDouble(cursor.getColumnIndex("price"))
                val cantidad = cursor.getInt(cursor.getColumnIndex("cantidad"))
                val productType = cursor.getString(cursor.getColumnIndex("productType"))
                val isAvailable = cursor.getInt(cursor.getColumnIndex("isAvailable")) == 1

                val electrodomestico = Electrodomestico(id, productName, price, cantidad, productType, isAvailable)
                electrodomesticos.add(electrodomestico)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return electrodomesticos
    }

    fun addElectrodomesticoToAlmacen(electrodomestico: Electrodomestico, almacenId: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("productName", electrodomestico.productName)
        values.put("price", electrodomestico.price)
        values.put("cantidad", electrodomestico.cantidad)
        values.put("productType", electrodomestico.productType)
        values.put("isAvailable", if (electrodomestico.isAvailable == true) 1 else 0)
        values.put("storeID", almacenId)

        // Inserta el registro en la tabla "electrodomesticos"
        db.insert("electrodomesticos", null, values)
        db.close()
    }

    fun deleteElectrodomesticoInAlmacen(almacenID: Int, productoID: Int) {
        val db = this.writableDatabase
        val selection = "storeID = ? AND productID = ?"
        val selectionArgs = arrayOf(almacenID.toString(), productoID.toString())
        val deletedRows = db.delete("electrodomesticos", selection, selectionArgs)
        db.close()
    }

    fun updateElectrodomestico(
        productoID: Int,
        nuevoNombre: String,
        nuevoPrecio: Double,
        nuevaCantidad: Int
    ): Boolean{
        val db = this.writableDatabase
        val values = ContentValues()

        // Agrega los nuevos valores que deseas actualizar
        values.put("productName", nuevoNombre)
        values.put("price", nuevoPrecio)
        values.put("cantidad", nuevaCantidad)

        val selection = "productID = ?"
        val selectionArgs = arrayOf(productoID.toString())

        val updatedRows = db.update("electrodomesticos", values, selection, selectionArgs)

        db.close()

        return updatedRows > 0
    }


    @SuppressLint("Range")
    fun getElectrodomesticoYAlmacenById(electrodomesticoId: Int): ElectrodomesticoYAlmacen? {
        val db = this.readableDatabase
        var electrodomesticoYAlmacen: ElectrodomesticoYAlmacen? = null

        val query = "SELECT * FROM electrodomesticos WHERE productID = $electrodomesticoId"

        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val electrodomestico = Electrodomestico(
                productID = cursor.getInt(cursor.getColumnIndex("productID")),
                productName = cursor.getString(cursor.getColumnIndex("productName")),
                price = cursor.getDouble(cursor.getColumnIndex("price")),
                cantidad = cursor.getInt(cursor.getColumnIndex("cantidad")),
                productType = cursor.getString(cursor.getColumnIndex("productType")),
                isAvailable = cursor.getInt(cursor.getColumnIndex("isAvailable")) == 1,
            )
            val storeId = cursor.getInt(cursor.getColumnIndex("storeID"))
            electrodomesticoYAlmacen = ElectrodomesticoYAlmacen(electrodomestico, storeId)
        }
        cursor.close()
        return electrodomesticoYAlmacen
    }



}