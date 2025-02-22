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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.apexascent.assignment.R
import com.apexascent.assignment.data.TarotCard
import com.apexascent.assignment.database.TarotDatabase
import com.apexascent.assignment.database.TarotResult
import com.apexascent.assignment.screens.destinations.HistoryScreenDestination
import com.apexascent.assignment.screens.destinations.ResultsScreenDestination
import com.apexascent.assignment.screens.effects.FloatingStars
import com.apexascent.assignment.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
@Destination(start = true)
@Composable
fun MainScreen(navigator: DestinationsNavigator) {
    // Getting the tarot deck from our constants object
    var tarotDeck by remember { mutableStateOf(Constants.tarotDeck.shuffled()) }

    // Creating a database instance
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context = context,
            TarotDatabase::class.java, "tarot_result_database"
        ).build()
    }

    //Creating the coroutine scope to run the database suspend functions
    val scope = rememberCoroutineScope()
    var userQuestion by remember { mutableStateOf("") }
    var selectedCards by remember { mutableStateOf<List<TarotCard>>(emptyList()) }
    var isShuffling by remember { mutableStateOf(false) }

    // Creating sound-related variables
    var isPlaying by remember { mutableStateOf(false) }
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.mysterious) }
    // Set the MediaPlayer to loop indefinitely
    mediaPlayer.isLooping = true

    // Handle MediaPlayer lifecycle
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release() // Release MediaPlayer when the composable is removed
        }
    }



    //UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF4B134F), Color(0xFFEFCEAD)), // Dark Purple to Beige
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
                        })
                Icon(
                    tint = Color.White,
                    painter = painterResource(if(isPlaying) R.drawable.ic_sound_on else R.drawable.ic_sound_off ),
                    contentDescription = "Sound",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            if (!isPlaying){
                                mediaPlayer.start()
                                isPlaying = true
                            }
                            else {
                                mediaPlayer.pause()
                                isPlaying = false
                            }


                        })

            }


        }


        OutlinedTextField(
            value = userQuestion,
            onValueChange = { userQuestion = it },
            label = { Text("Enter your question") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                scope.launch {
                    isShuffling = true
                    repeat(5) { // Shuffle 5 times for effect
                        tarotDeck = tarotDeck.shuffled()
                        delay(500) // Delay between shuffles
                    }
                    isShuffling = false
                }
            },
            enabled = !isShuffling,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Shuffle Cards")
        }

        // Equivalent to RecyclerView in XML
        Box(modifier = Modifier.weight(1f)) {
            FloatingStars()

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize(1f)) {
                items(items =tarotDeck, key = { card -> card.imageId }) { card ->
                    // A holder for each TarotCard instance
                    TarotCardItem(
                        card = card,
                        isSelected = selectedCards.contains(card),
                        isShuffling = isShuffling,
                        onClick = {
                            if (selectedCards.contains(card)) {
                                selectedCards = selectedCards - card
                            } else if (selectedCards.size < 3) {
                                selectedCards = selectedCards + card
                            }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
            onClick = {
                val currentTime = getCurrentTime()
                scope.launch {
                    db.dao.saveResult(TarotResult(0,selectedCards, userQuestion, currentTime))
                }
                navigator.navigate(ResultsScreenDestination(selectedCards[0], selectedCards[1], selectedCards[2]))




            },
            enabled = selectedCards.size == 3 && userQuestion.isNotEmpty()
        ) {
            Text("Reveal & Continue")
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}
//A simple UI desin for each tarot card.
@Composable
fun TarotCardItem(card: TarotCard, isSelected: Boolean, isShuffling: Boolean, onClick: () -> Unit) {
    val cardDrawable = if (isSelected) card.imageId else R.drawable.card_backs

    // Flip animation when selected
    val flipRotation by animateFloatAsState(
        targetValue = if (isSelected) 180f else 0f,
        animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
    )
    // Shuffle animation, we move each card by a random amount of pixel in each direction, the same with rotation
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val cardRotation = remember { Animatable(0f) } // This is added to make the cards rotate when moving horizontally and vertically

    LaunchedEffect(isShuffling) {
        if(isShuffling){
            launch {
                offsetX.animateTo(
                    targetValue = (-100..100).random().toFloat(),
                    animationSpec = tween(durationMillis =(300), easing = LinearOutSlowInEasing)
                )
                offsetX.animateTo(0f, animationSpec = tween(200)) // We come back to the initial position
            }

            launch {
                offsetY.animateTo(
                    targetValue = (-100..100).random().toFloat(),
                    animationSpec = tween(durationMillis = (300), easing = LinearOutSlowInEasing)
                )
                offsetY.animateTo(0f, animationSpec = tween(200))
            }

            launch {
                cardRotation.animateTo(
                    targetValue = (-180..180).random().toFloat(),
                    animationSpec = tween(durationMillis = (300), easing = LinearOutSlowInEasing)
                )
                cardRotation.animateTo(0f, animationSpec = tween(200))
            }
        }else {
            launch {
                offsetX.animateTo(0f, animationSpec = tween(200))
                offsetY.animateTo(0f, animationSpec = tween(200))
                cardRotation.animateTo(0f, animationSpec = tween(200))



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
            .rotate(cardRotation.value)
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
fun getCurrentTime(): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return dateFormat.format(Date())
}
