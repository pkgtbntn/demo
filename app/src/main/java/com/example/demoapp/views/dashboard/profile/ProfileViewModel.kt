package com.example.demoapp.views.dashboard.profile

import androidx.lifecycle.*
import com.example.demoapp.db.profile.Profile
import com.example.demoapp.domain.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val inputPhone = MutableLiveData<String>()

    val saveButton = MutableLiveData<String>()
    val clearButton = MutableLiveData<String>()

    val profileData = repository.profile

    init {
        saveButton.value = "Save"
        clearButton.value = "Clear All"

        profileData.observeForever { profile: Profile? ->
            inputPhone.value = profile?.phoneNumber
            inputEmail.value = profile?.email
            inputName.value = profile?.name
        }
    }

    fun clearFields(){
        inputName.value = ""
        inputEmail.value = ""
        inputPhone.value = ""
    }

    fun saveProfile() {
        val name = inputName.value!!
        val email = inputEmail.value!!
        val phone = inputPhone.value!!
        insertProfile(Profile(0, name, phone, email))
    }

    private fun insertProfile(profile: Profile) {
        viewModelScope.launch {
            repository.insert(profile)
        }
    }

}