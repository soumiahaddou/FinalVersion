package com.apexascent.assignment.screens

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.apexascent.assignment.util.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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

    //UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(
                colors = listOf(Color(0xFF4B134F), Color(0xFFEFCEAD)), // Dark Purple to Beige
            ))
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
            Icon(
                tint = Color.White,
                painter = painterResource(R.drawable.ic_history),
                contentDescription = "History",
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navigator.navigate(HistoryScreenDestination())
                    })

        }


        OutlinedTextField(
            value = userQuestion,
            onValueChange = { userQuestion = it },
            label = { Text("Enter your question") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Equivalent to RecyclerView in XML
        Box(modifier = Modifier.weight(1f)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.fillMaxSize(1f)
            ) {
                items(tarotDeck) { card ->
                    // A holder for each TarotCard instance
                    TarotCardItem(
                        card = card,
                        isSelected = selectedCards.contains(card),
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
//A simple holder for each tarot card.
@Composable
fun TarotCardItem(card: TarotCard, isSelected: Boolean, onClick: () -> Unit) {
    val cardDrawable = if (isSelected) card.imageId else R.drawable.card_backs

    Image(
        painter = painterResource(id = cardDrawable),
        contentDescription = "Tarot Card",
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clickable { onClick() }
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
