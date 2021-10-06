package com.example.demoapp.db.product

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoapp.db.profile.Profile
import com.example.demoapp.db.profile.ProfileDAO

@Database(entities = [Product::class, Profile::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract val productDAO: ProductDAO
    abstract val profileDAO: ProfileDAO

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null
        fun getInstance(context: Context): ProductDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDatabase::class.java,
                        "product_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}
