package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NonHealthProfessionalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_health_professional)

        // Initialize the buttons
        val generalWellbeingButton: Button = findViewById(R.id.btn_generalwellbeing)
        val healthcareInfoButton: Button = findViewById(R.id.btn_healthcareinfo)
        val realtimeDataButton: Button = findViewById(R.id.btn_realtimedata)

        // Set click listeners for each button
        generalWellbeingButton.setOnClickListener {
            val intent = Intent(this, HealthCareInfo::class.java)
            startActivity(intent)
        }

        healthcareInfoButton.setOnClickListener {
            val intent = Intent(this, HealthCareInfo::class.java)
            startActivity(intent)
        }

        realtimeDataButton.setOnClickListener {
            val intent = Intent(this, HealthCareInfo::class.java)
            startActivity(intent)
        }
    }
}
