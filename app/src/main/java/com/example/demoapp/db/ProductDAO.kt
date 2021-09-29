package com.example.demoapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {

    @Insert
    suspend fun insertProduct(product: Product) : Long

    @Query("SELECT * FROM product_data_table")
    fun getAllProducts(): Flow<List<Product>>

    @Query("DELETE FROM product_data_table")
    suspend fun deleteAll() : Int


}