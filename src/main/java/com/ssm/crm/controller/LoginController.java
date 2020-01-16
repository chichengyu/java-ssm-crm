package com.ssm.crm.controller;

import com.ssm.crm.constart.UserConstart;
import com.ssm.crm.pojo.User;
import com.ssm.crm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/dologin")
    public String login(String usercode, String password,String code,HttpServletRequest request){
        if (StringUtils.isEmpty(usercode) || StringUtils.isEmpty(password) || StringUtils.isBlank(code)){
            return "login";
        }
        String verifyCode = (String) request.getSession().getAttribute(UserConstart.VERIFY_CODE);
        if (!StringUtils.equals(verifyCode,code)){
            return "login";
        }
        /* 密码加密
            String gensalt = BCrypt.gensalt();
            String hashpw = BCrypt.hashpw("123", gensalt);
        */
        List<User> users = userService.login(usercode);
        if (users.size() == 1 && BCrypt.checkpw(password,users.get(0).getUserPassword())){
            request.getSession(true).setAttribute(UserConstart.USER_KEY,users.get(0));
            return "redirect:/customer/list";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession(true).invalidate();
        return "redirect:dologin";
    }
}
