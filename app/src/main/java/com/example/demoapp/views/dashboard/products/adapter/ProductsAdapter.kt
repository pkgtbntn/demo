package com.example.demoapp.views.dashboard.products.adapter

import android.util.Log.e
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.databinding.RcvItemProductsBinding
import com.example.demoapp.generated.callback.OnClickListener
import com.example.demoapp.reqres.model.products.ProductsResponseItem


class ProductsAdapter(val onClickListener: OnClickListener) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    var products = mutableListOf<ProductsResponseItem>()

    fun setProductsList (products: List<ProductsResponseItem>) {
        this.products = products.toMutableList()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(products[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RcvItemProductsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    inner class ViewHolder(val binding: RcvItemProductsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductsResponseItem) {
            binding.productItem = product
            binding.root.setOnClickListener {
                onClickListener.onClick(product)
            }
        }
    }

    class OnClickListener(val clickListener: (product: ProductsResponseItem) -> Unit) {
        fun onClick(product: ProductsResponseItem) {
            return clickListener(product)
        }
    }
}
