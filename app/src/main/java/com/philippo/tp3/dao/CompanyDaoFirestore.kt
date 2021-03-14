package com.philippo.tp3.dao

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.philippo.tp3.model.Company

class CompanyDaoFirestore : CompanyDao {

    private val collection = FirebaseFirestore
        .getInstance()
        .collection("companies")

    override fun insert(company: Company): Task<Void> {
        return collection
            .document(company.name!!)
            .set(company)
    }

    override fun get(name: String): Task<DocumentSnapshot> {
        return collection
            .document(name)
            .get()
    }

    override fun all(): CollectionReference {
        return collection
    }
}