package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HealthProfessionalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_professional)

        val btnDiseaseDiagnosis: Button = findViewById(R.id.btn_disease_diagnosis)
        val btnMedicationInformation: Button = findViewById(R.id.btn_medication_information)
        val btnLatestMedicalDevices: Button = findViewById(R.id.btn_latest_medical_devices)
        val btnResearchPublications: Button = findViewById(R.id.btn_research_publications)


        btnDiseaseDiagnosis.setOnClickListener {
            val intent = Intent(this, DiseaseDiagnosis::class.java)
            startActivity(intent)
        }

        btnMedicationInformation.setOnClickListener {
            // Handle Medication Information action
        }

        btnLatestMedicalDevices.setOnClickListener {
            // Handle Latest Medical Devices action
        }

        btnResearchPublications.setOnClickListener {
            // Handle Research & Publications action
        }
    }
}
