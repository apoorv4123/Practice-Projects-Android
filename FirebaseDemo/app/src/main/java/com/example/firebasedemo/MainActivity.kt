package com.example.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDb = findViewById<Button>(R.id.btnDb)
        val etNote = findViewById<EditText>(R.id.etNote)

        btnDb.setOnClickListener {
            val note = etNote.text.toString()
            // Upload the note to Firebase
            FirebaseDatabase.getInstance().reference.setValue(note)
        }

    }
}