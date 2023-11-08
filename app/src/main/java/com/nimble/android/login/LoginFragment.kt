package com.nimble.android.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nimble.android.R
import com.nimble.android.databinding.FragmentLoginBinding
import com.nimble.android.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding by autoCleared()
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        initializeViewModel()
        observeViewModel()
    }

    private fun initializeViewModel() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewModel = viewModel
    }

    private fun observeViewModel() {
        viewModel.navigateToHomeFragment.observe(viewLifecycleOwner) {
            if(it != null) {
                viewModel.onHomeFragmentNavigate()
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                )
            }
        }
    }
}