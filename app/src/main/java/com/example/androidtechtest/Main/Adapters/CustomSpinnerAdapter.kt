package com.example.androidtechtest.Main.Adapters

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.androidtechtest.Models.SpinnerLeagues
import com.example.androidtechtest.R

class CustomSpinnerAdapter(
    private val context: Context,
    private val mutableList: MutableList<SpinnerLeagues>
) : BaseAdapter() {

    private lateinit var name: TextView

    val layoutInflater: LayoutInflater = LayoutInflater.from(context)


    override fun getCount(): Int {
        return mutableList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View

        view = layoutInflater.inflate(R.layout.custom_spinner, parent, false)
        getItem(position)?.let { country ->
            name = view.findViewById(R.id.name)
            name.text = mutableList[position].name

        }
        return view
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.custom_simple_spinner, parent, false)
        } else {
            view = convertView
        }
        getItem(position)?.let { country ->
            name = view.findViewById(R.id.name)
            name.text = mutableList[position].name
        }
        return view
    }
}