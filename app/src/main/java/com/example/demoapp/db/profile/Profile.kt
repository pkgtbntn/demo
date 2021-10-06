package com.example.demoapp.db.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_data_table")
data class Profile (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profile_id")
    val id: Int,

    @ColumnInfo(name = "profile_name")
    val name: String,

    @ColumnInfo(name = "profile_phone_number")
    val phoneNumber: String,

    @ColumnInfo(name = "profile_email")
    val email: String
)