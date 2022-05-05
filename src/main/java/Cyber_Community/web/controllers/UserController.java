package Cyber_Community.web.controllers;

import Cyber_Community.api.exceptions.NotFoundException;
import Cyber_Community.services.JwtUitls;
import Cyber_Community.entities.User;
import Cyber_Community.services.UserService;
import Cyber_Community.response.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        System.out.println("\n                        Someone is trying login\n");


        /*if(user==null||!(user.getPassword().equals())){
            return Result.error(CodeMsg.INVALID_CREDENTIAL);
        }else {
            Map<String,Object>dataMap=new HashMap<>();
            dataMap.put("NickName",user.getNickname());
            dataMap.put("UserId",user.getId_User());
            String token= jwtUitls.getToken(user);
            return Result.success(token);
        }*/
        return Result.success();
    }

    //For login:
    @GetMapping("/check")
    public String enterPage(User user, Model model,RedirectAttributes attributes, HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException, ServletException {
        JwtUitls jwtUitls=new JwtUitls();
        User user1=this.userService.getUser(user.getNickname());
        if (user1!=null) {
            if(user1.getPassword().equals(user.getPassword())){
                Long id=user1.getId_User();
                attributes.addAttribute("id",id);
                String token= jwtUitls.getToken(user);
                model.addAttribute("token",token);
                model.addAttribute("id",id);
                request.login(user.getNickname(),user.getPassword());
                //return "redirect:/logged/{id}";
                return "/Login/prueba";
            }else {
                return "redirect:/login/error";
            }
        } else {
           return "redirect:/login/error";
        }
    }


    //Create new user
    @PostMapping("/new")
    public String createUser(User newUser,Model model,RedirectAttributes attributes) {
            if(this.userService.NickNameExisted(newUser.getNickname())){
                return "redirect:/signup/error";
            }else if(this.userService.EmailExisted(newUser.getEmail())){
                return "redirect:/signup/error";
            }
            this.userService.add(newUser);

            //model.addAttribute("user",newUser);
            attributes.addAttribute("user",newUser);
            return "redirect:/Login";
        //return "redirect:/user/check";

    }

    @GetMapping("/update/{id}") //edit a user-get id
    public String updUser(Model model, User user, @PathVariable long id) {
        this.userService.getUser(id);
        model.addAttribute("U_id", user.getId_User());
        model.addAttribute("notAdmin",true);
        model.addAttribute("admin",false);
        return "User/EditUser_template";
    }

    //Modify an existing user
    @PostMapping("/updated/{id}/user")
    public String updateUser(Model model, User user, @PathVariable long id) {
        this.userService.add(user);
        this.userService.update(id, this.userService.getUser(id));
        model.addAttribute("U_id", user.getId_User());
        model.addAttribute("message", "The user has been edited");
        return "message2";
    }

    //Delete an existing user
    @GetMapping("/delete/{id}")
    public String deleteUser(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getUser(id));
        userService.removeUser(id);
        model.addAttribute("message", "The user has been deleted");
        return "message";
    }

    //Auxiliar functions
    @GetMapping("/search/byName")
    public String searchByName(Model model){
        model.addAttribute("isClub",false);
        model.addAttribute("isUser",true);
        model.addAttribute("users",userService.getUsers());
        return "Index/search";
    }

    @GetMapping("/seeProfile/{id}")
    public String seeProfile(Model model, @PathVariable long id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NotFoundException("User " + id + " not found");
        } else {
            model.addAttribute("user", userService.getUser(id));
            model.addAttribute("id_User", id);
            return "User/User_seeProfile";
        }
    }
}
