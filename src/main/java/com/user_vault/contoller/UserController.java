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
    public String editData(@ModelAttribute User user,
                           HttpSession session, Model model,
                           RedirectAttributes redirectAttributes){

        User sessionUser = (User) session.getAttribute("loggedInUser");

        if (sessionUser == null) {
            return "redirect:/login";
        }

        // 🔐 Force ID from session (prevent tampering)
        user.setId(sessionUser.getId());

        boolean updated = userService.updateUser(user);

        if (!updated) {
            redirectAttributes.addFlashAttribute("error", "Failed to update profile");
            return "redirect:/edit";
        }

        // ✅ Fetch fresh user from DB
        User updatedUser = userService.getUserById(sessionUser.getId());

        if (updatedUser == null) {
            session.invalidate();
            return "redirect:/login";
        }


        // ✅ Update session with complete object
        session.setAttribute("loggedInUser", updatedUser);

        redirectAttributes.addFlashAttribute("success", "Profile updated successfully");
        System.out.println(session.getAttribute("loggedInUser"));
        return "redirect:/home";

        return "redirect:/dashboard";
    }


}
