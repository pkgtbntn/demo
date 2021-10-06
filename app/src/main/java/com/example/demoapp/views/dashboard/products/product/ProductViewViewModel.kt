package com.example.demoapp.views.dashboard.products.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.db.product.Product
import com.example.demoapp.domain.DemoProductRepository
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import kotlinx.coroutines.launch

class ProductViewViewModel(private val repository: DemoProductRepository) : ViewModel(){

    var data = ProductsResponseItem()

    var status = MutableLiveData<Boolean?>()

    fun saveProduct(){
        val title = data.title
        val description = data.description
        val image = data.image
        val price = data.price

        insertProduct(Product(0, title!!, image!!, description!!, price!!))
    }

    private fun insertProduct(product: Product){
        viewModelScope.launch{
            repository.insert(product)
        }
    }
}

