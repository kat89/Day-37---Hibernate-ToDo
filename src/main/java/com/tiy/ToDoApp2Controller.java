package com.tiy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kat on 5/17/2016.
 */
@Controller
public class ToDoApp2Controller {

    @Autowired
    ToDoAppRepository items;

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, password);
            users.save(user);
        } else if (!password.equals(user.password)) {
            throw new Exception("Incorrect password");
        }
        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session, String todoText, Integer itemID) {
        User savedUser = (User) session.getAttribute("user");
        List<ToDoItemList> itemsList = new ArrayList<ToDoItemList>();

        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
            itemsList = items.findByUser(savedUser);
        } else {

            Iterable<ToDoItemList> allItems = items.findAll();

            for (ToDoItemList toDoItemList : allItems) {
                itemsList.add(toDoItemList);
            }

        }
        model.addAttribute("items", itemsList);
        return "home";
    }


    @RequestMapping(path = "/add-item", method = RequestMethod.POST)
    public String addList(HttpSession session, String todoText, boolean itemIsDone) {
        User user = (User) session.getAttribute("user");
        ToDoItemList toDoItemList = new ToDoItemList(todoText,itemIsDone, user);
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
