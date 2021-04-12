package com.example.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name = intent.getStringExtra(KEY_1)
        val age = intent.getIntExtra(KEY_1, 21)
        val isStudent = intent.getBooleanExtra(KEY_1, true)
        textView.text = "Name: $name, Age: $age, isStudent?: $isStudent"
    }
}