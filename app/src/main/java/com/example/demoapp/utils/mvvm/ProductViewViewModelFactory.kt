package com.example.demoapp.utils.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.domain.DemoProductRepository
import com.example.demoapp.domain.DemoRepository
import com.example.demoapp.domain.ProfileRepository
import com.example.demoapp.views.dashboard.products.product.ProductViewViewModel

class ProductViewViewModelFactory(
    private val repository: DemoProductRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductViewViewModel::class.java)){
            return ProductViewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}