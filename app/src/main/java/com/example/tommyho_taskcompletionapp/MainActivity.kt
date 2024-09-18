package com.example.tommyho_taskcompletionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tommyho_taskcompletionapp.ui.theme.TommyHoTaskCompletionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TommyHoTaskCompletionAppTheme {
                taskMaker()
            }
        }
    }
}

@Composable
fun taskMaker(){
    var currentText by remember{ mutableStateOf("") }
    val tasks = remember { mutableStateListOf<String>() }
    val taskChecked = remember{mutableStateListOf<Boolean>()}
    Column() {
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = currentText,
            onValueChange = { currentText = it },
            label = { Text("Input text here") })
        Button(onClick = { tasks.add(currentText)
            currentText = ""
            taskChecked.add(false)}){
            Text("Add Task")
        }
        Button(onClick = {
            var i = 0
            while(i < tasks.size){
                if(taskChecked[i]){
                    tasks.removeAt(i)
                    taskChecked.removeAt(i)
                }
                else{
                    i++
                }
            }
        }){
            Text("Clear Completed")
        }
        LazyColumn() {
            items(tasks.size) { index ->
                Row(verticalAlignment = Alignment.CenterVertically,) {
                    Checkbox(checked = taskChecked[index], onCheckedChange = {taskChecked[index] = it})
                    if(taskChecked[index]){
                        Text(text = tasks[index], style = TextStyle(textDecoration = TextDecoration.LineThrough))
                    }
                    else{
                        Text(text = tasks[index])
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TommyHoTaskCompletionAppTheme {
    }
}