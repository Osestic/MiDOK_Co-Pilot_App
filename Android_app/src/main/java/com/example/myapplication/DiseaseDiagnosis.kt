package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class DiseaseDiagnosis : AppCompatActivity() {

    private lateinit var checkBoxFever: CheckBox
    private lateinit var checkBoxFatigue: CheckBox
    private lateinit var checkBoxBreath: CheckBox
    private lateinit var checkBoxCough: CheckBox
    private lateinit var checkBoxPain: CheckBox
    private lateinit var checkBoxThroat: CheckBox
    private lateinit var checkBoxTaste: CheckBox
    private lateinit var checkBoxHeadache: CheckBox
    private lateinit var checkBoxVomiting: CheckBox
    private lateinit var checkBoxDiarrhea: CheckBox
    private lateinit var checkBoxAches: CheckBox
    private lateinit var checkBoxChills: CheckBox

    private lateinit var seekBarSeverity: SeekBar
    private lateinit var spinnerDuration: Spinner
    private lateinit var continueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_diagnosis)

        initializeViews()

        continueButton.setOnClickListener {
            val intent = Intent(this, AIChatActivity2::class.java)

            intent.putExtra("fever", checkBoxFever.isChecked)
            intent.putExtra("fatigue", checkBoxFatigue.isChecked)
            intent.putExtra("breath", checkBoxBreath.isChecked)
            intent.putExtra("cough", checkBoxCough.isChecked)
            intent.putExtra("pain", checkBoxPain.isChecked)
            intent.putExtra("throat", checkBoxThroat.isChecked)
            intent.putExtra("taste", checkBoxTaste.isChecked)
            intent.putExtra("headache", checkBoxHeadache.isChecked)
            intent.putExtra("vomiting", checkBoxVomiting.isChecked)
            intent.putExtra("diarrhea", checkBoxDiarrhea.isChecked)
            intent.putExtra("aches", checkBoxAches.isChecked)
            intent.putExtra("chills", checkBoxChills.isChecked)

            intent.putExtra("severity", seekBarSeverity.progress)
            intent.putExtra("duration", spinnerDuration.selectedItem.toString())

            startActivity(intent)
        }
    }

    private fun initializeViews() {
        checkBoxFever = findViewById(R.id.checkbox_fever)
        checkBoxFatigue = findViewById(R.id.checkbox_fatigue)
        checkBoxBreath = findViewById(R.id.checkbox_breath)
        checkBoxCough = findViewById(R.id.checkbox_cough)
        checkBoxPain = findViewById(R.id.checkbox_pain)
        checkBoxThroat = findViewById(R.id.checkbox_throat)
        checkBoxTaste = findViewById(R.id.checkbox_taste)
        checkBoxHeadache = findViewById(R.id.checkbox_headache)
        checkBoxVomiting = findViewById(R.id.checkbox_vomiting)
        checkBoxDiarrhea = findViewById(R.id.checkbox_diarrhea)
        checkBoxAches = findViewById(R.id.checkbox_aches)
        checkBoxChills = findViewById(R.id.checkbox_chills)
        seekBarSeverity = findViewById(R.id.seekbar_severity)
        spinnerDuration = findViewById(R.id.spinner_duration)
        continueButton = findViewById(R.id.btn_continue)

        // Listeners for the checkboxes can be set here similarly as you set for the fever checkbox
        // For brevity, I haven't set listeners for all the checkboxes, but you can add them as needed.
    }
}
