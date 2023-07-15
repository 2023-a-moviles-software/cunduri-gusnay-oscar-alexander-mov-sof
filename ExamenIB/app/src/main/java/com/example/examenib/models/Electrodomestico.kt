package com.example.examenib.models

class Electrodomestico(
    var productID: Int?,
    var productName: String,
    var price: Double,
    var cantidad: Int,
    var productType: Char?,
    var isAvailable: Boolean?,
) {

    init {
        if(this.productID == null){
            this.productID = setId()
        }else{
            this.productID
        }
        this.productName
        this.price
        this.cantidad
        this.productType = this.productName[0]
        this.isAvailable = this.cantidad > 0
    }

    companion object{
        var idCounter = 0
    }

    private fun setId():Int{
        idCounter++
        return idCounter
    }
}