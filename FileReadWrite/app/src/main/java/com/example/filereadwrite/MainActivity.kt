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
//            val fos = FileOutputStream(myFile,true)// writeText() does not has Append mode, so use output stream.
//            fos.write(etInput.text.toString().toByteArray())
        }

        btnRead.setOnClickListener {
            val dataDir = ContextCompat.getDataDir(this)
            val myFile = File(dataDir,"file.txt")
            tvDisplay.text = myFile.readText()
        }

// Kotlin provides us with methods like readText() & writeText(). Using these methods, we can read& write without having to make objects of i/p or o/p stream
// But this way has restrictions. Use this way only if you're sure that the file to be read & written won't be huge. And if you desire to have Append mode, 
// make output stream object and then use it to write. writeText() does not has append mode

    }
}
