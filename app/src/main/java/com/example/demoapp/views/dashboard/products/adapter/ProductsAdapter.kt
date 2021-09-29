package com.example.demoapp.views.dashboard.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.R
import com.example.demoapp.databinding.RcvItemProductsBinding
import com.example.demoapp.db.Product
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rcv_item_products.view.*


class ProductsAdapter(
    private val products: List<ProductsResponseItem>, private val onSelect: (ProductsResponseItem?) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(products[position], onSelect)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val applicationBinding = RcvItemProductsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(applicationBinding)
    }

    override fun getItemCount(): Int = products.size

    inner class ViewHolder(val binding: RcvItemProductsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductsResponseItem, onSelect: (ProductsResponseItem?) -> Unit) {
            binding.productItem = product
            binding.root.setOnClickListener {
                onSelect(product)
            }
        }
    }
}

@BindingAdapter("urlImage")
fun bindUrlImage(view: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        Glide.with(view.context).load(imageUrl).into(view)
    } else {
        view.setImageBitmap(null)
    }
}