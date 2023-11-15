package com.hado.jetpackcomposelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hado.jetpackcomposelogin.ui.theme.JetpackComposeLoginTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeLoginTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painterResource(id = R.drawable.background_page),
                            contentScale = ContentScale.FillWidth
                        ),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "Login Activity",
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 32.dp, bottom = 80.dp),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()

                            .background(
                                color = Color(android.graphics.Color.parseColor("#e0e0e0")),
                                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
                            )
                            .padding(32.dp)
                    ) {
                        // Email
                        Text(
                            text = "Email",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp),
                            color = Color.Black
                        )

                        var email by rememberSaveable {
                            mutableStateOf("")
                        }
                        TextField(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            placeholder = {
                                Text("example@gmail.com")
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.White,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                textColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                                unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .background(Color.White, CircleShape)
                        )

                        // Password
                        Text(
                            text = "Password",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp),
                            color = Color.Black
                        )

                        var password by rememberSaveable {
                            mutableStateOf("")
                        }
                        TextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            placeholder = {
                                Text("Type your password")
                            },
                            shape = RoundedCornerShape(10.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.White,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                textColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                                unfocusedLabelColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                                .background(Color.White, CircleShape)
                        )

                        // Labels
                        Text(
                            text = "Forgot your password? Recover it",
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            textAlign = TextAlign.Center,
                            color = Color(android.graphics.Color.parseColor("#5e5e5e"))
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                               modifier = Modifier
                                   .height(1.dp)
                                   .weight(1f)
                                   .background(Color(android.graphics.Color.parseColor("#4d4d4d")))
                            )
                            Text(
                                text = "Or Login with",
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                                color = Color(android.graphics.Color.parseColor("#4d4d4d"))
                            )
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .weight(1f)
                                    .background(Color(android.graphics.Color.parseColor("#4d4d4d")))
                            )
                        }

                        // Buttons
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp, end = 5.dp)
                                    .weight(0.5f)
                                    .height(55.dp),
                                border = BorderStroke(
                                    1.dp,
                                    Color(android.graphics.Color.parseColor("#4d4d4d"))
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.15f),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.google),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(25.dp)
                                    )
                                }
                                Text(
                                    text = "Google",
                                    color = Color(android.graphics.Color.parseColor("#2f4f86")),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(0.7f),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp, start = 5.dp)
                                    .weight(0.5f)
                                    .height(55.dp),
                                border = BorderStroke(
                                    1.dp,
                                    Color(android.graphics.Color.parseColor("#4d4d4d"))
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,

                                ),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(0.15f),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.facebook),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(25.dp)
                                    )
                                }
                                Text(
                                    text = "Facebook",
                                    color = Color(android.graphics.Color.parseColor("#2f4f86")),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(0.7f),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(top = 16.dp, bottom = 16.dp)
                                .fillMaxWidth()
                                .height(55.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(android.graphics.Color.parseColor("#fa951a"))
                            ),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "Login",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}