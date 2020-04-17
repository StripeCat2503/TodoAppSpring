package com.duynk.todo.controllers;

import com.duynk.todo.models.Todo;
import com.duynk.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/todoList")
    public String todoListPage(Model model){
        List<Todo> todoList = todoService.getTodoList();
        model.addAttribute("todoList", todoList);

        return "todoList";
    }

    @GetMapping("/addTodo")
    public String todoPage(Model model){
        model.addAttribute("todo", new Todo());
        return "addTodo";
    }

    @PostMapping("/addTodo")
    public String addTodo(@ModelAttribute Todo todo){

        Todo addedTodo = todoService.addTodo(todo);
        String url = "success";

        if(addedTodo == null){
            url = "fail";
        }

        return url;
    }

    @GetMapping("/deleteTodo")
    public String deleteTodo(Model model, @RequestParam(value = "id", required = false) int id){

        Todo deletedTodo = todoService.deleteTodo(id);

        String url = "todoList";
        if(deletedTodo == null){
            url = "fail";
        }
        else{
            List<Todo> todoList = todoService.getTodoList();
            model.addAttribute("todoList", todoList);
        }

        return url;
    }
}
