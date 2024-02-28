package com.example.lb1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PhotoAddActivity : AppCompatActivity() {
    private val preferences: SharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_add)
        val textView: TextView = findViewById(R.id.tv1)
        preferences.edit().putString("name","stanislav").apply()
        textView.text = preferences.getString("name","noName")

    }


}