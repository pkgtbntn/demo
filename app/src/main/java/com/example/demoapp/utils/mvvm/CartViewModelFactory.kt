package com.example.demoapp.utils.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.domain.DemoProductRepository
import com.example.demoapp.views.dashboard.cart.CartViewModel

class CartViewModelFactory constructor(private val repository: DemoProductRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            CartViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}