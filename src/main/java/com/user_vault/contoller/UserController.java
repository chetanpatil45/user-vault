package com.user_vault.contoller;

import com.user_vault.entity.User;
import com.user_vault.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequestMapping("/uservault")
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

    @GetMapping("/reset-pass")
    public String getResetPage(HttpSession session,
                               RedirectAttributes redirectAttributes){

        Integer userId = (Integer) session.getAttribute("resetUserId");

        User user = (User) session.getAttribute("loggedInUser");

        if (user != null && userId == null){
            redirectAttributes.addFlashAttribute("error", "Session expired. Please try again.");
            return "redirect:/dashboard";
        }
        else if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "Session expired. Please try again.");
            return "redirect:/forgot-password";
        }

        return "reset";
    }

    @PostMapping("/reset-pass")
    public String resetPassword(
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            RedirectAttributes redirectAttributes,
            HttpSession session ){

        Integer userId = (Integer) session.getAttribute("resetUserId");

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute( "error", "Passwords do not match" );
            return "redirect:/reset-pass";
        }

        boolean updated = userService.updatePassword(userId, newPassword);

        if (!updated) {
            redirectAttributes.addFlashAttribute( "error", "Failed to reset password" );
            return "redirect:/reset-pass";
        }

        session.removeAttribute("resetUserId");
        redirectAttributes.addFlashAttribute( "success", "Password reset successfully. Please login.");

        return "redirect:/login";
    }

    @PostMapping("/update-pass")
    public String updatePassword(
            @RequestParam String password,
            RedirectAttributes redirectAttributes,
            HttpSession session){

        User sessionUser = (User) session.getAttribute("loggedInUser");

        if(sessionUser == null){
            return "redirect:/login";
        }

        if(userService.validatePass(sessionUser.getId(), password)){
            session.removeAttribute("resetUserId");
            session.setAttribute("resetUserId", sessionUser.getId());
            return "redirect:/reset-pass";
        }
        else {
            redirectAttributes.addFlashAttribute("error","Password does not matched !");
            return "redirect:/dashboard";
        }
    }
}
