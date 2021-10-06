package com.example.demoapp.domain

import com.example.demoapp.db.product.Product
import com.example.demoapp.db.product.ProductDAO

class DemoProductRepository(private val dao: ProductDAO) {

    val products = dao.getAllProducts()

    val sum = dao.getSum()

    suspend fun insert(product: Product): Long {
        return dao.insertProduct(product)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAll()
    }
}
