package com.hado.jetpackkotlinfundamentals

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(
    name = "Core Jetpack Composables",
    device = Devices.PIXEL_3,
    showSystemUi = true
)
@Composable
fun CoreJetpackComposables() {
    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        Column {
            // Text composable
            val name = "John"
            Text(
                text = "Greeting $name!",
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                color = Color.Magenta,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )

            // Button composable
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.Red
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Press Me")
            }

            // TextField composable
            val textState = remember { mutableStateOf("") }
            TextField(
                value = textState.value,
                onValueChange = { newValue ->
                    textState.value = newValue
                },
                label = {
                    Text("Your name")
                }
            )

            // Image composable
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "My app icon",
                contentScale = ContentScale.Fit
            )

            // Modifier
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color.Green)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(size = 20.dp))
                    .background(Color.Red)
            )

            // Row
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("1", fontSize = 36.sp)
                Text("2", fontSize = 36.sp)
                Text("3", fontSize = 36.sp)
                Text("4", fontSize = 36.sp)
            }

            // Column
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize(),
            ) {
                Text("John", fontSize = 36.sp)
                Text("Amanda", fontSize = 36.sp)
                Text("Mike", fontSize = 36.sp)
                Text("Alma", fontSize = 36.sp)
            }
        }

        Box {
            // Box - Floating button
            Surface(
                modifier = Modifier.size(32.dp),
                color = Color.Green,
                shape = CircleShape,
                content = {}
            )
            Text("+", modifier = Modifier.align(Alignment.Center))
        }
    }
}