package com.example.week3day4hotel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3day4hotel.R
import com.example.week3day4hotel.adapter.GuestAdapter
import com.example.week3day4hotel.database.Database
import com.example.week3day4hotel.model.Guest
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.room_textview
import kotlinx.android.synthetic.main.list_layout.*
import kotlinx.android.synthetic.main.room_layout.*

class MainActivity2 : AppCompatActivity() {
    companion object {
        var guestList = mutableListOf<Guest>()
        lateinit var myDatabase: Database
        var room:String? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        var intent = getIntent()
        myDatabase = Database(this)


        room = intent.getStringExtra("Room")
        booked_textview.text = room + " will now be assigned to:"

        submit_button.setOnClickListener { _->

            saveToDatabase()

        }
    }

    override fun onResume() {
        super.onResume()
        readFromDatabase()
    }

    private fun saveToDatabase() {
        val guestName = name_edittext.text.toString()
        val guestRoom = room.toString()
        val guestNights = night_edittext.text.toString()
        val newGuest = Guest(guestName, guestRoom, Integer.parseInt(guestNights))
        myDatabase.insertGuest(newGuest)
        Toast.makeText(this, "Guest added to database.", Toast.LENGTH_SHORT).show()
        clearFields()
        readFromDatabase()
    }

    private fun clearFields() {
        name_edittext.text.clear()
        night_edittext.text.clear()
    }

    private fun readFromDatabase() {
        guestList = mutableListOf()

        val cursor = myDatabase.readAllGuests()
        cursor.moveToFirst()

        if (cursor.count > 0) {
            val guest = Guest(
                cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Database.COLUMN_ROOM)),
                cursor.getInt(cursor.getColumnIndex(Database.COLUMN_NIGHTS)))
            guestList.add(guest)
        }
        while (cursor.moveToNext()) {
            val guestName = cursor.getString(cursor.getColumnIndex(Database.COLUMN_NAME))
            val guestRoom = cursor.getString(cursor.getColumnIndex(Database.COLUMN_ROOM))
            val guestNights = cursor.getInt(cursor.getColumnIndex(Database.COLUMN_NIGHTS))
            val readGuest = Guest(guestName, guestRoom, guestNights)
            guestList.add(readGuest)

        }


        displayUsers()
    }

    private fun displayUsers() {

        val recyclerAdapter = GuestAdapter(guestList)
        guest_listview.adapter = recyclerAdapter
        val layoutMgr = LinearLayoutManager(this)
        guest_listview.layoutManager = layoutMgr
        recyclerAdapter.notifyDataSetChanged()

    }
}
