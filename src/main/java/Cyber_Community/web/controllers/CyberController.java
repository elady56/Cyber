package Cyber_Community.web.controllers;

/*
 *  Handle the main page
 */


import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Controller
@Transactional
public class CyberController {

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("notlogged",true);
        model.addAttribute("logged",false);
        model.addAttribute("admin",false);
        return "Index/index";
    }

    @GetMapping("/lmau")
    public String lmau(){return "Logged/Lmau_template";}


    @GetMapping("/Login")
    public String login() {
        return "Login/Login";
    }

    @GetMapping("/login/error")
    public String login(Model model){
        model.addAttribute("loginError", "Incorrect username or password");
        return "Login/Login";
    }
    @GetMapping("/signup")
    public String signUp() {
        return "Login/signUp";
    }

    @GetMapping("/signup/error")
    public String signUp(Model model) {
        model.addAttribute("loginError", "This nickname or email is already in use, try another");
        return "Login/signUp";
    }


    @GetMapping("/upUser")
    public String upUser(){return "User/EditUser_template";}

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }

}
