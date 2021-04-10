package com.example.appaddtwonos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        btnAdd.setOnClickListener({
//            result.text = (etNum1.text.toString().toInt() + etNum2.text.toString().toInt()).toString()
//        })

        btnAdd.setOnClickListener(View.OnClickListener{
            result.text = (etNum1.text.toString().toInt() + etNum2.text.toString().toInt()).toString()
        })
        
    }
}