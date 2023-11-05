package com.nimble.android.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.nimble.android.R
import com.nimble.android.databinding.FragmentHomeBinding
import com.nimble.android.databinding.FragmentLoginBinding
import com.nimble.android.login.LoginViewModel
import com.nimble.android.login.LoginViewModelFactory

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = HomeViewModelFactory(application)
        val homeViewModel = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
        binding.homeViewModel = homeViewModel
        binding.shimmerLayout.startShimmer()
        val token = HomeFragmentArgs.fromBundle(requireArguments()).token
        homeViewModel.getTokenFromLogin(token)
        val adapter = SurveyListAdapter(SurveyListAdapter.OnClickListener { surveyId ->

        })
        homeViewModel.survey.observe(viewLifecycleOwner, { surveys->
            surveys?.let {
                binding.shimmerLayout.stopShimmer()
                adapter.submitList(surveys.data)
                binding.homePager.adapter = adapter
                binding.homePager.visibility = View.VISIBLE
                binding.shimmerLayout.visibility = View.GONE
            }

        })
        return binding.root
    }
}