package com.example.demoapp.reqres

import com.example.demoapp.reqres.model.products.ProductsResponseItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

/**
 * Reqres datasource - Retrofit tagged
 */
interface ReqresDatasource {

    // BASE URL =========
//    companion object {
//        private const val URI_SCHEME = "https"
//
//        private const val URI_AUTHORITY_DEBUG = "fakestoreapi.com"
//        private const val URI_AUTHORITY_STAGING = "fakestoreapi.com"
//        private const val URI_AUTHORITY_RELEASE = "fakestoreapi.com"
//
//        val baseUrl
//            get() = Uri.Builder().apply {
//                scheme(URI_SCHEME)
//                authority(
//                    when (BuildConfig.BUILD_TYPE) {
//                        "debug" -> URI_AUTHORITY_DEBUG
//                        "staging" -> URI_AUTHORITY_STAGING
//                        "release" -> URI_AUTHORITY_RELEASE
//                        else -> URI_AUTHORITY_DEBUG
//                    }
//                )
//                appendPath("")
//            }.build().toString()
//    }

    companion object {

        var retrofitService: ReqresDatasource? = null

        fun getInstance() : ReqresDatasource {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://fakestoreapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ReqresDatasource::class.java)
            }
            return retrofitService!!
        }
    }

    // ENDPOINTS =========
    @GET("products")
    fun getProducts() : Call<List<ProductsResponseItem>>

}
