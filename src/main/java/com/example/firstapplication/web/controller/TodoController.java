package com.example.firstapplication.web.controller;

import com.example.firstapplication.web.model.Todo;
import com.example.firstapplication.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {
    @Autowired
    TodoService todoService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        //Date - DD/MM/YYYY
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }

    @RequestMapping(value="/list-todos", method= RequestMethod.GET)
    public String showTodosList(ModelMap model){
        model.put("todos", todoService.retrieveTodos("in28Minutes"));
        return "list-todos";
    }

    @RequestMapping(value="/add-todo", method= RequestMethod.GET)
    public String showAddToDoPage(ModelMap model){
        model.addAttribute("todo", new Todo(0, new String((String) model.get("name")),"Default Desc", new Date(), false));
        return "todo";
    }

    @RequestMapping(value="/update-todo", method=RequestMethod.GET)
    public String showUpdateToDoPage(ModelMap modelMap, @RequestParam int id){
        Todo todo = todoService.retrieveTodo(id);
        //populating current todo
        modelMap.put("todo", todo);
        return "todo";
    }


    @RequestMapping(value="/update-todo", method=RequestMethod.POST)
    public String updateToDo(ModelMap modelMap, @Valid Todo todo, BindingResult result){

        todo.setUser(new String((String)modelMap.get("name")));
        if(result.hasErrors()){
            return "todo";
        }

        todoService.updateToDo(todo);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/delete-todo", method= RequestMethod.GET)
    public String deleteToDo(@RequestParam int id){
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }


    @RequestMapping(value="/add-todo", method= RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        todoService.addTodo((String)model.get("name"), todo.getDesc(), todo.getTargetDate(), false );
        return "redirect:/list-todos";
    }
}
