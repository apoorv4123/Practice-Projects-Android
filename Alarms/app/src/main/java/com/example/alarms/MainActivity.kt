package com.example.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTask.setOnClickListener {

            val i = Intent(this,MainActivity2::class.java)

            val pi = PendingIntent.getActivity(this, 1234, i, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

//            alarmManager.set(AlarmManager.ELAPSED_REALTIME,SystemClock.elapsedRealtime() + 60000, pi)

            // Repeating alarm
//            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime() + 60000,
//                60000,
//                pi)
            // OR
            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 60000,
                60000,
                pi)

        }
    }
}