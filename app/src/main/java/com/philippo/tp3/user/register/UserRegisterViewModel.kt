package com.philippo.tp3.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.philippo.tp3.dao.UserDao

class UserRegisterViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun saveRegister(email: String, password: String) {
        UserDao
            .saveRegister(email, password)
            .addOnSuccessListener {
                _status.value = true
                changeMessage("Usuário cadastrado com sucesso!")
            }
            .addOnFailureListener {
                changeMessage("${it.message}")
            }
    }

    fun changeMessage(msg: String) {
        _msg.value = msg
    }
}