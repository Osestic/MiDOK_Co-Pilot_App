package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class HealthCareInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_care_info)

        val btnYou: Button = findViewById(R.id.btn_you)
        val btnSpouse: Button = findViewById(R.id.btn_spouse)
        val btnFamily: Button = findViewById(R.id.btn_family)

        btnYou.setOnClickListener {
            val intent = Intent(this, HealthCareInfo2::class.java)
            startActivity(intent)
        }

        btnSpouse.setOnClickListener {
            val intent = Intent(this, HealthCareInfo2::class.java)
            startActivity(intent)
        }

        btnFamily.setOnClickListener {
            val intent = Intent(this, HealthCareInfo2::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
