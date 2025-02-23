package com.apexascent.assignment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apexascent.assignment.Events.ChatEvent
import com.apexascent.assignment.ui.theme.Beige
import com.apexascent.assignment.ui.theme.Pink40
import com.apexascent.assignment.ui.theme.Purple80
import com.apexascent.assignment.viewModels.ChatViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ChatScreen(initialUserPrompt: String, initialAiResponse:String){
    Scaffold(topBar = {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Purple80)
            .height(70.dp)
            .padding(horizontal = 16.dp)
        ){
            Text(
                text = "AI Result Interpretation",
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.BottomCenter)
            )

        }
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
        ChatView(paddingValues = innerPadding, initialUserPrompt = initialUserPrompt, initialAiResponse = initialAiResponse)

    }

}

@Composable
fun ChatView(paddingValues: PaddingValues, initialUserPrompt: String, initialAiResponse:String){

    val chatViewModel = viewModel<ChatViewModel>()
    val chatState = chatViewModel.chatState.collectAsState().value
    LaunchedEffect(Unit) {
        chatViewModel.onEvent(ChatEvent.InitializeChat(initialUserPrompt = initialUserPrompt, initialAiResponse = initialAiResponse))
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Bottom
    )

    {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            reverseLayout = true
        ) {
            itemsIndexed(chatState.chatList){index, chat ->
                if(chat.isFromUser){
                    userChatItem(prompt = chat.prompt)
                }
                else{
                    modelChatItem(reponse = chat.prompt)
                }


            }

        }
        if (chatState.isLoading){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("AI is typing...")
                }

        }


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = chatState.prompt,
                onValueChange = {
                    chatViewModel.onEvent(ChatEvent.updatePrompt(it))
                },
                placeholder = { Text(text = "Type your prompt")},
                modifier = Modifier
                    .weight(1f)

            )
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        chatViewModel.onEvent(ChatEvent.sendPrompt(chatState.prompt))
                    },
                tint = MaterialTheme.colorScheme.primary)


        }
        Spacer(modifier = Modifier.height(20.dp))

    }

}


@Composable
fun userChatItem(prompt:String){
    Column(modifier = Modifier.padding(start = 50.dp, bottom = 22.dp)) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Purple80)
            .padding(16.dp),
            text = prompt,
            fontSize = 17.sp,
            color = Color.Black)
    }
}
@Composable
fun modelChatItem(reponse:String){
    Column(modifier = Modifier.padding(end = 50.dp, bottom = 22.dp)) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .padding(16.dp),
            text = reponse,
            fontSize = 17.sp,
            color = Color.White)
    }
}