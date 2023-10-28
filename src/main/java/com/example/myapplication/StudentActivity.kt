package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)

        val educationalResources: Button = findViewById(R.id.educationalResources)
        val schoolspecifichealthAid: Button = findViewById(R.id.schoolspecifichealthAid)
        val mentalhealthResources: Button = findViewById(R.id.mentalhealthResources)

        // Setting up OnClickListener for each button to navigate to the respective activities
        educationalResources.setOnClickListener {
            val intent = Intent(this, EducationalResourcesActivity::class.java)
            startActivity(intent)
        }

        schoolspecifichealthAid.setOnClickListener {
            val intent = Intent(this, SchoolSpecificHealthAidActivity::class.java)
            startActivity(intent)
        }

        mentalhealthResources.setOnClickListener {
            val intent = Intent(this, MentalHealthResourcesActivity::class.java)
            startActivity(intent)
        }
    }
}
