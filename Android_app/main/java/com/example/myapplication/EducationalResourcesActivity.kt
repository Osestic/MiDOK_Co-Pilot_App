package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EducationalResourcesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_educational_resources)

        // Initializing the buttons
        val subjectsButton: Button = findViewById(R.id.subjectsButton)
        val professionalExamsButton: Button = findViewById(R.id.professionalExamsButton)
        val advisingButton: Button = findViewById(R.id.advisingButton)

        // Setting up OnClickListener for each button to navigate to the respective activities
        subjectsButton.setOnClickListener {
            val intent = Intent(this, SubjectsActivity::class.java)
            startActivity(intent)
        }

        professionalExamsButton.setOnClickListener {
            val intent = Intent(this, ProfessionalExamsActivity::class.java)
            startActivity(intent)
        }

        advisingButton.setOnClickListener {
            val intent = Intent(this, AdvisingActivity::class.java)
            startActivity(intent)
        }
    }
}
