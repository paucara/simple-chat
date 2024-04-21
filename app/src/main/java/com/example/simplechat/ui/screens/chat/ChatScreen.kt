package com.example.simplechat.ui.screens.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.simplechat.data.model.Message
import com.example.simplechat.ui.theme.signikaFamily

@Composable
fun ChatScreen(onNavigateToSettings: () -> Unit, viewModel: ChatViewModel = hiltViewModel()) {

    val messages = viewModel.messages.collectAsState(initial = null)
    val message = viewModel.messageState.value

    Scaffold(
        modifier = Modifier.padding(start = 15.dp),
        topBar = { ChatTopBar(onNavigateToSettings) },
        bottomBar = { ChatBottomBar(viewModel::onMessageChange, viewModel::sendMessage, message) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            messages.value?.let { ChatContent(it) }
        }
    }
}

@Composable
fun ChatContent(messages: List<Message?>) {

    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = messages) {
        lazyListState.animateScrollToItem(index = messages.lastIndex)
    }

    LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {
        items(messages) { message ->
            message?.let { MessageBubble(message = message.body) }
        }
    }
}


@Composable
fun ChatBottomBar(
    onMessageChange: (String) -> Unit,
    sendMessage: () -> Unit,
    message: String
) {

    val enable = message.isNotBlank()

    Row(
        modifier = Modifier.padding(bottom = 10.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = message,
            onValueChange = { onMessageChange(it) },
            modifier = Modifier.weight(5F),
            shape = MaterialTheme.shapes.extraLarge,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        IconButton(onClick = { sendMessage() }, enabled = enable) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier.weight(1F)
            )
        }
    }
}

@Composable
fun ChatTopBar(onNavigateToSettings: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        Text(
            text = "SimpleChat", modifier = Modifier
                .weight(5F)
                .height(40.dp), fontFamily = signikaFamily, fontSize = 25.sp
        )
        IconButton(onClick = { onNavigateToSettings() }, modifier = Modifier.weight(1F)) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }
    }
}


@Composable
fun MessageBubble(message: String) {
    Box(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        Text(text = message, fontFamily = signikaFamily, fontWeight = FontWeight.Light)
    }
}

@Preview(showSystemUi = true)
@Composable
fun ChatPreview() {
    Column {
        repeat(5) {
            MessageBubble(message = "It is a long established fact that a reader")
        }
    }
}

