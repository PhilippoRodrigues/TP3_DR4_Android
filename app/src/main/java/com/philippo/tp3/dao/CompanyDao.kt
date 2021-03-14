package com.philippo.tp3.dao

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.philippo.tp3.model.Company

interface CompanyDao {

    fun insert(company: Company): Task<Void>
    fun get(name: String): Task<DocumentSnapshot>
    fun all(): CollectionReference

}