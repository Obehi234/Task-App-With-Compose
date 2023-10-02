package com.example.templateapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.templateapplication.model.Todo
import com.example.templateapplication.model.TodoList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {


    private val _state: MutableStateFlow<TodoList> = MutableStateFlow(TodoList())
    val state : StateFlow<TodoList> = _state

    init {
        addTask("Gallery", "Check out retro gallery in Osu on Friday")
    }

    fun addTask(taskName: String, taskDescription: String) {

        val taskSize = _state.value.list.size
        val todo =
            Todo(taskSize + 1, false, taskName, taskDescription)

        val list = _state.value.list.toMutableList()
        list.add(todo)
        val todos = _state.value.copy(list)
        _state.update {
            todos
        }


    }

    fun toggleTodo(todo: Todo) {
       val list = _state.value.list.toMutableList()
        val id = list.indexOfFirst {
            it.id == todo.id
        }
        list[id] = list[id].copy(status = !todo.status)
        _state.update{
            TodoList(list)
        }

    }

}