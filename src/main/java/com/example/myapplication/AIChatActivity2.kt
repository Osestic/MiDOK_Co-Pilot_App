package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class AIChatActivity2 : AppCompatActivity() {

    private lateinit var chatRecyclerView: RecyclerView
    private val messages: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aichat2)

        chatRecyclerView = findViewById(R.id.chatRecyclerView)

        val layoutManager = LinearLayoutManager(this)
        chatRecyclerView.layoutManager = layoutManager
        chatRecyclerView.adapter = ChatAdapter(messages)

        val symptoms2 = mutableListOf<String>()
        val symptomsList2 = arrayOf("urinate", "thirsty", "weight", "hungry", "blurry", "numb", "tired", "skin")
        for (symptom in symptomsList2) {
            if (intent.getBooleanExtra(symptom, false)) {
                symptoms2.add(symptom)
            }
        }

        val severity = intent.getIntExtra("severity", -1)
        val duration = intent.getStringExtra("duration") ?: "unknown"

        val prompt = generatePrompt(symptoms2, severity, duration)
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
        return "I think my spouse is experiencing diabetes, my spouse has $symptomsText with a severity of $severity for the past $duration. Do the symptoms my spouse has match with diabetes symptoms. if yes, list in simple terms some possible treatment"
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
                    Toast.makeText(this@AIChatActivity2, "Failed to send message", Toast.LENGTH_SHORT).show()
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
                                Toast.makeText(this@AIChatActivity2, responseBody, Toast.LENGTH_SHORT).show()
                                Log.e("AIChatActivity", "Failed to parse response", e)
                            }
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@AIChatActivity2, "Response body is null", Toast.LENGTH_SHORT).show()
                            Log.e("AIChatActivity", "Response body is null")
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@AIChatActivity2, "Failed to get response", Toast.LENGTH_SHORT).show()
                        Log.e("AIChatActivity", "Failed to get response")
                    }
                }
            }
        })
    }


}