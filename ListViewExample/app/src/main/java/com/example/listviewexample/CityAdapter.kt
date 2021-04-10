package com.example.listviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CityAdapter(val cityData: Array<City>) : BaseAdapter() {

    // Returns the size of the data
    override fun getCount(): Int {
        return cityData.size
    }

    // Returns the item present at the position passed
    override fun getItem(position: Int): City {
        return cityData[position]
    }

    // Returns the unique id of the item present at the given position
    override fun getItemId(position: Int): Long {
        return cityData[position].name.hashCode()
            .toLong()  // city name is going to be unique, and its
        // hashcode is also unique. And since hashcode returns int so we convert it to long
    }

    // region
//    override fun getView(position: Int, convertView: View, container: ViewGroup?): View {
//        // get the layout or inflate the layout
//        val convertView = LayoutInflater.from(container?.context).inflate(R.layout.city_item, container, false)
//
//        // find the views
//        val cityCountry: TextView = convertView.findViewById(R.id.city_country)
//        val cityName: TextView = convertView.findViewById(R.id.city_name)
//
//        // set the data
//        cityCountry.text = getItem(position).country
//        cityName.text = getItem(position).name
//
//        // return the view
//        return convertView
//    }
//endregion

    // Improved version of get view, this method improves the memory consumption
//region
    override fun getView(position: Int, convertView: View?, container: ViewGroup): View {
        val cityView: View
        val viewHolder: ViewHolder

        // First we check whether convertView is null i.e. whether an existing view is passed to us
        if (convertView == null) {
            // Inflate the layout
            cityView = LayoutInflater.from(container.context).inflate(
                R.layout.city_item, container, false
            )

            viewHolder = ViewHolder()   // create an object of ViewHolder in which we put references of layout items.
            with(viewHolder) {
                cityCountry = cityView.findViewById(R.id.city_country)
                cityName = cityView.findViewById(R.id.city_name)
                cityView.tag = this// tag is an attribute of every view and we can
                // put any object inside this, so we put object of ViewHolder("this") as tag of the view which we inflated
            }
        } else {
            cityView = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        // we set the data
        with(viewHolder) {
            cityCountry.text = getItem(position).country
            cityName.text = getItem(position).name
        }

        return cityView
    }

    private class ViewHolder {
        lateinit var cityCountry: TextView //lateinit because we are going to initialise them later in the code
        lateinit var cityName: TextView
    }
//endregion

}