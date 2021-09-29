package com.example.demoapp.views.dashboard.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demoapp.domain.DemoRepository
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import com.example.demoapp.utils.coroutines.SchedulerProvider
import com.example.demoapp.utils.coroutines.with
import com.example.demoapp.utils.mvvm.RxViewModel
import com.example.demoapp.utils.mvvm.SingleLiveEvent

class ProductsViewModel(
    private val demoRepository: DemoRepository,
    private val schedulerProvider: SchedulerProvider
) : RxViewModel() {

    private val _state = MutableLiveData<ProductsState>()
    val state: LiveData<ProductsState> get() = _state

    private val _event = SingleLiveEvent<ProductsEvent>()
    val event: LiveData<ProductsEvent> get() = _event

    fun loadProducts() {
        _event.value = ProductsEvent.Pending
        launch {
            demoRepository.getProducts().with(schedulerProvider)
                .subscribe(
                    { products ->
                        _event.value = ProductsEvent.LoadProductsSuccess
                        _state.value = _state.value?.copy(products = products)
                            ?: ProductsState(products = products)
                    },
                    { error -> _event.value = ProductsEvent.LoadProductsFailed(error) })
        }
    }
}

data class ProductsState(
    val products: List<ProductsResponseItem> = emptyList()
)

sealed class ProductsEvent {
    object Pending : ProductsEvent()
    object LoadProductsSuccess : ProductsEvent()
    data class LoadProductsFailed(val error: Throwable) : ProductsEvent()
}
