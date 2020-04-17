package com.duynk.todo.services;

import com.duynk.todo.models.Todo;
import com.duynk.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo addTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public List<Todo> getTodoList(){
        return todoRepository.findAll();
    }

    public Todo deleteTodo(Integer id){
        Todo todo = todoRepository.findById(id).get();
        if(todo != null){
            todoRepository.delete(todo);
            return todo;
        }
        else{
            return null;
        }
    }
}
