package com.example.demoapp.domain

import com.example.demoapp.reqres.ReqresDatasource

class DemoRepository constructor(private val reqresDatasource: ReqresDatasource) {
    fun getProducts() = reqresDatasource.getProducts()
}