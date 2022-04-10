package com.binar.ariefaryudisyidik.challengesilverchapter4.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.binar.ariefaryudisyidik.challengesilverchapter4.R
import com.binar.ariefaryudisyidik.challengesilverchapter4.databinding.FragmentLoginBinding
import com.binar.ariefaryudisyidik.challengesilverchapter4.helper.Preferences

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Preferences().getLoggedInStatus(requireContext())) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login() {
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        var isValid = true

        if (email.isEmpty()) {
            binding.tilEmail.error = "Field can't be empty"
            isValid = false
        } else if (!checkEmail(email)) {
            binding.tilEmail.error = "This email is not found"
            isValid = false
        } else {
            binding.tilEmail.error = null
            binding.tilEmail.isErrorEnabled = false
        }

        if (password.isEmpty()) {
            binding.tilPassword.error = "Field can't be empty"
            isValid = false
        } else if (!checkPassword(password)) {
            binding.tilPassword.error = "This password is incorrect"
            isValid = false
        } else {
            binding.tilPassword.error = null
            binding.tilPassword.isErrorEnabled = false
        }

        if (isValid) {
            enter()
        }
    }

    /** Menuju ke MainActivity dan Set User dan Status sedang login, di Preferences  */
    private fun enter() {
        Preferences().setLoggedInUser(
            requireContext(),
            Preferences().getRegisteredUsername(requireContext())
        )
        Preferences().setLoggedInStatus(requireContext(), true)
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

    /** True jika parameter email sama dengan data email yang terdaftar dari Preferences  */
    private fun checkEmail(email: String) =
        email == Preferences().getRegisteredEmail(requireContext())

    /** True jika parameter password sama dengan data password yang terdaftar dari Preferences  */
    private fun checkPassword(password: String) =
        password == Preferences().getRegisteredPassword(requireContext())

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}