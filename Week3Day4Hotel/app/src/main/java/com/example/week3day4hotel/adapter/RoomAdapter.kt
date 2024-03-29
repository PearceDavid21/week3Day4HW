package com.example.week3day4hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.week3day4hotel.R
import com.example.week3day4hotel.model.Room

class RoomAdapter (private val roomList: List<Room>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View =
            LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.room_layout, parent, false)

        view.findViewById<TextView>(R.id.room_number_textview).text = roomList[position].roomnumber


        return view
    }

    override fun getItem(position: Int): Room {
        return roomList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {

        return roomList.size
    }
}