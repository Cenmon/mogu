package com.cenmo.mogu.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Cenmo
 * @Date 2020-10-31 2020/10/31
 */
@Controller
public class PageController {

    @GetMapping({"/index","/"})
    public ModelAndView showIndex(){
        ModelAndView view = new ModelAndView("main");
        return view;
    }

//    @GetMapping("/{page}")
    public ModelAndView showPage(@PathVariable String page){
        return new ModelAndView(page);
    }

    /**
     * 展示登录页面
     * @return
     */
    @GetMapping("/page/showLogin")
    public ModelAndView showLogin(){
        return new ModelAndView("login");
    }

//    @GetMapping("/test")
//    public ModelAndView test(){
//        ModelAndView view = new ModelAndView("index");
//        return view;
//    }
//
//    @GetMapping("/test2")
//    public ModelAndView test2(){
//        ModelAndView view = new ModelAndView("test");
//        return view;
//    }
//
//    @GetMapping("/test3")
//    public ModelAndView test3(){
//        ModelAndView view = new ModelAndView("main.html");
//        return view;
//    }
}
