package com.example.demoapp.reqres.model.products

import java.io.Serializable

data class ProductsResponseItem(
    var category: String? = null,
    var description: String? = null,
    var id: Int? = null,
    var image: String? = null,
    var price: Double? = null,
    var rating: Rating? = null,
    var title: String? = null
) : Serializable