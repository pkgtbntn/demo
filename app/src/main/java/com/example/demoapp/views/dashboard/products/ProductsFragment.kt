package com.example.demoapp.views.dashboard.products

import android.os.Bundle
import android.util.Log
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProductsBinding
import com.example.demoapp.domain.DemoRepository
import com.example.demoapp.reqres.ReqresDatasource
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import com.example.demoapp.utils.mvvm.ProductViewModelFactory
import com.example.demoapp.views.dashboard.products.adapter.ProductsAdapter

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding

    lateinit var viewModel: ProductsViewModel

    private val retrofitService = ReqresDatasource.getInstance()

    /** View Methods **/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_products, container, false)
        viewModel = ViewModelProvider(this, ProductViewModelFactory(DemoRepository(retrofitService))).get(ProductsViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = ProductsAdapter(ProductsAdapter.OnClickListener{
            viewModel.displayProductDetails(it)
        })

        viewModel.selectedProduct.observe(this, Observer {
            if (null != it) {
                val bundle = bundleOf("product" to it)
                findNavController().navigate(R.id.action_dashboardFragment_to_productViewFragment, bundle)
                viewModel.displayProductsDetailsComplete()
            }
        })

        binding.rcvProductList.adapter = adapter

        viewModel.productList.observe(this, Observer {
            adapter.setProductsList(it)
        })

        viewModel.errorMessage.observe(this, Observer {

        })

        return binding.root
    }
}