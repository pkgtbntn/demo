package com.example.demoapp.domain

import androidx.lifecycle.LiveData
import com.example.demoapp.db.profile.Profile
import com.example.demoapp.db.profile.ProfileDAO
import kotlinx.coroutines.flow.Flow

class ProfileRepository(private val dao: ProfileDAO) {

    val profile: LiveData<Profile?>

    init {
        profile = dao.provideProfile()
    }

    suspend fun insert(profile: Profile): Long {
        return dao.insertProfile(profile)
    }

    suspend fun deleteAll() : Int {
        return dao.deleteAll()
    }
}
