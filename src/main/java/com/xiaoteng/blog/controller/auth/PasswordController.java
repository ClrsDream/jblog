package com.xiaoteng.blog.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PasswordController {

    @GetMapping("/password/find")
    public String showPasswordFindPage() {
        return "auth/password/find";
    }

//    public RedirectView handlePasswordFind() {
//
//    }

}
