package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdvisingActivity : AppCompatActivity() {

    private lateinit var courseAdvisingButton: Button
    private lateinit var careerAdvisingButton: Button
    private lateinit var studyTipsButton: Button
    private lateinit var firstGenButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advising)

        // Initialize buttons
        courseAdvisingButton = findViewById(R.id.courseAdvisingButton)
        careerAdvisingButton = findViewById(R.id.careerAdvisingButton)
        studyTipsButton = findViewById(R.id.studyTipsButton)
        firstGenButton = findViewById(R.id.firstGenButton)

        // Set onClickListeners
        courseAdvisingButton.setOnClickListener {
            val intent = Intent(this, AIChatActivity::class.java)
            startActivity(intent)
        }

        careerAdvisingButton.setOnClickListener {
            val intent = Intent(this, AIChatActivity::class.java)
            startActivity(intent)
        }

        studyTipsButton.setOnClickListener {
            val intent = Intent(this, AIChatActivity::class.java)
            startActivity(intent)
        }

        firstGenButton.setOnClickListener {
            val intent = Intent(this, AIChatActivity::class.java)
            startActivity(intent)
        }
    }
}
