package com.example.lb1


import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast


class RegistrationActivity() : AppCompatActivity(), Parcelable {

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var dateOfBirth: EditText
    private lateinit var login: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var reg: Button

    constructor(parcel: Parcel) : this() {

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        val preference: SharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        name = findViewById(R.id.inName)
        phone = findViewById(R.id.inPhone)
        dateOfBirth = findViewById(R.id.inDateOfBirth)
        login = findViewById(R.id.inLogin)
        password = findViewById(R.id.inPassword)
        confirmPassword = findViewById(R.id.inConfirmPassword)
        reg = findViewById(R.id.cReg)

        reg.setOnClickListener {
            // Отримання даних з полів вводу
            val nameValue = name.text.toString().trim()
            val phoneValue = phone.text.toString().trim()
            val dateOfBirthValue = dateOfBirth.text.toString().trim()
            val loginValue = login.text.toString().trim()
            val passwordValue = password.text.toString().trim()
            val confirmPasswordValue = confirmPassword.text.toString().trim()

            // Перевірка порожності полів
            if (nameValue.isNotEmpty() && phoneValue.isNotEmpty() &&
                dateOfBirthValue.isNotEmpty() && loginValue.isNotEmpty() &&
                passwordValue.isNotEmpty() && confirmPasswordValue.isNotEmpty()
            ) {
                // Перевірка співпадіння паролів
                if (passwordValue == confirmPasswordValue) {
                    // Збереження даних у SharedPreferences
                    val sharedPreferences: SharedPreferences =
                        getSharedPreferences("user_data", MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putString("name", nameValue)
                    editor.putString("phone", phoneValue)
                    editor.putString("date_of_birth", dateOfBirthValue)
                    editor.putString("login", loginValue)
                    editor.putString("password", passwordValue)
                    editor.apply()

                    showToast("Реєстрація успішна!")

                    // Перехід на новий активіті
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Паролі не співпадають. Перевірте правильність введених даних.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Будь ласка, заповніть всі поля перед реєстрацією.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val imageView: ImageView = findViewById(R.id.avatar)
        // Викликаємо інтент для вибору фото з галереї при кліку на ImageView
        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data
            // Отримуємо обране фото і встановлюємо його в ImageView
            findViewById<ImageView>(R.id.avatar).setImageURI(selectedImageUri)
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RegistrationActivity> {
        override fun createFromParcel(parcel: Parcel): RegistrationActivity {
            return RegistrationActivity(parcel)
        }

        override fun newArray(size: Int): Array<RegistrationActivity?> {
            return arrayOfNulls(size)
        }
    }
}