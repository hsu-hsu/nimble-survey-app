package com.nimble.android.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.snackbar.Snackbar
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
        setUpViewPager()
        initializeViewModel()
        fetchData()
        observeViewModel()
    }

    private fun setUpViewPager() {
        adapter = SurveyListAdapter(SurveyListAdapter.OnClickListener { survey ->
            viewModel.onSurveyItemClick(survey)
        })
        binding.homePager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.homePager)
        binding.homePager.registerOnPageChangeCallback( object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val survey = adapter.currentList[position]
                binding.titleText.text = survey.attributes.title
                binding.detailText.text = survey.attributes.description

                if(position == adapter.currentList.size - 1) {
                    displaySnackBarForLoadMoreAction(position)
                }
            }
        })
    }

    private fun displaySnackBarForLoadMoreAction(position: Int) {
        Snackbar.make(
            binding.root,
            R.string.ask_to_load_more_survey,
            Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.load_more){

        }
            .show()

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
                binding.homePager.visibility = View.VISIBLE
                binding.continueFab.visibility = View.VISIBLE
                binding.dotsIndicator.visibility = View.VISIBLE
                binding.shimmerLayout.visibility = View.GONE
            }

        }

        viewModel.navigateToDetailFragment.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        it
                    )
                )
                viewModel.onDetailFragmentNavigate()
            }
        }
    }

    private fun fetchData() {
        binding.shimmerLayout.startShimmer()
        viewModel.fetchSurveysList()
    }
}