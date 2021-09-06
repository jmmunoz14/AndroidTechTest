package com.example.androidtechtest.Main.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.androidtechtest.Main.Repository.MainRepository
import com.example.androidtechtest.Models.Team
import com.example.androidtechtest.Models.Teams
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LiveData




class MainViewModel constructor(private val repository: MainRepository, application: Application) : AndroidViewModel(
    application
) {

    fun getAllTeams(leagueID: String) : LiveData<List<Team>?>? {
        return repository.getAllTeams(leagueID)
    }
}