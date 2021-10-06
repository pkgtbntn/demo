package com.example.demoapp.views.dashboard.products.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProductViewBinding
import com.example.demoapp.db.product.ProductDatabase
import com.example.demoapp.domain.DemoProductRepository
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import com.example.demoapp.utils.mvvm.ProductViewViewModelFactory
class ProductViewFragment : Fragment() {

    val data by lazy {
        arguments?.getSerializable("product") as ProductsResponseItem
    }

    private lateinit var binding: FragmentProductViewBinding
    private lateinit var productViewModel: ProductViewViewModel

    /** View Methods **/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_view, container, false)

        val dao = ProductDatabase.getInstance(requireContext()).productDAO
        val repository = DemoProductRepository(dao)
        val factory = ProductViewViewModelFactory(repository)

        productViewModel = ViewModelProvider(this, factory).get(ProductViewViewModel::class.java)
        productViewModel.data = data

        binding.viewModel = productViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}