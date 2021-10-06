package com.example.demoapp.views.dashboard.cart

import android.os.Bundle
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
import com.example.demoapp.databinding.FragmentCartBinding
import com.example.demoapp.databinding.FragmentProductsBinding
import com.example.demoapp.db.product.ProductDAO
import com.example.demoapp.db.product.ProductDatabase
import com.example.demoapp.db.profile.ProfileDatabase
import com.example.demoapp.domain.DemoProductRepository
import com.example.demoapp.domain.DemoRepository
import com.example.demoapp.utils.mvvm.CartViewModelFactory
import com.example.demoapp.utils.mvvm.ProductViewModelFactory
import com.example.demoapp.views.dashboard.cart.adapter.CartAdapter
import com.example.demoapp.views.dashboard.products.ProductsViewModel
import com.example.demoapp.views.dashboard.products.adapter.ProductsAdapter

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    lateinit var viewModel: CartViewModel

    private lateinit var adapter: CartAdapter

    /** View Methods **/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_cart, container, false)
        val dao = ProductDatabase.getInstance(requireContext()).productDAO

        val repository = DemoProductRepository(dao)

        viewModel = ViewModelProvider(this, CartViewModelFactory(repository)).get(CartViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = CartAdapter()
        binding.rcvCartList.adapter = adapter

        viewModel.getAddedProducts().observe(this, Observer {
            adapter.setCartList(it)
        })

        viewModel.getTotalExpense().observe(this, Observer {
            binding.textView2.text = "Total: $$it"
        })

        return binding.root
    }

}