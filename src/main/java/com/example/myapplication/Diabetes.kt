package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Spinner

class Diabetes : AppCompatActivity() {

    private lateinit var checkBoxUrinate: CheckBox
    private lateinit var checkBoxThirsty: CheckBox
    private lateinit var checkBoxWeight: CheckBox
    private lateinit var checkBoxHungry: CheckBox
    private lateinit var checkBoxBlurry: CheckBox
    private lateinit var checkBoxNumb: CheckBox
    private lateinit var checkBoxTired: CheckBox
    private lateinit var checkBoxSkin: CheckBox

    private lateinit var seekBarSeverity: SeekBar
    private lateinit var spinnerDuration: Spinner
    private lateinit var continueButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diabetes)

        initializeViews()

        continueButton.setOnClickListener {
            val intent = Intent(this, AIChatActivity2::class.java)

            intent.putExtra("urinate", checkBoxUrinate.isChecked)
            intent.putExtra("thirsty", checkBoxThirsty.isChecked)
            intent.putExtra("weight", checkBoxWeight.isChecked)
            intent.putExtra("hungry", checkBoxHungry.isChecked)
            intent.putExtra("blurry", checkBoxBlurry.isChecked)
            intent.putExtra("numb", checkBoxNumb.isChecked)
            intent.putExtra("tired", checkBoxTired.isChecked)
            intent.putExtra("skin", checkBoxSkin.isChecked)

            intent.putExtra("severity", seekBarSeverity.progress)
            intent.putExtra("duration", spinnerDuration.selectedItem.toString())

            startActivity(intent)
        }
    }

    private fun initializeViews() {
        checkBoxUrinate = findViewById(R.id.checkbox_urinate)
        checkBoxThirsty = findViewById(R.id.checkbox_thirsty)
        checkBoxWeight = findViewById(R.id.checkbox_weight)
        checkBoxHungry = findViewById(R.id.checkbox_hungry)
        checkBoxBlurry = findViewById(R.id.checkbox_blurry)
        checkBoxNumb = findViewById(R.id.checkbox_numb)
        checkBoxTired = findViewById(R.id.checkbox_tired)
        checkBoxSkin = findViewById(R.id.checkbox_skin)

        seekBarSeverity = findViewById(R.id.seekbar_severity)
        spinnerDuration = findViewById(R.id.spinner_duration)
        continueButton = findViewById(R.id.btn_continue)

        // Listeners for the checkboxes can be set here similarly as you set for the fever checkbox
        // For brevity, I haven't set listeners for all the checkboxes, but you can add them as needed.
    }
}