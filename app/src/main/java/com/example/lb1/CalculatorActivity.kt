package com.example.lb1

import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var editText: EditText

    private var operand1: Int = 0
    private var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textViewSecond)

        editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(10))

        val numberButtons = listOf<Button>(
            findViewById(R.id.bt1),
            findViewById(R.id.bt2),
            findViewById(R.id.bt3),
            findViewById(R.id.bt4),
            findViewById(R.id.bt5),
            findViewById(R.id.bt6),
            findViewById(R.id.bt7),
            findViewById(R.id.bt8),
            findViewById(R.id.bt9),
            findViewById(R.id.bt10)
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                editText.append(button.text)
            }
        }

        val buttonEquals: Button = findViewById(R.id.bt11)
        val buttonClear: Button = findViewById(R.id.bt12)
        val buttonAdd: Button = findViewById(R.id.bt13)
        val buttonSubtract: Button = findViewById(R.id.bt14)
        val buttonMultiply: Button = findViewById(R.id.bt15)
        val buttonDivide: Button = findViewById(R.id.bt16)

        buttonEquals.setOnClickListener { performOperation("=") }
        buttonAdd.setOnClickListener { performOperation("+") }
        buttonSubtract.setOnClickListener { performOperation("-") }
        buttonMultiply.setOnClickListener { performOperation("*") }
        buttonDivide.setOnClickListener { performOperation("/") }
        buttonClear.setOnClickListener {
            editText.setText("")
            textView.text = ""
        }
    }

    private fun performOperation(newOperator: String) {
        val inputString = editText.text.toString()
        if (inputString.isNotEmpty()) {
            val input = inputString.toInt()
            when (operator) {
                null,
                "=" -> operand1 = input
                "+" -> operand1 += input
                "-" -> operand1 -= input
                "*" -> operand1 *= input
                "/" -> operand1 /= input
            }
            operator = newOperator
            textView.text = operand1.toString()
            editText.text.clear()
        } else {
            textView.text = "Error: No input"
        }
    }
}