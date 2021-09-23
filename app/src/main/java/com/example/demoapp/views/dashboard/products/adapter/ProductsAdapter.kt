package com.example.demoapp.views.dashboard.products.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.R
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import com.example.demoapp.utils.ext.inflate
import kotlinx.android.synthetic.main.rcv_item_products.view.*

class ProductsAdapter(
    private val context: Context,
    private val products: List<ProductsResponseItem>,
    private val itemClickHandler: ((ProductsResponseItem) -> Unit)
) : RecyclerView.Adapter<ProductsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(parent.inflate(R.layout.rcv_item_products, false))

    override fun onBindViewHolder(holder: ProductsAdapter.Holder, position: Int) =
        holder.bind(products[position])

    override fun getItemCount() = products.size

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: ProductsResponseItem) {
            itemView.apply {
                with(product) {
                    Glide.with(context).load(image).into(imv_product);
                    txv_product_item.text = title
                    setOnClickListener {
                        itemClickHandler(product)
                    }
                }
            }
        }
    }

}