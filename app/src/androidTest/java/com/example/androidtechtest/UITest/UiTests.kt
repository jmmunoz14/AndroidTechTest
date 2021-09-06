package com.example.androidtechtest.UITest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.androidtechtest.Main.Adapters.MainViewHolder
import com.example.androidtechtest.Main.View.MainActivity
import com.example.androidtechtest.Models.Team
import com.example.androidtechtest.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTests {
    private lateinit var stringToBetyped: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    val teamsNumber = 2

    val team1 = Team("1234","123","algo1","1901","algo es severo equipo","","","","","","","")
    val team2 = Team("1244","123","algo2","1901","algo es severo equipo","","","","","","","")
    val team3 = Team("1254","123","Atlético de Madrid, Atletico Madrid","1901","algo es severo equipo","","","","","","","")

    val teamList = listOf(team1,team2,team3)


    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "Espresso"
    }

    /**
     * Asegurarse de que la vista del Recyclerview es visible
     */
    @Test
    fun isListVisible() {
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()))
    }

    /**
     * asegurarse de que se muestra el equipo correcto al hacer click
     */
    @Test
    fun testOnClickTeam() {
        onView(withId(R.id.recyclerview))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MainViewHolder>(teamsNumber,click()))

        onView(withId(R.id.name)).check(matches(withText(teamList[teamsNumber].strAlternate)))
    }

    /**
     * asegurarse de que se navega correctamente al DetailActivity
     */
    @Test
    fun testNavigation() {
        onView(withId(R.id.recyclerview))
            .perform(RecyclerViewActions.actionOnItemAtPosition<MainViewHolder>(teamsNumber,click()))

        onView(withId(R.id.name)).check(matches(withText(teamList[teamsNumber].strAlternate)))

        pressBack()

        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()))

    }

}