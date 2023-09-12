package com.example.examenib.models

class Electrodomestico(
    var productID: Int?,
    var productName: String,
    var price: Double,
    var cantidad: Int,
    var productType: String,
    var isAvailable: Boolean,
) {

    init {
        this.productID
        this.productName
        this.price
        this.cantidad
        this.productType = this.productName[0].toString()
        this.isAvailable = this.cantidad > 0
    }

}