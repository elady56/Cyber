package Cyber_Community.api.controllers;
import Cyber_Community.serviceBean.UserServiceBean;
import Cyber_Community.services.JwtUitls;

import Cyber_Community.entities.User;

import Cyber_Community.response.CodeMsg;
import Cyber_Community.response.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class UserRestController {
    @Autowired
    UserServiceBean userService;

    private JwtUitls jwtUitls=new JwtUitls();

    @PostMapping("/login")
    public Result<String> login(@RequestBody Map<String,String>loginMap){
        System.out.println("\n                        Someone is trying login\n");
        String nickName=loginMap.get("nickName");
        String password=loginMap.get("password");
        User user= userService.getUser(nickName);
        if(user==null||!(user.getPassword().equals(password))){
            return Result.error(CodeMsg.INVALID_CREDENTIAL);
        }else {
            Map<String,Object>dataMap=new HashMap<>();
            dataMap.put("NickName",user.getNickname());
            dataMap.put("UserId",user.getId_User());
            String token= jwtUitls.getToken(user);
            return Result.success(token);
        }
    }


    @RequestMapping(value="/profile",method = RequestMethod.POST)
    public Result<User> profile(HttpServletRequest request) throws Exception{
        String authorization =request.getHeader("Authorization");
        if(authorization==null){
            return Result.error(CodeMsg.TOKEN_INVALID);
        }
        String token=authorization.replace("Bearer","");
        int code=jwtUitls.verify(token);
        if(code==1){
            return Result.error(CodeMsg.TOKEN_EXPIRED);
        }else {
            Claims claims=jwtUitls.parseJwt(token);
            Long userId=Long.parseLong(claims.getId());
            User user=this.userService.getUser(userId);
            if(user!=null){
                return Result.success(user);
            }else {
                return Result.error(CodeMsg.TOKEN_INVALID);
            }
        }
    }

    @GetMapping("/users")
    public Collection<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/logged/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User user = userService.getUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User newUser) {
        User oldUser = userService.getUser(id);
        if (oldUser != null) {
            userService.update(id,newUser);
            return new ResponseEntity<>(oldUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id) {

        User user =userService.getUser(id);
        userService.removeUser(id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



}
