package com.nimble.android.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.nimble.android.BuildConfig
import com.nimble.android.R
import com.nimble.android.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = LoginViewModelFactory(application)
        val loginViewModel = ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
        binding.loginViewModel = loginViewModel

        loginViewModel.navigateToHomeFragment.observe(viewLifecycleOwner) { data ->
            data?.let {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                        data
                    )
                )
                loginViewModel.onHomeFragmentNavigate()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("key", BuildConfig.client_id)
    }
}