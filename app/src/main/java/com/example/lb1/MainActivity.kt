package com.example.lb1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val imageView: ImageView = findViewById(R.id.imageView)
        val button: Button = findViewById(R.id.bt)


        imageView.setOnClickListener {
            textView.text = imageView.contentDescription
        }

        textView.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        button.setOnClickListener {
            val intent = Intent(this, PhotoAddActivity::class.java)
            startActivity(intent)
        }

    }
}
