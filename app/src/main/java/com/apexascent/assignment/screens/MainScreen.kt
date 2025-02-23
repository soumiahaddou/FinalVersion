package com.apexascent.assignment.screens


import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.apexascent.assignment.Events.MainScreenEvent
import com.apexascent.assignment.R
import com.apexascent.assignment.data.TarotCard
import com.apexascent.assignment.database.TarotDatabase
import com.apexascent.assignment.database.TarotResult
import com.apexascent.assignment.screens.destinations.ChatScreenDestination
import com.apexascent.assignment.screens.destinations.HistoryScreenDestination
import com.apexascent.assignment.screens.destinations.ResultsScreenDestination
import com.apexascent.assignment.screens.effects.FloatingStars
import com.apexascent.assignment.ui.theme.Beige
import com.apexascent.assignment.ui.theme.Pink40
import com.apexascent.assignment.ui.theme.Purple80
import com.apexascent.assignment.viewModels.MainViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun MainScreen(navigator: DestinationsNavigator) {
    val viewModel = viewModel<MainViewModel>()
    val state by viewModel.mainState.collectAsState()
    // Creating a database instance
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context = context,
            TarotDatabase::class.java, "tarot_result_database"
        ).build()
    }
    // Creating sound-related variables
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.mysterious) }
    // Set the MediaPlayer to loop indefinitely
    mediaPlayer.isLooping = true
    // Handle MediaPlayer lifecycle
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release() // Release MediaPlayer when the composable is removed
        }
    }
    AIDialog(
        showDialog = state.showDialog,
        onDismiss = {
            viewModel.onEvent(MainScreenEvent.DismissDialog(db.dao))
            },
        onContinue = {
            viewModel.onEvent(MainScreenEvent.ContinueChat(db.dao))
            navigator.navigate(
                ChatScreenDestination(state.userPrompt, state.aiResponse)
            )

        },
        aiResponse = state.aiResponse ,
        isLoading = state.isLoading
    )
    //UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Pink40, Beige) // Dark Purple to Beige
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
            ) {
            Text(
                color = Color.White,
                text = "Ask a Question",
                fontSize = 20.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp))
            //Clickable Icon to navigate to the hisotry screen
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(
                    tint = Color.White,
                    painter = painterResource(R.drawable.ic_history),
                    contentDescription = "History",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            navigator.navigate(HistoryScreenDestination())
                            //navigator.navigate(ChatScreenDestination())
                        })
                Icon(
                    tint = Color.White,
                    painter = painterResource(if(state.isPlaying) R.drawable.ic_sound_on else R.drawable.ic_sound_off ),
                    contentDescription = "Sound",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            if (!state.isPlaying) {
                                viewModel.onEvent(MainScreenEvent.PlayAudio(mediaPlayer))
                            } else {
                                viewModel.onEvent(MainScreenEvent.StopAudio(mediaPlayer))
                            }
                        })
            }
        }
        OutlinedTextField(
            value = state.userQuestion,
            onValueChange = { viewModel.onEvent(MainScreenEvent.UpdateUserQuestion(it)) },
            label = { Text("Enter your question") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.onEvent(MainScreenEvent.ShuffleCards)
            },
            enabled =!state.isShuffling ,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Shuffle Cards")
        }

        Box(modifier = Modifier.weight(1f)) {
            FloatingStars()
            // Equivalent to RecyclerView in XML

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize(1f)) {
                items(items =state.tarotDeck, key = { card -> card.cardId }) { card ->
                    // A holder for each TarotCard instance
                    TarotCardItem(
                        card = card,
                        isSelected = state.selectedCards.contains(card),
                        isShuffling = state.isShuffling,
                        onClick = {
                            viewModel.onEvent(MainScreenEvent.SelectTarotCard(card))
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(modifier = Modifier
                .weight(1f),
                onClick = {
                    val currentTime = viewModel.getCurrentTime()
                    viewModel.onEvent(MainScreenEvent.SaveTarotResult(TarotResult(0, state.selectedCards, state.userQuestion, currentTime), db.dao))
                    navigator.navigate(ResultsScreenDestination(state.selectedCards[0], state.selectedCards[1], state.selectedCards[2],state.userQuestion) )
                },
                enabled = state.selectedCards.size == 3 && state.userQuestion.isNotEmpty()
            ) {
                Text("Reveal")
            }
            Button(modifier = Modifier
                .weight(1f),
                onClick = {
                    viewModel.onEvent(MainScreenEvent.ShowDialog)
                    viewModel.onEvent(MainScreenEvent.FetchAIResponse)



                },
                enabled = state.selectedCards.size == 3 && state.userQuestion.isNotEmpty()
            ) {
                Text("Reveal with AI")
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

    }
}
//A simple UI desin for each tarot card.
@Composable
fun TarotCardItem(card: TarotCard, isSelected: Boolean, isShuffling: Boolean, onClick: () -> Unit) {
    val cardDrawable = if (isSelected) card.cardId else R.drawable.card_backs

    // Flip animation when selected
    val flipRotation by animateFloatAsState(
        targetValue = if (isSelected) 180f else 0f,
        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
    )
    // Shuffle animation, we move each card by a random amount of pixel in each direction, the same with rotation
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    LaunchedEffect(isShuffling) {
        if(isShuffling){

            launch {
                offsetX.animateTo(
                    targetValue = (-100..100).random().toFloat(),
                    animationSpec = tween(durationMillis =(500), easing = LinearOutSlowInEasing)
                )
                offsetX.animateTo(0f, animationSpec = tween(200)) // We come back to the initial position
            }

            launch {
                offsetY.animateTo(
                    targetValue = (-100..100).random().toFloat(),
                    animationSpec = tween(durationMillis = (500), easing = LinearOutSlowInEasing)
                )
                offsetY.animateTo(0f, animationSpec = tween(200))
            }

        }else {
            launch {
                offsetX.animateTo(0f, animationSpec = tween(200))
                offsetY.animateTo(0f, animationSpec = tween(200))



            }

        }

    }

    Image(
        painter = painterResource(id = cardDrawable),
        contentDescription = "Tarot Card",
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clickable { onClick() }
            .offset(offsetX.value.dp, offsetY.value.dp)
            .graphicsLayer {
                rotationY = flipRotation
            }
            .border(
                2.dp,
                if (isSelected) Color.Green else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
    )
}
@Composable
fun AIDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onContinue: () -> Unit,
    aiResponse: String?,
    isLoading: Boolean
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("AI Interpretation") },
            text = {
                if (isLoading) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Generating response...")
                    }
                } else {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .heightIn(max = 400.dp)
                    ) {
                        Text(aiResponse ?: "No response available")
                    }
                }
            },
            confirmButton = {
                if (!isLoading) {
                    Button(onClick = onContinue) {
                        Text("Continue Chat")
                    }
                }
            },
            dismissButton = {
                if (!isLoading) {
                    Button(onClick = onDismiss) {
                        Text("Skip")
                    }
                }
            }
        )
    }
}
