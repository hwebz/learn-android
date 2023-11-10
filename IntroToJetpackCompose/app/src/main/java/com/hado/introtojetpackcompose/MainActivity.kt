package com.hado.introtojetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hado.introtojetpackcompose.ui.theme.IntroToJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroToJetpackComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var moneyCounter by remember {
        mutableStateOf(0)
    }
    var isEnough: Boolean = moneyCounter >= 1000
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = Color(0xFF546E7A)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (isEnough) "Yay!!!!" else "$${moneyCounter}",
                style = TextStyle(
                    color = if (isEnough) Color.Green else Color.White,
                    fontSize = if (isEnough) 80.sp else 35.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            CounterButton(moneyCounter = moneyCounter) { newValue ->
                moneyCounter = newValue
            }
        }
    }
}

@Composable
fun CounterButton(moneyCounter: Int = 0, updateMoneyCounter: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(200.dp)
            .clip(CircleShape)
            .clickable {
                updateMoneyCounter(moneyCounter + 100)
                Log.d("Tap", "CreateCircle: Tap")
            },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Tap to add $100", modifier = Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntroToJetpackComposeTheme {
        Column {
            MyApp()
        }
    }
}