package com.nj.controller;

import com.nj.common.exception.ApplicationException;
import com.nj.dto.LoginDTO;
import com.nj.entity.User;
import com.nj.service.IUserService;
import com.nj.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class UserController {

    private IUserService userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") User user, Model model) throws ApplicationException {
        userService.signup(user);
        return "redirect:/api/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, @ModelAttribute LoginDTO loginDto,
            RedirectAttributes redirectAttributes) throws ApplicationException {
        System.out.println("in login");
        User user = userService.login(loginDto.getEmail(), loginDto.getPassword());
        if (user != null) {
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("currUser", user);
            newSession.setMaxInactiveInterval(30 * 60);
            return "redirect:/home";
        } else {
            redirectAttributes.addAttribute("error", "Invalid credentials");
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
