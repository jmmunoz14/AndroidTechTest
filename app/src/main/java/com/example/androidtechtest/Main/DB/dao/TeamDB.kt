package com.example.androidtechtest.Main.DB.dao

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidtechtest.Models.Team
import androidx.room.Room


@Database(entities = [Team::class], version = 1)
abstract class TeamDB : RoomDatabase() {
    abstract fun localTeamDao(): TeamsDAO


    companion object {
        private var INSTANCE: TeamDB? = null

        fun getDbInstance(context: Context): TeamDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    TeamDB::class.java,
                    "localTeams"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }

}