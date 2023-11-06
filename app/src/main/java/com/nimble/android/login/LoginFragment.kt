package com.nimble.android.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.nimble.android.BuildConfig
import com.nimble.android.R
import com.nimble.android.databinding.FragmentLoginBinding
import com.nimble.android.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private var binding: FragmentLoginBinding by autoCleared()
    private val viewModel: LoginViewModel by viewModels()

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_login, container, false)
//
//        val application = requireNotNull(this.activity).application
//        val viewModelFactory = LoginViewModelFactory(application)
//        val loginViewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
//        binding.loginViewModel = loginViewModel
//
//        loginViewModel.navigateToHomeFragment.observe(viewLifecycleOwner) { data ->
//            data?.let {
//                findNavController().navigate(
//                    LoginFragmentDirections.actionLoginFragmentToHomeFragment(
//                        data
//                    )
//                )
//                loginViewModel.onHomeFragmentNavigate()
//            }
//        }
//        return binding.root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        observeViewModel()
    }

    private fun observeViewModel() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                launch {
//
//                }
//            }
//        }
        viewModel.navigateToHomeFragment.observe(viewLifecycleOwner) { data ->
            data?.let {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                        data
                    )
                )
                viewModel.onHomeFragmentNavigate()
            }
        }
    }
}