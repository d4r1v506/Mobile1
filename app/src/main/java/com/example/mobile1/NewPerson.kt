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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import org.w3c.dom.NameList

@Composable
fun NewPerson() {

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
                })
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