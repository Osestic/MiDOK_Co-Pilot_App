package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<String>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageTextView: TextView = itemView.findViewById(R.id.messageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat_message_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.messageTextView.text = messages[position]
    }
}
