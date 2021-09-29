package com.example.demoapp.views.dashboard.products.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProductViewBinding
import com.example.demoapp.db.ProductDatabase
import com.example.demoapp.domain.DemoProductRepository
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import com.example.demoapp.utils.mvvm.ProductViewModelFactory
import kotlinx.android.synthetic.main.fragment_product_view.*

class ProductViewFragment : Fragment() {

    val data by lazy {
        arguments?.getSerializable("product") as ProductsResponseItem
    }

    private lateinit var binding: FragmentProductViewBinding
    private lateinit var productViewModel: ProductViewViewModel

    /** View Methods **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_view, container, false)
        val dao = ProductDatabase.getInstance(requireContext()).productDAO
        val repository = DemoProductRepository(dao)
        val factory = ProductViewModelFactory(repository)
        productViewModel = ViewModelProvider(this,factory).get(ProductViewViewModel::class.java)
        productViewModel.data = data
        binding.viewModel = productViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setupClickListeners()

        initUI()
    }

    /** Private Methods **/
    private fun initUI(){
        Glide.with(requireContext()).load(data.image).into(imv_item_image)
        txv_item_name.text = data.title
        txv_item_description.text = data.description
        txv_product_price.text = "Price: $${data.price}"
    }
//
//    private fun setupClickListeners(){
//        btn_add_cart.setOnClickListener{
//
//        }
//    }
}