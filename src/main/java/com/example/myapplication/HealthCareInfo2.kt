package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class HealthCareInfo2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_care_info2)

        val btnCommonDiseases: Button = findViewById(R.id.btn_commondiseases)
        val btnHereditary: Button = findViewById(R.id.btn_hereditary)
        val btnMental: Button = findViewById(R.id.btn_mental)
        val btnSexual: Button = findViewById(R.id.btn_sexual)

        btnCommonDiseases.setOnClickListener {
            val intent = Intent(this, CommonDiseases::class.java)
            startActivity(intent)
        }

        btnHereditary.setOnClickListener {
            // Handle "Hereditary diseases" button click
            showToast("Information about Hereditary diseases")
        }

        btnMental.setOnClickListener {
            // Handle "Mental Health Issues" button click
            showToast("Information about Mental Health Issues")
        }

        btnSexual.setOnClickListener {
            // Handle "Sexual Transmitted Diseases" button click
            showToast("Information about Sexual Transmitted Diseases")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
