package com.example.demoapp.views.dashboard.products.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.demoapp.R
import com.example.demoapp.reqres.model.products.ProductsResponseItem
import kotlinx.android.synthetic.main.fragment_product_view.*

class ProductViewFragment : Fragment() {

    private val data by lazy {
        arguments?.getSerializable("product") as ProductsResponseItem
    }

    /** View Methods **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_product_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        setupClickListeners()
    }

    /** Private Methods **/
    private fun initUI(){
        Glide.with(requireContext()).load(data.image).into(imv_item_image)
        txv_item_name.text = data.title
        txv_item_description.text = data.description
        txv_product_price.text = "Price: $${data.price}"
    }

    private fun setupClickListeners(){
        btn_add_cart.setOnClickListener{

        }
    }
}