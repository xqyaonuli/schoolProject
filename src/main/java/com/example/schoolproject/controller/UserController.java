package com.example.schoolproject.controller;

import com.example.schoolproject.mapper.UserMapper;
import com.example.schoolproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("User")
@CrossOrigin
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/login")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        // mv会自动寻找templates/page/index.html，并进行渲染
        mv.setViewName("login");
        return mv;
    }

//    @PostMapping("/doLogin")
//    public ModelAndView doLogin(HttpServletRequest request, User loginUser) {
//        ModelAndView mv = new ModelAndView();
//        User user = userMapper.GetUserText(loginUser.getName());
//        if (loginUser.getName() == null) {
//            mv.addObject("errorMessage_user", "用户名不能为空");
//            mv.setViewName("login");
//        } else if (loginUser.getPassword() == null) {
//            mv.addObject("errorMessage_user", "密码不能为空");
//            mv.setViewName("login");
//        } else if (user == null) {
//            mv.addObject("errorMessage_user", "用户不存在");
//            mv.setViewName("login");
//        } else if (user.getName().equals(loginUser.getName()) && user.getPassword().equals(loginUser.getPassword())) {// 重定向至需求页
//            mv.setViewName("redirect:/Course/selectAll");
//        } else {
//            mv.addObject("errorMessage_user", "用户名或者密码错误");
//            mv.setViewName("login");
//        }
//        return mv;
//    }

    @RequestMapping ("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, User loginUser) {
        User user = userMapper.GetUserText(loginUser.getName());
        if (loginUser.getName() == null) {
           return "用户名不能为空";
        } else if (loginUser.getPassword() == null) {
            return "密码不能为空";
        } else if (user == null) {
           return  "用户不存在";
        } else if (user.getName().equals(loginUser.getName()) && user.getPassword().equals(loginUser.getPassword())) {// 重定向至需求页
            return  "success";
        } else {
            return  "用户名或者密码错误";
        }
    }

}
