package com.example.simplechat.ui.screens.chat

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val storageService: StorageService
) : ViewModel() {

    var messages : Flow<List<Message?>> = getListMessages()

    private var _messageState = mutableStateOf("")
    val messageState : State<String> = _messageState

    private fun getListMessages() : Flow<List<Message?>> = storageService.getMessages()

    fun onMessageChange(newValue: String) {
        _messageState.value = newValue
    }

    fun sendMessage(){
        val message = Message(body = _messageState.value)
        viewModelScope.launch {
            try {
                storageService.sendMessage(message)
            }catch (e:Exception){
                Log.e("error","error send message D:")
            }
        }
    }
}