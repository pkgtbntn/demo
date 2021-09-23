package com.example.demoapp.utils.ext

import androidx.recyclerview.widget.RecyclerView

/**
 * Set [layoutManager] and a [recyclerViewAdapter]
 */
fun RecyclerView.setup(layoutManager: RecyclerView.LayoutManager, recyclerViewAdapter: RecyclerView.Adapter<*>?) {
    this.layoutManager = layoutManager
    adapter = recyclerViewAdapter
}
