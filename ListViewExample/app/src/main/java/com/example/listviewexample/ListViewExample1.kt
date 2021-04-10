package com.example.listviewexample

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListViewExample1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l_view_example1)    // Inflate or put the layout inside memory

        val cityNames = arrayOf(
            "New Delhi",
            "Mumbai",
            "Chennai",
            "Kolkata",
            "Guwahati"
        )    // The data we want to show

        val cities: ListView =
            findViewById(R.id.cities)    // We get the reference of cities listView into the memory inside the variable cities

        val cityAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cityNames)

        cities.adapter = cityAdapter    // Set the adapter inside listView

        // We are using lambda because it takes a listener object which is
        // an interface with single function
        cities.setOnItemClickListener { adapterView, view, position, id ->
            val city: TextView = view as TextView   // We get the clicked view
            Toast.makeText(this, city.text, Toast.LENGTH_SHORT).show()
        }

    }

}