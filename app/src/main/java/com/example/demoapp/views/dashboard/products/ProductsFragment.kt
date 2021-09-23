package com.example.demoapp.views.dashboard.products

import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.R
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import com.example.demoapp.utils.ext.setup
import com.example.demoapp.views.dashboard.products.adapter.ProductsAdapter
import com.example.demoapp.views.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.androidx.viewmodel.ext.sharedViewModel
import org.koin.androidx.viewmodel.ext.viewModel

class ProductsFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()
    private val viewModel: ProductsViewModel by viewModel()

    private lateinit var adapter: ProductsAdapter

    private val productsList = mutableListOf<ProductsResponseItem>()

    /** View Methods **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_products, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        initUI()
    }

    /** Private Methods **/
    private fun setupViewModel(){
        // event observer
        viewModel.event.observe(this, Observer { event ->
            when (event) {
                is ProductsEvent.Pending -> onPending()
                is ProductsEvent.LoadProductsSuccess -> onLoadProductsSuccess()
                is ProductsEvent.LoadProductsFailed -> onLoadProductsFailed(event.error)
            }
        })

        // state observer
        viewModel.state.observe(this, Observer { state -> setState(state) })

        viewModel.loadProducts()
    }

    private fun setState(newState: ProductsState) {
        with(newState) {
            productsList.apply { clear(); addAll(products) }
            adapter.notifyDataSetChanged()
        }
    }

    private fun onPending(){
        mainViewModel.startLoad()
    }

    private fun onLoadProductsSuccess(){
        mainViewModel.endLoad()
    }

    private fun onLoadProductsFailed(error: Throwable){
        mainViewModel.endLoad()
    }

    private fun initUI(){
        adapter = ProductsAdapter(requireContext(), productsList) {
            findNavController().navigate(R.id.action_dashboardFragment_to_productViewFragment)
        }
        rcv_product_list.setup(LinearLayoutManager(context), adapter)
    }
}