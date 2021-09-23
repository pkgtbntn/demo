package com.example.demoapp.domain

import android.util.Log.e
import com.example.demoapp.reqres.ReqresDatasource
import com.example.demoapp.reqres.model.products.ProductsResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Demo repository
 */
interface DemoRepository {
    fun getProducts(): Single<ProductsResponse>
}

/**
 * Demo repository implementation
 * Make use of ReqresDatasource & add some cache
 */
class DemoRepositoryImpl(
    private val reqresDatasource: ReqresDatasource
) : DemoRepository {

    override fun getProducts(): Single<ProductsResponse> {
        return reqresDatasource.getProducts().map {
            e("HELLO", it.body().toString())
            it.body()
        }
    }

}
