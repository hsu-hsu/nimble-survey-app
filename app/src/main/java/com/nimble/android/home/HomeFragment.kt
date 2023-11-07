package com.nimble.android.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nimble.android.R
import com.nimble.android.databinding.FragmentHomeBinding
import com.nimble.android.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding by autoCleared()
    private val viewModel: HomeViewModel by viewModels()
    private var adapter: SurveyListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        initializeViewModel()
        fetchData()
        initializeAdapter()
        observeViewModel()
    }

    private fun initializeViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeViewModel = viewModel
    }

    private fun observeViewModel() {
        viewModel.survey.observe(viewLifecycleOwner) { surveys ->
            surveys?.let {
                binding.shimmerLayout.stopShimmer()
                adapter.submitList(surveys.data)
                binding.homePager.adapter = adapter
                binding.homePager.visibility = View.VISIBLE
                binding.shimmerLayout.visibility = View.GONE
                binding.dotsIndicator.attachTo(binding.homePager)
            }

        }

        viewModel.navigateToDetailFragment.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
                viewModel.onDetailFragmentNavigate()
            }
        }
    }

    private fun initializeAdapter() {
        adapter = SurveyListAdapter(SurveyListAdapter.OnClickListener { survey ->
            viewModel.onSurveyItemClick(survey)
        })
    }

    private fun fetchData() {
        binding.shimmerLayout.startShimmer()
        val token = HomeFragmentArgs.fromBundle(requireArguments()).token
        viewModel.getTokenFromLogin(token)
    }
}