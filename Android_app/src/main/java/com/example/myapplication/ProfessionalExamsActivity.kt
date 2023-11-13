package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast

class ProfessionalExamsActivity : AppCompatActivity() {

    private var selectedExam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_professional_exams)

        val examSearch: AutoCompleteTextView = findViewById(R.id.examSearch)
        val continueButton: Button = findViewById(R.id.continueButton)

        val examsMap = mapOf(
            "A" to arrayOf("AMC (Australian Medical Council Exam)", "ABFM (American Board of Family Medicine Exam)", "ABPN (American Board of Psychiatry and Neurology Exam)"),
            "B" to arrayOf("BMAT (Biomedical Admissions Test)", "BLS (Basic Life Support)"),
            "C" to arrayOf("COMLEX (Comprehensive Osteopathic Medical Licensing Examination)", "CCRN (Critical Care Registered Nurse Exam)", "CPN (Certified Pediatric Nurse Exam)"),
            "D" to arrayOf("DAT (Dental Admission Test)", "DNB (Diplomate of National Board India)"),
            "E" to arrayOf("ECFMG (Educational Commission for Foreign Medical Graduates Certification)", "EMR (Emergency Medical Responder Exam)"),
            "F" to arrayOf("FMGE (Foreign Medical Graduate Examination India)", "FNP (Family Nurse Practitioner Exam)"),
            "G" to arrayOf("GAMSAT (Graduate Australian Medical School Admissions Test)", "GRE (Graduate Record Examinations)"),
            "H" to arrayOf("HPCSA (Health Professions Council of South Africa Examinations)"),
            "I" to arrayOf("INBDE (Integrated National Board Dental Examination)", "IELTS (International English Language Testing System)"),
            "J" to arrayOf("JIPMER (Jawaharlal Institute of Postgraduate Medical Education and Research Entrance Exam)"),
            "K" to arrayOf("KMLE (Korean Medical Licensing Examination)", "KAPLAN (Kaplan Nursing School Admissions Test)"),
            "L" to arrayOf("LMCC (Licentiate of the Medical Council of Canada)"),
            "M" to arrayOf("MCAT (Medical College Admission Test)", "MCCQE (Medical Council of Canada Qualifying Examination)"),
            "N" to arrayOf("NBDE (National Board Dental Examinations)", "NCLEX (National Council Licensure Examination)"),
            "O" to arrayOf("OET (Occupational English Test)", "OSCE (Objective Structured Clinical Examination)"),
            "P" to arrayOf("PLAB (Professional and Linguistic Assessments Board UK)", "PANCE (Physician Assistant National Certifying Exam)"),
            "Q" to arrayOf("QE (Qualifying Examination Canada)"),
            "R" to arrayOf("RACGP (Royal Australian College of General Practitioners Fellowship Exam)", "RITE (Residency In-Service Training Exam)"),
            "S" to arrayOf("USMLE (United States Medical Licensing Examination Steps 1, 2 CK/CS, and 3)", "STEP (Structured Training and Experience Portfolio Australia)"),
            "T" to arrayOf("TSE (Test of Spoken English)"),
            "U" to arrayOf("UMAT (Undergraduate Medicine and Health Sciences Admission Test Australia and New Zealand)", "UKCAT (United Kingdom Clinical Aptitude Test)"),
            "V" to arrayOf("VQE (Visa Qualifying Examination)"),
            "W" to arrayOf("WBA (Workplace-Based Assessment Australia)"),
            "X" to arrayOf("X-Ray Certification Exam"),
            "Y" to arrayOf("There is no known professional medical exam that starts with the letter Y."),
            "Z" to arrayOf("There is no known professional medical exam that starts with the letter Z.")
        )


        val allExams = examsMap.values.flatMap { it.toList() }.toTypedArray()

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, allExams)
        examSearch.setAdapter(adapter)
        examSearch.threshold = 1

        examSearch.setOnItemClickListener { _, _, position, _ ->
            selectedExam = adapter.getItem(position)
            Toast.makeText(this, "Selected Exam: $selectedExam", Toast.LENGTH_SHORT).show()
        }

        continueButton.setOnClickListener {
            if (!selectedExam.isNullOrEmpty()) {
                val intent = Intent(this, AIChatActivity::class.java)
                intent.putExtra("selectedExam", selectedExam)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please select an exam.", Toast.LENGTH_SHORT).show()
            }
        }

        for ((letter, exams) in examsMap) {
            val spinnerId = resources.getIdentifier("spinner$letter", "id", packageName)
            if (spinnerId != 0) {
                val spinner: Spinner? = findViewById(spinnerId)
                if (spinner != null) {
                    val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, exams)
                    spinner.adapter = spinnerAdapter

                    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            selectedExam = parent?.getItemAtPosition(position).toString()
                            examSearch.setText(selectedExam)
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }

                    val buttonId = resources.getIdentifier("button$letter", "id", packageName)
                    if (buttonId != 0) {
                        val button: Button? = findViewById(buttonId)
                        button?.setOnClickListener {
                            if (spinner.visibility == View.INVISIBLE) {
                                spinner.visibility = View.VISIBLE
                                spinner.performClick()
                            } else {
                                spinner.visibility = View.INVISIBLE
                            }
                        }
                    } else {
                        Toast.makeText(this, "Resource ID for button$letter not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Spinner for letter $letter not found", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Resource ID for spinner$letter not found", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
