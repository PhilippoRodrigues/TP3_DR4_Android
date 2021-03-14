package com.philippo.tp3.ui.evaluation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.philippo.tp3.R
import com.philippo.tp3.databinding.EvaluationFragmentBinding

class EvaluationFragment : Fragment() {

    lateinit var binding: EvaluationFragmentBinding
    private lateinit var viewModel: EvaluationViewModel

    private fun obterResposta(answer: String) {
        if (answer == viewModel._currentQuestion.value!!.currentQuestionAlternative[0]) {
            viewModel.updateCurrentScore()
        }

        viewModel.questionIndex++

        if (viewModel.questionIndex < viewModel.qtdMaxQuestions) {
            viewModel.setGetQuestion()
            binding.invalidateAll()
        } else {
            getScore()
        }
    }

    private fun getScore() {
        val action =
            EvaluationFragmentDirections.actionEvaluationFragmentToScoreFragment()
        action.score = viewModel.score.value ?: 0
        if (viewModel.score.value!! == 6) {
            this.findNavController().navigate(action)
            Toast.makeText(activity, "Uau! Quanta perfeição esse lugar!", Toast.LENGTH_SHORT).show()
        } else {
            this.findNavController().navigate(action)
            Toast.makeText(activity, "Vish... Fecha isso!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.evaluation_fragment, container, false)

        viewModel = ViewModelProvider(this).get(EvaluationViewModel::class.java)

        binding.evaluationViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    //Chama a função obterResposta para cada evento de clique entre as alternativas
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val alternativeA = view?.findViewById<TextView>(R.id.alternativeA)
        val alternativeB = view?.findViewById<TextView>(R.id.alternativeB)

        alternativeA?.setOnClickListener {
            obterResposta(alternativeA.text.toString())
        }

        alternativeB?.setOnClickListener {
            obterResposta(alternativeB.text.toString())
        }
    }
}