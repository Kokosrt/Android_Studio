package com.example.lb1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class AuthActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var loginButton: Button
    private lateinit var logEditText: EditText
    private lateinit var passEditText: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val reg: Button = findViewById(R.id.regis)
        loginButton = findViewById(R.id.logBut)
        logEditText = findViewById(R.id.login)
        passEditText = findViewById(R.id.password)

        reg.setOnClickListener() {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        val sharedPreferences: SharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        // Витягнення логіну та паролю
        val savedLogin = sharedPreferences.getString("login", "")
        val savedPassword = sharedPreferences.getString("password", "")

        // Обробник подій для кнопки авторизації
        loginButton.setOnClickListener {
            val enteredLogin = logEditText.text.toString().trim()
            val enteredPassword = passEditText.text.toString().trim()

            // Перевірка порожності полів
            if (enteredLogin.isNotEmpty() && enteredPassword.isNotEmpty()) {
                // Перевірка логіну та паролю
                if (enteredLogin == savedLogin && enteredPassword == savedPassword) {
                    showToast("Вхід успішний!")
                    // Логін та пароль вірні, виконайте дії авторизації
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    finish() // Завершення поточного активіті
                } else {
                    Toast.makeText(this, "Логін або пароль введений неправильно", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Будь ласка, заповніть всі поля перед входом", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}