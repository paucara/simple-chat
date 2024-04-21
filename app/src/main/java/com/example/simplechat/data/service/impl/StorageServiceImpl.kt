package com.example.simplechat.data.service.impl

import com.example.simplechat.data.model.Message
import com.example.simplechat.data.service.StorageService
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import com.google.firebase.database.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val database: FirebaseDatabase
) : StorageService {

    override fun getMessages(): Flow<List<Message?>> {
        val ref = database.getReference("chats/0/messages")
        return ref.orderByChild("timestamp").snapshots.map { snapshot ->
            snapshot.children.mapNotNull { it.getValue<Message>() }
        }
    }

    override suspend fun sendMessage(message: Message) {
        val messagesRef = database.getReference("chats").child("0").child("messages")
        val idMessage = messagesRef.push().key ?: return
        val messagesMap = mapOf(
            "id" to idMessage,
            "body" to message.body,
            "timestamp" to message.timestamp
        )
        messagesRef.child(idMessage).setValue(messagesMap).await()
    }

}