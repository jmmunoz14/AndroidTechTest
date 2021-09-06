package com.example.androidtechtest.Main.DB.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidtechtest.Models.Team

@Dao
interface TeamsDAO {

    @Query(value = "SELECT * FROM team WHERE idLeague = (:idLeaguess) ")
    fun getAllTeamsFromLeague(idLeaguess: String): List<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teams: List<Team>)

    @Delete
    fun delete(team: Team)
}