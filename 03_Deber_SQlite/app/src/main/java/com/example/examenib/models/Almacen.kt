package com.example.examenib.models

class Almacen(
    val id: Int?,
    var storeName: String?,
    val storeLocation: String?,
    var isOpen: Boolean,
    var storeValue: Double
) {
    init {
        this.id
        this.storeName
        this.storeLocation
        this.isOpen
        this.storeValue
    }

}