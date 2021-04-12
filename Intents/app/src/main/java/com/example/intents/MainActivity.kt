package com.example.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

const val KEY_1 = "Name"
const val KEY_2 = "Age"
const val KEY_3 = "Student"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnExplicitIntent.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra(KEY_1,"Apoorv")
            intent.putExtra(KEY_2,20)
            intent.putExtra(KEY_3,true)
            startActivity(intent)
        }

        btnmail.setOnClickListener {
            val email = et.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_SENDTO // The type of thing this Intent will be doing
            i.data = Uri.parse("mailto:$email")
            i.putExtra(Intent.EXTRA_SUBJECT,"Implicit Intent")
            startActivity(i)
        }

        btnbrowse.setOnClickListener {
            val url = et.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_VIEW // When we click on browse button, we want to view some webpage , so action will be VIEW
            i.data = Uri.parse("http://$url")
            startActivity(i)
        }

        btndial.setOnClickListener {
            val mobile = et.text.toString()
            val i = Intent()
            i.action = Intent.ACTION_DIAL // When we click on dial button, so action will be DIAL
            i.data = Uri.parse("tel:$mobile") // tel stands for telephone
            startActivity(i)
        }

    }
}