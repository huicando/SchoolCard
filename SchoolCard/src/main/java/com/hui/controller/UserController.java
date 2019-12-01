package com.hui.controller;

import com.hui.pojo.User;
import com.hui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findByOpenid")
    @ResponseBody
    public User findByOpenid(@RequestParam("openid") String openid){
        return userService.findByOpenid(openid);
    }
    @RequestMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody User user){
        userService.addUser(user);
        return user;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public User UpdateUser(@RequestBody User user){
        userService.updateUser(user);
        return user;
    }

    @RequestMapping("/updateCardId")
    @ResponseBody
    public void updateCardId(@RequestBody User user){
        userService.updateCardId(user);
    }

}
