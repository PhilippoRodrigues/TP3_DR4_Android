package com.philippo.tp3.ui.company.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import androidx.security.crypto.MasterKeys.*
import com.philippo.tp3.R
import com.philippo.tp3.dao.CompanyDaoFirestore
import com.philippo.tp3.databinding.CompanyRegisterFragmentBinding
import kotlinx.android.synthetic.main.company_register_fragment.*
import kotlinx.android.synthetic.main.score_fragment.*

class CompanyRegisterFragment : Fragment() {

    lateinit var binding: CompanyRegisterFragmentBinding

    private lateinit var companyViewModel: CompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireActivity().application
        val companyViewModelFactory = CompanyViewModelFactory(CompanyDaoFirestore(), application)

        companyViewModel = ViewModelProvider(this, companyViewModelFactory).get(CompanyViewModel::class.java)

        companyViewModel.msg.observe(viewLifecycleOwner) { msg ->
            if (!msg.isNullOrBlank())
                Toast.makeText(
                    requireContext(),
                    msg,
                    Toast.LENGTH_LONG
                ).show()
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.company_register_fragment, container, false)

//        btnProceedEvaluation.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.action_companyRegisterFragment_to_evaluationFragment)
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnProceedEvaluation.setOnClickListener {
            val name = editTextCompanyName.text.toString()
            val district = editTextCompanyDistrict.text.toString()

            val masterKeyAlias = getOrCreate(AES256_GCM_SPEC)

            val cryptoCompanyName = EncryptedSharedPreferences.create(
                name, masterKeyAlias, requireContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

            val cryptoCompanyDistrict = EncryptedSharedPreferences.create(
                district, masterKeyAlias, requireContext(),
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

            companyViewModel.store(cryptoCompanyName.toString(), cryptoCompanyDistrict.toString())

            Navigation.findNavController(it).navigate(R.id.action_companyRegisterFragment_to_evaluationFragment)
        }
    }

//    private fun preencherFormulario(company: Company) {
//        editTextCompanyName.setText(company.name)
//        editTextCompanyDistrict.setText(company.district)
//        editTextFormCarroPlaca.setText(carro.placa)
//        formCarroViewModel.downloadImageCarro(carro.placa!!)
//        btnFormSalvar.text = "Atualizar"
//    }
}