package com.example.todolist.model

import android.os.Parcel
import android.os.Parcelable

data class TakeHomeModel(
        val pageNumber: Int,
        val pageSize: Int,
        val products: List<Product>,
        val statusCode: Int,
        val totalProducts: Int)

 class Product() : Parcelable {
    var inStock: Boolean? = null
    var longDescription: String? = null
    var price: String? = null
    var productId: String? = null
    var productImage: String? = null
    var productName: String? = null
    var reviewCount: Int? = null
    var reviewRating: Double? = null
    var shortDescription: String? = null

    constructor(parcel: Parcel) : this() {
        inStock = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        longDescription = parcel.readString()
        price = parcel.readString()
        productId = parcel.readString()
        productImage = parcel.readString()
        productName = parcel.readString()
        reviewCount = parcel.readValue(Int::class.java.classLoader) as? Int
        reviewRating = parcel.readValue(Double::class.java.classLoader) as? Double
        shortDescription = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(inStock)
        parcel.writeString(longDescription)
        parcel.writeString(price)
        parcel.writeString(productId)
        parcel.writeString(productImage)
        parcel.writeString(productName)
        parcel.writeValue(reviewCount)
        parcel.writeValue(reviewRating)
        parcel.writeString(shortDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}
