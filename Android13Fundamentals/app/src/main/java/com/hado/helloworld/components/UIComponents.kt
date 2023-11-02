package com.hado.helloworld.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hado.helloworld.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextExample() {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = {
            Text(stringResource(id = R.string.sample))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Black
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotOutlinedEditTextExample() {
    TextField(
        value = "",
        onValueChange = {},
        label = {
            Text(stringResource(id = R.string.sample))
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 16.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.Black
        )
    )
}

@Composable
fun ButtonWithIcon() {
    Button(onClick = {}) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(id = R.string.shop),
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = stringResource(id = R.string.buy),
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun CornerCutShapeButton() {
    Button(onClick = { }, shape = CutCornerShape(10)) {
        Text(text = stringResource(id = R.string.cornerButton))
    }
}

@Composable
fun RoundCornerShapeButton() {
    Button(onClick = {}, shape = RoundedCornerShape(10.dp)) {
        Text(text = stringResource(id = R.string.rounded))
    }
}

@Composable
fun ElevatedButtonExample() {
    Button(
        onClick = {},
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        )
    ) {
        Text(text = stringResource(id = R.string.elevated))
    }
}

@Composable
fun ImageViewExample() {
    Image(
        painter = painterResource(id = R.drawable.natural),
        contentDescription = stringResource(id = R.string.image),
        modifier = Modifier.fillMaxWidth()
    )
}