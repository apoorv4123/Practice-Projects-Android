package com.example.filereadwrite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWrite.setOnClickListener {
            val dataDir = ContextCompat.getDataDir(this)//Direcory inside which I want to save my file
            val myFile = File(dataDir,"file.txt")

            myFile.writeText(etInput.text.toString())// In kotlin we can choose not to make file output stream object
//            OR
//            val fos = FileOutputStream(myFile,true)
//            fos.write(etInput.text.toString().toByteArray())
        }

        btnRead.setOnClickListener {
            val dataDir = ContextCompat.getDataDir(this)
            val myFile = File(dataDir,"file.txt")
            tvDisplay.text = myFile.readText()
        }



    }
}