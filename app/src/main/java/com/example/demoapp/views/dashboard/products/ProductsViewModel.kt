package com.example.demoapp.views.dashboard.products

import android.util.Log.e
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.demoapp.domain.DemoRepository

import androidx.lifecycle.ViewModel
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsViewModel constructor(private val repository: DemoRepository) : ViewModel() {

    val productList = MutableLiveData<List<ProductsResponseItem>>()
    val errorMessage = MutableLiveData<String>()

    private val _selectedProduct = MutableLiveData<ProductsResponseItem>()
    val selectedProduct: LiveData<ProductsResponseItem>
        get() = _selectedProduct

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        val response = repository.getProducts()
        response.enqueue(object : Callback<List<ProductsResponseItem>> {
            override fun onResponse(
                call: Call<List<ProductsResponseItem>>,
                response: Response<List<ProductsResponseItem>>
            ) {
                productList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<ProductsResponseItem>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun displayProductDetails(product : ProductsResponseItem){
        _selectedProduct.value = product
    }

    fun displayProductsDetailsComplete() {
        _selectedProduct.value = null
    }
}

open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let {
            onEventUnhandledContent(it)
        }
    }
}