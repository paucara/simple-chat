package com.example.simplechat.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Message(
    val id : String = "",
    val body: String = "",
    val timestamp: Long = System.currentTimeMillis()
)