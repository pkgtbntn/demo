package com.example.demoapp.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingUtils {

    companion object {

        //    @BindingAdapter("setAdapter")
//    fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
//        recyclerView.setHasFixedSize(true)
//        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
//        recyclerView.adapter = adapter
//    }
        @BindingAdapter("urlImage")
        @JvmStatic
        fun bindUrlImage(view: ImageView, imageUrl: String?) {
            if (imageUrl != null) {
                Glide.with(view.context).load(imageUrl).into(view)
            } else {
                view.setImageBitmap(null)
            }
        }
    }
}