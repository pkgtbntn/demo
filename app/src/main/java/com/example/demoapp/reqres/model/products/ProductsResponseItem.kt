package com.example.demoapp.reqres.model.products

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import java.io.Serializable

data class ProductsResponseItem(
    val category: String? = null,
    val description: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val price: Double? = null,
    val rating: Rating? = null,
    val title: String? = null
) : Serializable