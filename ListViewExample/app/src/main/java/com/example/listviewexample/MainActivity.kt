package com.example.listviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

// Custom list view example 1
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val citydata = arrayOf(
            City("India", "Mumbai"),
            City("Nepal", "Kathmandu"),
            City("Bhutan", "Thimpu")
        )

        val cities = findViewById<ListView>(R.id.cities)
        val cityadapter = CityAdapter(citydata)

        cities.adapter = cityadapter

    }
}