package com.apexascent.assignment.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apexascent.assignment.data.TarotCard
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ResultsScreen(firstCard: TarotCard, secondCard: TarotCard, thirdCard: TarotCard){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(brush = Brush.linearGradient(
                colors = listOf(Color(0xFF4B134F), Color(0xFFEFCEAD)), // Dark Purple to Beige
            ))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Interpretation of Results",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        )
        Text(text = "Your Cards: ")
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(id = firstCard.imageId),
                contentDescription = "Tarot Card",
                modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp)
                    .weight(1f) )
            Image(painter = painterResource(id = secondCard.imageId),
                contentDescription = "Tarot Card",
                modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp)
                    .weight(1f) )
            Image(painter = painterResource(id = thirdCard.imageId),
                contentDescription = "Tarot Card",
                modifier = Modifier
                    .padding(8.dp)
                    .size(150.dp)
                    .weight(1f) )
        }
        Text(text ="Interpretations: ")

        val interpretations = firstCard.imageMeaning + secondCard.imageMeaning + thirdCard.imageMeaning
        for (interpreation in interpretations){
            Text(text = "- $interpreation")
        }
        Spacer(modifier = Modifier.height(20.dp))
      



    }



}