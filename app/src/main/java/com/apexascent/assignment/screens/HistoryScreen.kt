package com.apexascent.assignment.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.apexascent.assignment.database.TarotDatabase
import com.apexascent.assignment.database.TarotResult
import com.apexascent.assignment.screens.destinations.ResultsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun HistoryScreen(navigator: DestinationsNavigator){
    val context = LocalContext.current
    // Creating an instance of the database
    val db = remember {
        Room.databaseBuilder(
            context,
            TarotDatabase::class.java,
            "tarot_result_database"
        ).build()
    }
    var savedResults by remember { mutableStateOf<List<TarotResult>>(emptyList())    }
    val scope = rememberCoroutineScope()
    // Retrieving saved results
    scope.launch{
        savedResults = db.dao.getAllResults()
    }
    // UI
    Column(
        modifier = Modifier
            .fillMaxSize().background(brush = Brush.linearGradient(
                colors = listOf(Color(0xFF4B134F), Color(0xFFEFCEAD)), // Dark Purple to Beige
            ))
            .padding(16.dp) // Optional padding
    ) {
        Text(
            text = "History",
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Box(modifier = Modifier.weight(1f)) { // Ensures LazyColumn gets remaining space
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(savedResults) { tarotResult ->
                    resultCard(tarotResult = tarotResult) {
                        val firstCard = tarotResult.result[0]
                        val secondCard = tarotResult.result[1]
                        val thirdCard = tarotResult.result[2]
                        //Results are clickable and will send you to the ResultsScreen for a detailed interpretation

                        navigator.navigate(ResultsScreenDestination(firstCard, secondCard, thirdCard))
                    }
                }
            }
        }

        Button(
            onClick = {
                // Clearing all the saved results from the database and updating the UI
                scope.launch {
                    db.dao.clearAll()
                }
                savedResults = emptyList()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            enabled = savedResults.isNotEmpty()
        ) {
            Text(text = "Clear History")
        }
    }



}
// A simple card to show the result information
@Composable
fun resultCard(tarotResult: TarotResult, onClick:() ->Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(8.dp)
        ,
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFCEAD)),
        elevation =CardDefaults.cardElevation(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)){
            Text(text ="Question: ${tarotResult.userQuestion}")
            Row(modifier = Modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = tarotResult.result[0].imageId),
                    contentDescription = "Tarot Card",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(100.dp)
                        .weight(1f) )
                Image(painter = painterResource(id = tarotResult.result[1].imageId),
                    contentDescription = "Tarot Card",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(100.dp)
                        .weight(1f) )
                Image(painter = painterResource(tarotResult.result[0].imageId),
                    contentDescription = "Tarot Card",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(100.dp)
                        .weight(1f) )
            }
            val resultSummary = tarotResult.result[0].imageMeaning.joinToString(",")
            Text(text ="Interpretation: $resultSummary",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(text = "Date: ${tarotResult.time}")



        }


    }

}