package com.example.demoapp.views.dashboard.profile

import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.demoapp.R
import com.example.demoapp.databinding.FragmentProfileBinding
import com.example.demoapp.db.profile.Profile
import com.example.demoapp.db.profile.ProfileDatabase
import com.example.demoapp.domain.ProfileRepository
import com.example.demoapp.utils.coroutines.SchedulerProvider
import com.example.demoapp.utils.mvvm.ProfileViewModelFactory
import kotlinx.coroutines.flow.*

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    /** View Methods **/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile, container, false)
        val dao = ProfileDatabase.getInstance(requireContext()).profileDAO
        val repository = ProfileRepository(dao)
        val factory = ProfileViewModelFactory(repository)
        profileViewModel = ViewModelProvider(this,factory).get(ProfileViewModel::class.java)
        binding.viewModel = profileViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}