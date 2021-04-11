package com.example.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.apply {
            text = "Enter your name below:"
            textSize = 30f
            setTextColor(resources.getColor(R.color.colorPrimary))
            isVisible = true    // This requires changing minimum dependencies
        }

        with(editText){
            hint = "Enter your name here"
            addTextChangedListener {
                Log.i("ViewBinding", it.toString())
//                button.isEnabled = it.toString().length > 6 && it.toString().length < 20
                button.isEnabled = it.toString().length in 7..19
            } }

        button.setOnClickListener {
            Toast.makeText(this, "2. Name is "+editText.text.toString(), Toast.LENGTH_SHORT).show()
//            In place of 'this', we can also write 'it.context'. Every view has a context
        }

        button.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "3. Name is "+editText.text.toString(), Toast.LENGTH_SHORT).show()
        })

        // Anonymous inner class to handle Button click
        button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View) {
                Toast.makeText(view.context, "4. Button pressed from anonymous function", Toast.LENGTH_SHORT).show()
            }
        })

        button.setOnClickListener(this) // We'll implement interface View.OnClickListener and override onClick() function
    }

    fun showText(view: View) {
        Toast.makeText(this, "1. Name is "+editText.text.toString(), Toast.LENGTH_SHORT).show()
//            In place of 'this', we can also write view.context. Every view has a context
    }// This way of handling click on button is not much used.

    override fun onClick(view: View) {
        Toast.makeText(this, "5. Button pressed from interface function", Toast.LENGTH_SHORT).show()
    }
}