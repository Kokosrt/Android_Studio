package com.example.lb1

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lb1.R
import java.io.File
import java.io.FileOutputStream
import kotlin.math.ceil
import kotlin.math.max

@Suppress("DEPRECATION")
class RegistrationActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var imageView: ImageView
    private var selectedImageUri: Uri? = null


    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val editTextName = findViewById<EditText>(R.id.inName)
        val editTextPhone = findViewById<EditText>(R.id.inPhone)
        val editTextDoB = findViewById<EditText>(R.id.inDateOfBirth)
        val editTextLogin = findViewById<EditText>(R.id.inLogin)
        val editTextPassword = findViewById<EditText>(R.id.inPassword)
        val editTextConfirmPassword = findViewById<EditText>(R.id.inConfirmPassword)

        imageView = findViewById(R.id.avatar)

        imageView.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Виберіть фото"), PICK_IMAGE_REQUEST)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK && data != null && data.data != null) {
            selectedImageUri = data.data
            val scaledBitmap = scaleBitmap(selectedImageUri)
            if (scaledBitmap != null) {
                imageView.setImageBitmap(scaledBitmap)
            } else {
                // Обробка ситуації, коли scaledBitmap має значення null
            }
        }
    }


    private fun scaleBitmap(selectedImageUri: Uri?): Bitmap? {
        selectedImageUri?.let {
            val inputStream = contentResolver.openInputStream(it)
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()
            val scaleFactor = calculateScaleFactor(options.outWidth, options.outHeight)
            options.inJustDecodeBounds = false
            options.inSampleSize = scaleFactor
            val scaledBitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(it), null, options)
            return scaledBitmap
        }
        return null
    }


    private fun calculateScaleFactor(imageWidth: Int, imageHeight: Int): Int {
        var scaleFactor = 1
        if (imageWidth > MAX_IMAGE_WIDTH || imageHeight > MAX_IMAGE_HEIGHT) {
            val widthScale = imageWidth.toFloat() / MAX_IMAGE_WIDTH.toFloat()
            val heightScale = imageHeight.toFloat() / MAX_IMAGE_HEIGHT.toFloat()
            scaleFactor = ceil(max(widthScale, heightScale).toDouble()).toInt()
        }
        return scaleFactor
    }

    companion object {
        private const val MAX_IMAGE_WIDTH = 500
        private const val MAX_IMAGE_HEIGHT = 300
    }

}



