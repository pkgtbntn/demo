package com.example.demoapp.utils.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.domain.ProfileRepository
import com.example.demoapp.views.dashboard.profile.ProfileViewModel

class ProfileViewModelFactory(
    private val repository: ProfileRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}