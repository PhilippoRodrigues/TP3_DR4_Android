package com.philippo.tp3.ui.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

//    private val _telaDeInicio = MutableLiveData<Boolean>()
//    val telaDeInicio: LiveData<Boolean>
//        get() = _telaDeInicio

    init {
        _score.value = finalScore
    }

//    fun voltarTelaDeInicio() {
//        _telaDeInicio.value = true
//    }
//
//    fun voltarTelaDeInicioFinalizada() {
//        _telaDeInicio.value = false
//    }
}