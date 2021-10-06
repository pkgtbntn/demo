package com.example.demoapp.views.dashboard.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.demoapp.domain.DemoProductRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CartViewModel constructor(private val repository: DemoProductRepository) : ViewModel(){

    fun getAddedProducts() = liveData {
        repository.products.collect {
            emit(it)
        }
    }

    fun checkout(){
        viewModelScope.launch{
            repository.deleteAll()
        }
    }

    fun getTotalExpense() = liveData {
        repository.sum.collect {
            emit(it)
        }
    }
}