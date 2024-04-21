package com.example.simplechat.data.service

import com.example.simplechat.data.model.Message
import kotlinx.coroutines.flow.Flow

interface StorageService{
    suspend fun sendMessage(message : Message)
    fun getMessages() : Flow<List<Message?>>
}