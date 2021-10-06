package com.example.demoapp.db.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_data_table")
data class Product (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "product_id")
    val id: Int,

    @ColumnInfo(name = "product_title")
    val title: String,

    @ColumnInfo(name = "product_image")
    val image: String,

    @ColumnInfo(name = "product_description")
    val description: String,

    @ColumnInfo(name = "product_price")
    val price: Double
)