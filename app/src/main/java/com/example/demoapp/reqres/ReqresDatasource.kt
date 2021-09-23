package com.example.demoapp.reqres

import android.net.Uri
import com.example.demoapp.BuildConfig
import com.example.demoapp.reqres.model.products.ProductsResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

/**
 * Reqres datasource - Retrofit tagged
 */
interface ReqresDatasource {

    // BASE URL =========
    companion object {
        private const val URI_SCHEME = "https"

        private const val URI_AUTHORITY_DEBUG = "fakestoreapi.com"
        private const val URI_AUTHORITY_STAGING = "fakestoreapi.com"
        private const val URI_AUTHORITY_RELEASE = "fakestoreapi.com"

        val baseUrl
            get() = Uri.Builder().apply {
                scheme(URI_SCHEME)
                authority(
                    when (BuildConfig.BUILD_TYPE) {
                        "debug" -> URI_AUTHORITY_DEBUG
                        "staging" -> URI_AUTHORITY_STAGING
                        "release" -> URI_AUTHORITY_RELEASE
                        else -> URI_AUTHORITY_DEBUG
                    }
                )
                appendPath("")
            }.build().toString()
    }

    // ENDPOINTS =========
    @GET("products")
    fun getProducts() : Single<Response<ProductsResponse>>

}
