package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast

class SubjectsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)

        val subjectSearch: AutoCompleteTextView = findViewById(R.id.subjectSearch)
        val continueButton: Button = findViewById(R.id.continueButton)

        // Sample list of subjects (you can replace with your own)
        val subjects = arrayOf(
            "Anatomy", "Biochemistry", "Biomechanics", "Cardiology", "Dermatology",
            "Endocrinology", "Epidemiology", "Gastroenterology", "Genetics", "Hematology",
            "Immunology", "Infectious Diseases", "Internal Medicine", "Microbiology",
            "Nephrology", "Neurology", "Obstetrics and Gynecology", "Oncology",
            "Ophthalmology", "Orthopedics", "Otolaryngology (ENT)", "Pathology",
            "Pediatrics", "Pharmacology", "Physiology", "Psychiatry", "Pulmonology",
            "Radiology", "Rheumatology", "Surgery", "Toxicology", "Urology", "Vascular Medicine"
        )


        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, subjects)
        subjectSearch.setAdapter(adapter)
        subjectSearch.threshold = 1

        subjectSearch.setOnItemClickListener { _, _, position, _ ->
            val selectedExam = adapter.getItem(position)
            Toast.makeText(this, "Selected Subject: $selectedExam", Toast.LENGTH_SHORT).show()

        }
        continueButton.setOnClickListener {
            // Check if a subject has been selected
            if (subjectSearch.text.isNotEmpty()) {
                val intent = Intent(this, AIChatActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a subject.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

