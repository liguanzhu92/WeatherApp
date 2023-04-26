package com.example.weatherapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.network.model.City
import com.example.weatherapp.utils.ItemClickListener
import java.lang.StringBuilder

class CityListAdapter: RecyclerView.Adapter<CityViewHolder>() {

    private val cityList = mutableListOf<City>()
    private var itemListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val sb = StringBuilder(cityList[position].name + ",")
        sb.append(cityList[position].state + ",")
        sb.append(cityList[position].country)
        with(holder.itemView) {
            findViewById<TextView>(R.id.item_city_info).text = sb.toString()
            setOnClickListener {
                itemListener?.onItemClick(cityList[position].lat, cityList[position].lon)
            }
        }
    }

    fun refreshCityList(list: List<City>) {
        cityList.clear()
        cityList.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener:ItemClickListener) {
        itemListener = listener
    }

}

class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)