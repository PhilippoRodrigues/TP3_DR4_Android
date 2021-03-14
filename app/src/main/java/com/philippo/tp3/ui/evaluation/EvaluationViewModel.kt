package com.philippo.tp3.ui.evaluation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philippo.tp3.model.Question

class EvaluationViewModel : ViewModel() {

    var questionIndex = 0

    val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
    get() = _score

    val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question>
    get() = _currentQuestion

    lateinit var alternatives: MutableList<String>

    val qtdMaxQuestions = 6

    var questions = MutableLiveData<MutableList<Question>>()

    init {
        _score.value = 0

        questions.value = mutableListOf (
            Question("Tem rato?",
                arrayListOf("Sim", "Não")),
            Question("Tem barata?",
                arrayListOf("Sim", "Não")),
            Question("Tem mosca?",
                arrayListOf("Sim", "Não")),
            Question("Está limpinho?",
                arrayListOf("Sim", "Não")),
            Question("Está arrumadinho?",
                arrayListOf("Sim", "Não")),
            Question("O ambiente é simpático?",
                arrayListOf("Sim", "Não")),
        )
        showRandomQuestion()
    }

    fun setGetQuestion() {
        _currentQuestion.value = questions.value!![questionIndex]
        alternatives = ArrayList(_currentQuestion.value!!.currentQuestionAlternative)
    }

    fun showRandomQuestion() {
        questions.value!!.shuffle()
        setGetQuestion()
    }

    fun updateCurrentScore(){
        _score.value = _score.value!!.plus(1)
        showRandomQuestion()
    }
}