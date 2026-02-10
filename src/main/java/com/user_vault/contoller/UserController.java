package com.user_vault.contoller;

import com.user_vault.entity.User;
import com.user_vault.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user",user);
        return "home";
    }

    @PostMapping("/edit")
    public String editProfile(@ModelAttribute User user,
                           HttpSession session,
                           RedirectAttributes redirectAttributes){

        User sessionUser = (User) session.getAttribute("loggedInUser");
        if (sessionUser == null) {
            return "redirect:/login";
        }

        user.setId(sessionUser.getId());
        boolean updated = userService.updateUser(user);

        if (!updated) {
            redirectAttributes.addFlashAttribute("error", "Failed to update profile");
            return "redirect:/edit";
        }

        User updatedUser = userService.getUserById(sessionUser.getId());

        System.out.println("ID"+sessionUser.getId());
        System.out.println(updatedUser);

        if (updatedUser == null) {
            session.invalidate();
            return "redirect:/login";
        }

        session.setAttribute("loggedInUser", updatedUser);
        redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        System.out.println(session.getAttribute("loggedInUser"));

        return "redirect:/dashboard";
    }

    @PostMapping("/deleteProfile")
    public String deleteProfile(@RequestParam String email,
                                HttpSession session,
                                RedirectAttributes redirectAttributes){
        User sessionUser = (User) session.getAttribute("loggedInUser");

        if(sessionUser == null){
            return "redirect:/login";
        }

        if(email.equals(sessionUser.getEmail())){
            if (userService.deleteUser(sessionUser)){
                session.invalidate();
                redirectAttributes.addFlashAttribute("success","Profile deleted");
                return "redirect:/";
            }else {
                redirectAttributes.addFlashAttribute("error", "Delete operation Failed");
                return "redirect:/dashboard";
            }
        }
        else {
            redirectAttributes.addFlashAttribute("error","Email does not matched !");
            return "redirect:/dashboard";
        }
    }
}
