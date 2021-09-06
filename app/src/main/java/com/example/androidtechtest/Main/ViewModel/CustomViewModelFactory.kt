package com.example.androidtechtest.Main.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtechtest.Main.Repository.MainRepository

class CustomViewModelFactory constructor(private val repository: MainRepository, private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository, application) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}