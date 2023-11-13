package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentButton: LinearLayout = findViewById(R.id.studentButton)
        val healthProfessionalButton: LinearLayout = findViewById(R.id.healthProfessionalButton)
        val nonHealthProfessionalButton: LinearLayout = findViewById(R.id.nonHealthProfessionalButton)

        studentButton.setOnClickListener {
            val intent = Intent(this, StudentActivity::class.java)
            startActivity(intent)
        }

        healthProfessionalButton.setOnClickListener {
            val intent = Intent(this, HealthProfessionalActivity::class.java)
            startActivity(intent)
        }

        nonHealthProfessionalButton.setOnClickListener {
            val intent = Intent(this, NonHealthProfessionalActivity::class.java)
            startActivity(intent)
        }
    }
}