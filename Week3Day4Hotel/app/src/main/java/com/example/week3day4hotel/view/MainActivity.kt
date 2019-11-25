package com.example.week3day4hotel.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.week3day4hotel.R
import com.example.week3day4hotel.adapter.RoomAdapter
import com.example.week3day4hotel.model.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var roomList: MutableList<Room> = mutableListOf()
        var room: String? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        roomList.add(Room("Room 101"))
        roomList.add(Room("Room 102"))
        roomList.add(Room("Room 103"))

        val newAdapter = RoomAdapter(roomList)

        room_listview.adapter = newAdapter

        room_listview.setOnItemClickListener(AdapterView.OnItemClickListener(){ parent, view, position, id ->

            Toast.makeText(this, roomList[position].roomnumber, Toast.LENGTH_SHORT).show()
            if(roomList[position].roomnumber == "Room 101")
            {
                room = roomList[position].roomnumber
                Glide.with(this)
                    .load("http://www.thebowerinn.co.uk/images/new/Accommodation/Luxury-Rooms/590x374xLuxury-1.jpg.pagespeed.ic.DmcsQ3bFJ3.jpg")
                    .into(room_imageView)
            }


            if(roomList[position].roomnumber == "Room 102")
            {
                room = roomList[position].roomnumber

                Glide.with(this)
                    .load("https://q-cf.bstatic.com/images/hotel/max1024x768/225/225572686.jpg")
                    .into(room_imageView)
            }


            if(roomList[position].roomnumber == "Room 103")
            {
                room = roomList[position].roomnumber


                Glide.with(this)
                    .load("https://image.insider.com/5dd4194979d7575c3025b8e8?width=1100&format=jpeg&auto=webp")
                    .into(room_imageView)
            }

        })
        select_button.setOnClickListener { _->
            if(room != null) {
                var intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("Room",
                    room
                )
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Please choose a room first!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

