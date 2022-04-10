package com.binar.ariefaryudisyidik.challengesilverchapter4.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.binar.ariefaryudisyidik.challengesilverchapter4.databinding.FragmentRegisterBinding
import com.binar.ariefaryudisyidik.challengesilverchapter4.helper.Preferences

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val username = binding.edtUsername.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        val confirmPassword = binding.edtConfirmPassword.text.toString().trim()
        var isValid = true

        if (username.isEmpty()) {
            binding.tilUsername.error = "Field can't be empty"
            isValid = false
        } else {
            binding.tilUsername.error = null
            binding.tilUsername.isErrorEnabled = false
        }
        if (email.isEmpty()) {
            binding.tilEmail.error = "Field can't be empty"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Invalid email"
            isValid = false
        } else {
            binding.tilEmail.error = null
            binding.tilEmail.isErrorEnabled = false
        }
        if (password.isEmpty()) {
            binding.tilPassword.error = "Field can't be empty"
            isValid = false
        } else {
            binding.tilPassword.error = null
            binding.tilPassword.isErrorEnabled = false
        }
        if (confirmPassword.isEmpty()) {
            binding.tilConfirmPassword.error = "Field can't be empty"
            isValid = false
        } else if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Password doesn't match"
            isValid = false
        } else {
            binding.tilConfirmPassword.error = null
            binding.tilConfirmPassword.isErrorEnabled = false
        }

        if (isValid) {
            Preferences().setRegisteredUsername(requireContext(), username)
            Preferences().setRegisteredEmail(requireContext(), email)
            Preferences().setRegisteredPassword(requireContext(), password);
            clearField()
            Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearField() {
        binding.edtUsername.setText("")
        binding.edtEmail.setText("")
        binding.edtPassword.setText("")
        binding.edtConfirmPassword.setText("")
        binding.edtUsername.isFocusable = false
        binding.edtEmail.isFocusable = false
        binding.edtPassword.isFocusable = false
        binding.edtConfirmPassword.isFocusable = false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}