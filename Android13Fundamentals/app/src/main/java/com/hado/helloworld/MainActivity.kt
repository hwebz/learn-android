package com.hado.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hado.helloworld.components.ButtonWithIcon
import com.hado.helloworld.components.CityApp
import com.hado.helloworld.components.CornerCutShapeButton
import com.hado.helloworld.components.EditTextExample
import com.hado.helloworld.components.ElevatedButtonExample
import com.hado.helloworld.components.ImageViewExample
import com.hado.helloworld.components.NotOutlinedEditTextExample
import com.hado.helloworld.components.RoundCornerShapeButton
import com.hado.helloworld.pagerexample.CityTabCarousel
import com.hado.helloworld.ui.theme.HelloWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Basic components
//                        Greeting("Android 132")
//                        SampleButton()
//                        EditTextExample()
//                        NotOutlinedEditTextExample()
//                        ButtonWithIcon()
//                        CornerCutShapeButton()
//                        RoundCornerShapeButton()
//                        ElevatedButtonExample()
//                        ImageViewExample()

                        // List view components
//                        CityApp()

                        // Pager
                        CityTabCarousel()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Test Test test $name!",
            modifier = modifier
    )
}

@Composable
fun SampleButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Color.Blue),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Gray,
            containerColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = R.string.click_me),
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 30.dp, vertical = 6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting("Android")
            SampleButton()
            EditTextExample()
            NotOutlinedEditTextExample()
            ButtonWithIcon()
            CornerCutShapeButton()
            RoundCornerShapeButton()
            ElevatedButtonExample()
            ImageViewExample()
        }
    }
}