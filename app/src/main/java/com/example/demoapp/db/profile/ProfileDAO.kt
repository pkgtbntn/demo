package com.example.demoapp.db.profile

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.demoapp.db.product.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDAO {

    @Insert
    suspend fun insertProfile(profile: Profile) : Long

    @Query("SELECT * FROM profile_data_table")
    fun getProfile(): Flow<List<Profile>>

    @Query("DELETE FROM profile_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM profile_data_table ORDER BY profile_id DESC LIMIT 1")
    fun provideProfile() : LiveData<Profile?>

    @Update
    suspend fun updateProfile(profile: Profile) : Int
}