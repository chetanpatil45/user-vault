package com.user_vault.contoller;

import com.user_vault.entity.User;
import com.user_vault.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

//        User user = userService.authenticate(email, password);

//        if (user == null) {
//            return "redirect:/login?error";
//        }

        User user = new User();
        user.setEmail(email);
        user.setPass(password);
        user.setName("Chetan");
        user.setGender("Male");
        user.setBio("Passionate Student");
        user.setDesignation("Student");
        user.setPhone("9284691048");
//        user.set

        session.setAttribute("loggedInUser", user);
        return "redirect:/dashboard";
    }


    @PostMapping("/signup")
    public String signup(){
        return "";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignup(){
        return "signup";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
