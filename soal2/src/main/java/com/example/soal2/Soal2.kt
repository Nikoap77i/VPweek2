package com.example.soal2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Poppins = FontFamily(
    Font(R.font.poppins, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
)

private val CardNavy = Color(0xFF2E3554)
private val FieldBlue = Color(0xFFD6ECFA)
private val FieldTextBlue = Color(0xFF3C4A5C)

@Composable
fun TravelReviewScreen() {
    var q1 by remember { mutableStateOf("") }
    var q2 by remember { mutableStateOf("") }
    var q3 by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.eifel_probolinggo),
                contentDescription = "Header Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .offset(y = (-28).dp)
                    .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                    .background(CardNavy)
                    .padding(horizontal = 24.dp, vertical = 28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Nglencer Sek Le!",
                        color = Color.White,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        "Menara Eiffel Kearifan Lokal",
                        color = Color.White,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                    Text(
                        "Kota Probolinggo, Jawa Timur",
                        color = Color.White.copy(alpha = 0.7f),
                        fontFamily = Poppins,
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        repeat(5) {
                            Icon(Icons.Filled.Star, contentDescription = null, tint = Color(0xFFFFC94A), modifier = Modifier.size(18.dp))
                        }
                        Spacer(Modifier.width(8.dp))
                        Text("5.0", color = Color.White, fontFamily = Poppins, fontWeight = FontWeight.Medium, fontSize = 14.sp)
                    }

                    Spacer(Modifier.height(20.dp))

                    ReviewField(value = q1, onValueChange = { q1 = it }, placeholder = "What did you enjoy most about your trip?")
                    Spacer(Modifier.height(12.dp))
                    ReviewField(value = q2, onValueChange = { q2 = it }, placeholder = "Does it match your expectation?")
                    Spacer(Modifier.height(12.dp))
                    ReviewField(value = q3, onValueChange = { q3 = it }, placeholder = "Would you go back here?")

                    Spacer(Modifier.height(20.dp))

                    FloatingActionButton(
                        onClick = {},
                        containerColor = FieldBlue,
                        contentColor = FieldTextBlue,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                    Spacer(Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun ReviewField(value: String, onValueChange: (String) -> Unit, placeholder: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(placeholder, fontFamily = Poppins, fontSize = 13.sp, color = FieldTextBlue.copy(alpha = 0.6f))
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp)),
        textStyle = TextStyle(fontFamily = Poppins, fontSize = 14.sp, color = FieldTextBlue),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = FieldBlue,
            unfocusedContainerColor = FieldBlue,
            disabledContainerColor = FieldBlue,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = FieldTextBlue
        )
    )
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
fun TravelReviewScreenPreview() {
    MaterialTheme {
        TravelReviewScreen()
    }
}
