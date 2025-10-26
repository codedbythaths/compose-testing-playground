package com.example.composetestingplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetestingplayground.ui.theme.ComposeTestingPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTestingPlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Greeting(
                            name = "Android", modifier = Modifier.padding(innerPadding)
                        )

                        CounterApp()

                        UserForm()
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Composable
fun CounterApp() {
    var count by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Counter: $count",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.testTag("counter_text")
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = { count-- }, modifier = Modifier.testTag("decrease_button")) {
                    Text("Decrease")
                }

                Button(onClick = { count++ }, modifier = Modifier.testTag("increase_button")) {
                    Text("Increase")
                }

                Button(onClick = { count = 0 }, modifier = Modifier.testTag("reset_button")) {
                    Text("Reset")
                }
            }
        }
    }
}


@Composable
fun UserForm() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "User Form",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.testTag("form_title")
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("name_field")
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("email_field")
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { isSubmitted = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("submit_button")
            ) {
                Text("Submit")
            }

            if (isSubmitted) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Submitted: $name ($email)",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.testTag("submission_result")
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTestingPlaygroundTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    ComposeTestingPlaygroundTheme {
        CounterApp()
    }
}


@Preview(showBackground = true)
@Composable
fun UserFormPreview() {
    ComposeTestingPlaygroundTheme {
        UserForm()
    }
}