package com.philippo.tp3.model

data class Question(
    var question: String,
    var currentQuestionAlternative: MutableList<String>)
