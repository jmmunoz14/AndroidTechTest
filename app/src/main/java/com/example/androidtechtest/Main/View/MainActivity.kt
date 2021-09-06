package com.example.androidtechtest.Main.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtechtest.Main.Adapters.CustomSpinnerAdapter
import com.example.androidtechtest.Main.Adapters.MainAdapter
import com.example.androidtechtest.Main.Adapters.OnTeamListener
import com.example.androidtechtest.Main.Repository.MainRepository
import com.example.androidtechtest.Main.ViewModel.CustomViewModelFactory
import com.example.androidtechtest.Main.ViewModel.MainViewModel
import com.example.androidtechtest.Models.SpinnerLeagues
import com.example.androidtechtest.Models.Team
import com.example.androidtechtest.R
import com.example.androidtechtest.DaoTest.ApiService
import com.example.androidtechtest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnTeamListener {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private lateinit var retrofitService: ApiService
    private lateinit var selectedLeague: SpinnerLeagues
    var arrayLeagues = mutableListOf<SpinnerLeagues>()
    val adapter = MainAdapter(this)
    lateinit var teamsList : List<Team>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.activity_main, null, false)
        setContentView(binding.root)
        Thread.sleep(2000)
        setTheme(R.style.Theme_AndroidTechTest_NoActionBar)

        retrofitService = ApiService.getInstance(resources)

        viewModel =
            ViewModelProvider(this, CustomViewModelFactory(MainRepository(retrofitService,application), application)).get(
                MainViewModel::class.java
            )

        binding.recyclerview.adapter = adapter

        val spinner: Spinner = findViewById(R.id.spinner)


        for(i in resources.getStringArray(R.array.leagues_array)){
            var name = i.split("-")[0]
            var id = i.split("-")[1]
            var league = SpinnerLeagues(name,id)
            arrayLeagues.add(league)
        }

        val arrayAdapter = CustomSpinnerAdapter(this,arrayLeagues)

        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedLeague = arrayLeagues[spinner.selectedItem as Int]
                viewModel.getAllTeams(selectedLeague.id)
            }

        }
        spinner.setSelection(0)

        selectedLeague = arrayLeagues[spinner.selectedItem as Int]


        viewModel.getAllTeams(selectedLeague.id)?.observe(this, Observer {
            it?.let {
                teamsList = it
                adapter.setTeamsList(it)
            }

        })


    }

    override fun onTeamClick(position: Int) {
        var team : Team = teamsList[position]
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("team", team )
        startActivity(intent)

    }
}