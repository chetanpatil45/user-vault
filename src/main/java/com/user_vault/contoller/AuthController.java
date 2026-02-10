package com.user_vault.contoller;

import com.user_vault.entity.User;
import com.user_vault.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {

        User user = userService.authenticate(email, password);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error","User not found");
            return "redirect:/login";
        }

        session.setAttribute("loggedInUser", user);
        return "redirect:/dashboard";
    }


    @PostMapping("/signup")
    public String editProfile(@ModelAttribute User user,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        if (userService.register(user)) {
            return "redirect:/login?success=1";
        } else {
            redirectAttributes.addAttribute("error", "Failed to create account");
            return "signup";
        }
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
