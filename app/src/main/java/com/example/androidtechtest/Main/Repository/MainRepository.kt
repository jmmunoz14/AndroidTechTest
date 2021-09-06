package com.example.androidtechtest.Main.Repository

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androidtechtest.Main.DB.dao.TeamDB
import com.example.androidtechtest.Main.DB.dao.TeamsDAO
import com.example.androidtechtest.Models.Team
import com.example.androidtechtest.Models.Teams
import com.example.androidtechtest.DaoTest.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainRepository(private val apiService: ApiService, var application: Application) {

    val teamsList = MutableLiveData<List<Team>>()
    var localTeamDao = getDao()


    fun getAllTeams(leagueID: String): MutableLiveData<List<Team>> {

        val teamsFromDao = localTeamDao.getAllTeamsFromLeague(leagueID)


        if (teamsFromDao?.isEmpty()) {
            var response = apiService.getAllMovies(leagueID)

            response.enqueue(object : Callback<Teams> {
                override fun onResponse(call: Call<Teams>, response: Response<Teams>) {
                    val teams = response.body()?.teams
                    if (teams != null) {
                        teamsList.postValue(teams!!)
                        updateLocalTeams(teams)
                    }
                }

                override fun onFailure(call: Call<Teams>, t: Throwable) {
                    t.message?.let { Log.e("Api call error", it) }
                }
            })
        } else {
            teamsList.value = teamsFromDao
        }


        return teamsList

    }

    fun updateLocalTeams(teams: List<Team>) {

        Thread {
            try {
                localTeamDao.insertAll(teams)
            } catch (e: Exception) {
                e.message?.let { Log.e(TAG, it) }
            }
        }.start()


    }

    private fun getDao(): TeamsDAO {

        val db = TeamDB.getDbInstance(application.applicationContext)
        return db!!.localTeamDao()

    }

}