package models.dto

class Electrodomesticos(
    val productID: Int,
    val productName: String,
    val price: Double,
    val isAvailable: Boolean,
    val productType: Char
) {
    init {
        this.productID
        this.productName
        this.price
        this.isAvailable
        this.productType
    }
}