package com.example.demoapp.views.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.demoapp.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import com.example.demoapp.views.dashboard.cart.CartFragment
import com.example.demoapp.views.dashboard.offers.OffersFragment
import com.example.demoapp.views.dashboard.products.ProductsFragment
import com.example.demoapp.views.dashboard.profile.ProfileFragment

class DashboardFragment : Fragment() {

    /** View Methods **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_dashboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    /** Private Methods **/
    private fun initUI(){
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, ProductsFragment())?.commit()

        bottom_navigation_view.setOnItemSelectedListener {
            var selectedFragment: Fragment? = null
            when (it.itemId) {
                R.id.products -> selectedFragment = ProductsFragment()
                R.id.cart -> selectedFragment = CartFragment()
                R.id.offers -> selectedFragment = OffersFragment()
                R.id.profile -> selectedFragment = ProfileFragment()
            }
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container, selectedFragment ?: Fragment())
                ?.commit()
            true
        }
    }
}