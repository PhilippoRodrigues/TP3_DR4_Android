package com.philippo.tp3.user.register

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.philippo.tp3.R
import kotlinx.android.synthetic.main.user_register_fragment.*

class UserRegisterFragment : Fragment() {

    private lateinit var registerViewModel: UserRegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        registerViewModel = ViewModelProvider(this).get(UserRegisterViewModel::class.java)

        registerViewModel.status.observe(viewLifecycleOwner){
            if(it)
                findNavController().popBackStack()
        }

        registerViewModel.msg.observe(viewLifecycleOwner){
            if(!it.isNullOrBlank())
                Snackbar.make(requireContext(), this.requireView(), it, Snackbar.LENGTH_LONG).show()
        }

        return inflater.inflate(
            R.layout.user_register_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCadastro.setOnClickListener {
            val password = editTextPassword.text.toString()
            val repeatPassword = editTextRepeatPassword.text.toString()

            if (password == repeatPassword) {
                val email = editTextCadastroEmail.text.toString()

                registerViewModel.saveRegister(email, password)
            }
            else {
                registerViewModel.changeMessage("Senhas não conferem")
            }
        }
    }
}