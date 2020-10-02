package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping(value = "/")
    public String PageHome() {
        return "login";
    }


}
