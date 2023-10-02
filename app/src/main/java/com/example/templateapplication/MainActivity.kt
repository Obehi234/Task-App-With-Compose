package com.example.templateapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.templateapplication.model.Todo
import com.example.templateapplication.ui.theme.TemplateApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = viewModel.state.collectAsState()
            TemplateApplicationTheme {
                // A surface container using the 'background' color from the theme
                Box(modifier = Modifier.fillMaxSize()) {
                    Column {
                        for (i in state.value.list) {
                            Todo(i) {
                               viewModel.toggleTodo(it)
                            }
                        }
                    }
                        TodoForm(modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(color = Color.LightGray)
                        ) { name, description ->
                            viewModel.addTask(name, description)

                        }

                }


                }
            }
        }
    }
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoForm(modifier: Modifier = Modifier, onSaveClicked: (String, String) -> Unit) : Unit {
    var taskName: String by remember {
        mutableStateOf("")
    }
    var taskDescription: String by remember {
        mutableStateOf("")
    }
    Box(modifier = modifier.padding(10.dp)) {
        Column {
            TextField(modifier = Modifier.fillMaxWidth(), value = taskName, onValueChange = {
                                                                                            taskName = it
            }, label = {
                Text(text = "Task Name")
            })

            TextField(modifier = Modifier.fillMaxWidth(),value = taskDescription, onValueChange = {
                                                                                                  taskDescription = it
            }, label = {
                Text(text = "Task Description")
            })

            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
                onClick = { onSaveClicked(taskName, taskDescription) }) {
                Text(text = "Save")
            }
        }


    }
}



@Composable
fun Todo(i: Todo, onTodoToggled: (Todo) -> Unit) {
    Row {
        Checkbox(checked = i.status , onCheckedChange ={
            onTodoToggled(i)
        } )
        Column{
            Text(text = i.todoName)
            Spacer(modifier = Modifier.padding(all = 2.dp))
            Text(text = i.todoDescription)
        }
        Spacer(modifier = Modifier.padding(all = 5.dp))
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TemplateApplicationTheme {
        Greeting("Android")
    }
}