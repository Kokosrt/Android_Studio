package com.example.lb1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val button: Button = findViewById(R.id.btN)
        val editText: EditText = findViewById(R.id.editText)
        button.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText != "calc") {
                Toast.makeText(this, "Невірний ввід", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, CalculatorActivity::class.java)
                startActivity(intent)
            }
        }
    }
}