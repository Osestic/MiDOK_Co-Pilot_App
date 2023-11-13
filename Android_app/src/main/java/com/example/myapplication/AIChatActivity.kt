package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import java.util.concurrent.TimeUnit

class AIChatActivity : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private val messages: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_chat)

        chatRecyclerView = findViewById(R.id.chatRecyclerView)


        // Setup RecyclerView
        val layoutManager = LinearLayoutManager(this)
        chatRecyclerView.layoutManager = layoutManager
        chatRecyclerView.adapter = ChatAdapter(messages)



        val symptoms = mutableListOf<String>()
        val symptomsList = arrayOf("fever", "fatigue", "breath", "cough", "pain", "throat", "taste", "headache", "vomiting", "diarrhea", "aches", "chills")
        for (symptom in symptomsList) {
            if (intent.getBooleanExtra(symptom, false)) {
                symptoms.add(symptom)
            }
        }
        val severity = intent.getIntExtra("severity", -1)
        val duration = intent.getStringExtra("duration") ?: "unknown"

        // Generate prompt and get response from GPT
        val prompt = generatePrompt(symptoms, severity, duration)
        chatWithGPT(prompt) { responseText ->
            runOnUiThread {
                messages.add("MIDOK: $responseText")
                chatRecyclerView.adapter?.notifyDataSetChanged()
                chatRecyclerView.scrollToPosition(messages.size - 1)
            }
        }
    }

    private fun generatePrompt(symptoms: List<String>, severity: Int, duration: String): String {
        // Customize this function based on how you want to generate the prompt
        val symptomsText = if (symptoms.isEmpty()) "no specific symptoms" else symptoms.joinToString(", ")
        return "The patient is experiencing $symptomsText with a severity of $severity for the past $duration. Can you provide closest related diseases? and put the possible treatment after each one"
          //return "Who are you?"
    }


    private fun chatWithGPT(prompt: String, callback: (String) -> Unit) {
        val apiUrl = "https://ai-hackathon-ap.onrender.com/chat"

        // Create OkHttpClient instance
        val client = OkHttpClient.Builder()
            .connectTimeout(600, TimeUnit.SECONDS)
            .writeTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        // Create request body with JSON content
        val jsonBody = JSONObject()
        try {
            jsonBody.put("message", prompt)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val requestBody = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), jsonBody.toString())

        // Create a request
        val request = Request.Builder()
            .url(apiUrl)
            .post(requestBody)
            .build()

        // Enqueue the request
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@AIChatActivity, "Failed to send message", Toast.LENGTH_SHORT).show()
                    Log.e("AIChatActivity", "Failed to send message", e)
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    if (responseBody != null) {
                        try {
                            val jsonResponse = JSONObject(responseBody)
                            val responseMessage = jsonResponse.getString("response")
                            callback(responseMessage)
                        } catch (e: JSONException) {
                            runOnUiThread {
                                Toast.makeText(this@AIChatActivity, responseBody, Toast.LENGTH_SHORT).show()
                                Log.e("AIChatActivity", "Failed to parse response", e)
                            }
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@AIChatActivity, "Response body is null", Toast.LENGTH_SHORT).show()
                            Log.e("AIChatActivity", "Response body is null")
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@AIChatActivity, "Failed to get response", Toast.LENGTH_SHORT).show()
                        Log.e("AIChatActivity", "Failed to get response")
                    }
                }
            }
        })
    }


}
