package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast

class CommonDiseases : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common_diseases)

        val diseaseSearch: AutoCompleteTextView = findViewById(R.id.diseaseSearch)
        val continueButton: Button = findViewById(R.id.continueButton)

        // Sample list of subjects (you can replace with your own)
        val disease = arrayOf(
            "Diabetes",
            "Asthma",
            "Flu",
            "Common Cold",
            "Hypertension",
            "Migraine"
        )


        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, disease)
        diseaseSearch.setAdapter(adapter)
        diseaseSearch.threshold = 1

        diseaseSearch.setOnItemClickListener { _, _, position, _ ->
            val selectedDisease = adapter.getItem(position)
            Toast.makeText(this, "Selected Disease: $selectedDisease", Toast.LENGTH_SHORT).show()

        }
        continueButton.setOnClickListener {
            // Check if a subject has been selected
            if (diseaseSearch.text.isNotEmpty()) {
                val intent = Intent(this, Diabetes::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select a disease", Toast.LENGTH_SHORT).show()
            }
        }
    }
}