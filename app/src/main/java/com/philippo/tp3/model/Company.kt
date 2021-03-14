package com.philippo.tp3.model

import com.google.firebase.firestore.DocumentId

class Company(
    @DocumentId
    val name: String? = null,
    val district: String? = null,
)