package edu.bsu.shuttlelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yliu12 on 2018/2/18.
 */
@Controller
@RequestMapping("/login")

public class LoginController {
    @RequestMapping("/user")
    public String listUser(Model theModel) {
    		System.out.println("/login/user");
        return "listuser";
    }


}
