package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,//name of database class
            "User.db"// name of database
        )
//            .allowMainThreadQueries()// This is not in good prcatice, so in btnSave we used GlobalScope.launch & runBlocking in btnFetch below
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSave.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                db.userDao().insert(User("Apoorv Srivastava", "9650284103", "1733/29, FBD", 20))
            }
        }

        btnFetch.setOnClickListener {
            runBlocking {
                val list = GlobalScope.async(Dispatchers.IO) { db.userDao().getAllUser() }
                if (list.await().isNotEmpty()) {
                    with(list.await()[0]) {
                        tvName.text = name
                        tvAddress.text = address
                        tvAge.text = age.toString()
                        tvNumber.text = number
                    }
                }
            }
        }

    }
}