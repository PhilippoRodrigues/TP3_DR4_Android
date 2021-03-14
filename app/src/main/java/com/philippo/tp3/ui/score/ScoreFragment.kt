package com.philippo.tp3.ui.score

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.philippo.tp3.R
import com.philippo.tp3.databinding.ScoreFragmentBinding
import com.philippo.tp3.ui.company.form.CompanyViewModel
import kotlinx.android.synthetic.main.score_fragment.*

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.score_fragment,
            container,
            false
        )

        viewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)

        binding.scoreViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner

        binding.textViewScoreEvaluation.text = viewModel.score.toString()

        viewModel.score.observe(viewLifecycleOwner, {
            textViewScoreEvaluation.text = it.toString()
        })

        binding.textViewScoreEvaluation.text = viewModel.score.toString()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        btnLista.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_scoreFragment_to_listCompanyFragment)
        }
    }
}