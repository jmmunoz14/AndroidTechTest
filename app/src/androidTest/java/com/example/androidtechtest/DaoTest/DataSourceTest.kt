package com.example.androidtechtest.DaoTest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidtechtest.Main.DB.dao.TeamDB
import com.example.androidtechtest.Main.DB.dao.TeamsDAO
import com.example.androidtechtest.Models.Team
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
@RunWith(AndroidJUnit4::class)
class DataSourceTest {
    private lateinit var teamsDAO: TeamsDAO
    private lateinit var teamDB: TeamDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        teamDB = Room.inMemoryDatabaseBuilder(
            context, TeamDB::class.java).build()
        teamsDAO = teamDB.localTeamDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        teamDB.close()
    }

    /**
     * Test para asegurar que se guardan de forma correcta los equipos
     */
    @Test
    @Throws(Exception::class)
    fun writeTeamsAndReadList() {
        val team1 = Team("1234","123","algo","1901","algo es severo equipo","","","","","","","")
        val team2 = Team("1244","123","algo","1901","algo es severo equipo","","","","","","","")
        val team3 = Team("1254","123","algo","1901","algo es severo equipo","","","","","","","")

        val teamList = listOf(team1,team2,team3)
        teamsDAO.insertAll(teamList)
        assertEquals(teamsDAO.getAllTeamsFromLeague("123").size,3)

    }


    /**
     * Test para asegurar que no hay conflictos por el mismo id del equipo.
     */
    @Test
    @Throws(Exception::class)
    fun writeTheSameTeamManyTimes() {
        val team1 = Team("1234","123","algo","1901","algo es severo equipo","","","","","","","")
        val team2 = Team("1234","123","algo","1901","algo es severo equipo","","","","","","","")
        val team3 = Team("1234","123","algo","1901","algo es severo equipo","","","","","","","")

        val teamList = listOf(team1,team2,team3)
        teamsDAO.insertAll(teamList)
        assertEquals(teamsDAO.getAllTeamsFromLeague("123").size,1)

    }


}