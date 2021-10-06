package com.example.demoapp.views.dashboard.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.databinding.RcvItemCartBinding
import com.example.demoapp.db.product.Product
import com.example.demoapp.reqres.model.products.ProductsResponseItem

class CartAdapter : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var productsList = mutableListOf<Product>()

    fun setCartList (products: List<Product>) {
        this.productsList = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(productsList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RcvItemCartBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = productsList.size

    inner class ViewHolder(val binding: RcvItemCartBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product) {
            binding.productItem = product
        }
    }

}
