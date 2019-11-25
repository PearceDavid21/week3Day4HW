package com.example.week3day4hotel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week3day4hotel.R
import com.example.week3day4hotel.model.Guest
import com.example.week3day4hotel.view.MainActivity2
import com.example.week3day4hotel.view.MainActivity.Companion.roomList

class GuestAdapter(private  val guestList: List<Guest>):
    RecyclerView.Adapter<GuestAdapter.MyCustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_layout, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        holder.apply {
            guestNameTextView.text = guestList[position].name
            roomTextView.text = guestList[position].room
            nightTextView.text = guestList[position].nights.toString()
        }
    }


    inner class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val guestNameTextView = itemView.findViewById<TextView>(R.id.guest_name_textview)
        val nightTextView = itemView.findViewById<TextView>(R.id.night_textview)
        val roomTextView = itemView.findViewById<TextView>(R.id.room_textview)
    }
}
