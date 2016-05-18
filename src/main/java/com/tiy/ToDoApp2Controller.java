package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kat on 5/17/2016.
 */
@Controller
public class ToDoApp2Controller {

    @Autowired
    ToDoAppRepository items;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        Iterable<ToDoItemList> allItems = items.findAll();
        List<ToDoItemList> itemsList = new ArrayList<ToDoItemList>();
        for (ToDoItemList toDoItemList : allItems) {
            itemsList.add(toDoItemList);
        }

        model.addAttribute("items", itemsList);
        return "home";
    }

    @RequestMapping(path = "/add-item", method = RequestMethod.POST)
    public String addList(String todoText, boolean itemIsDone) {
    ToDoItemList toDoItemList = new ToDoItemList(todoText,itemIsDone);
    items.save(toDoItemList);
    return "redirect:/";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteList(Model model, Integer itemID) {
        if (itemID != null) {
            items.delete(itemID);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/modify", method = RequestMethod.GET)
    public String modify(Model model, Integer itemID) {
        if (itemID != null) {
            ToDoItemList toDoItemList = items.findOne(itemID);
            //toDoItemList.isDone = " ** " + game.name;
            toDoItemList.isDone = !toDoItemList.isDone;
            items.save(toDoItemList);

        }

        return "redirect:/";
    }

}
