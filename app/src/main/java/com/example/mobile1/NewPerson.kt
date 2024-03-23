package com.example.mobile1

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.w3c.dom.NameList

@Composable
fun FormTimer(
    duration: Int,
    timeLeft: Int,
    onPause:() -> Unit = {},
    onReset: () -> Unit = {},
    onComplete: () -> Unit = {}
){
    var timeLeft by remember {
        mutableIntStateOf(duration)
    }

    var isPaused by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0 && !isPaused){
            delay(1000L)
            timeLeft --
        }
        onComplete()
    }

    Row(){
        Text(
            text = "Time left: ${timeLeft.toString()}",
            modifier = Modifier
                .padding(16.dp)
            ,fontSize = 20.sp
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                isPaused = true
                onPause()
            }) {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                imageVector = Icons.Default.Warning,
                contentDescription = null
            )
        }
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
               isPaused = false
                timeLeft = duration
                onReset()
            }) {
            Icon(
                modifier = Modifier
                    .size(20.dp),
                imageVector = Icons.Default.Refresh,
                contentDescription = null
            )
        }
    }
}

@Composable
fun NewPerson() {

    var isFormEnabled by remember {
        mutableStateOf(true)
    }


    var name by remember {
        mutableStateOf("")
    }

    var age by remember {
        mutableStateOf("")
    }

    var persons by remember {
        mutableStateOf(listOf<Person>())
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

       /* FormTimer(
            duration = 20,
            onReset = {
               isFormEnabled = true
            },
            onComplete = {
                isFormEnabled = false
            }
        )*/

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "ADD PERSON",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            TextField(
                value = name,
                placeholder = { Text("Enter name") },
                onValueChange = { text ->
                    name = text
                })

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = age,
                placeholder = { Text("Enter age") },
                onValueChange = { text ->
                    age = text
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
            ),
            onClick = {
                val ageInt = age.toIntOrNull()
                if (name.isNotBlank() && ageInt !=null) {
                    persons = persons + Person(name = name, age = ageInt)
                    name = ""
                    age = ""
                } else {
                    Toast.makeText(
                        context, "Enter a valid name and age", Toast.LENGTH_SHORT
                    ).show()
                }
                name = ""
                age = ""
            })
        {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "icono de añadir"
            )
            Spacer(
                modifier = Modifier.size(16.dp)
            )
            Text(text = "Añadir")

        }
        }
        Divider()
        /*  Column {
        for(name in names){
            currentName : String ->
                Text(text = currentName)
        }
    }*/

        LazyColumn {
            items(persons) { person ->
                Text(
                    text = "Name: ${person.name}    Age: ${person.age}",
                    modifier = Modifier.padding(16.dp)
                )
                Divider()
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPersonPreview(){
    NewPerson()
}