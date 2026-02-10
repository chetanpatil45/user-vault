package com.user_vault.contoller;

import com.user_vault.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/uservault")
public class HomeController {
    @RequestMapping({"","/"})
    public String loadHomePage(){
        return "index";
    }

    @PostConstruct()
    public void init() {
        System.out.println("HOME CONTROLLER LOADED");
    }

    @GetMapping("/edit")
    public String loadEdit(Model model, HttpSession session){
        User user = (User) session.getAttribute("loggedInUser");

        model.addAttribute("user",user);
        return "edit";
    }
}
