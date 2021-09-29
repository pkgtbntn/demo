package com.example.demoapp.domain

import com.example.demoapp.db.Product
import com.example.demoapp.db.ProductDAO

class DemoProductRepository(private val dao: ProductDAO) {

    val products = dao.getAllProducts()

    suspend fun insert(product: Product): Long {
        return dao.insertProduct(product)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAll()
    }
}
