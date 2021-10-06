package com.example.demoapp.db.profile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoapp.db.product.Product
import com.example.demoapp.db.product.ProductDAO

@Database(entities = [Profile::class], version = 1)
abstract class ProfileDatabase : RoomDatabase() {
    abstract val profileDAO: ProfileDAO

    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null
        fun getInstance(context: Context): ProfileDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProfileDatabase::class.java,
                        "profile_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}
