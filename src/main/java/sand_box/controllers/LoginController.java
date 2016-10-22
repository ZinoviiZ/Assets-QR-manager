package sand_box.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sand_box.SHA1;
import sand_box.services.interfaces.UserService;
import sand_box.tables.login.User;
import sand_box.tables.login.User_Role;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by Zinoviy on 9/5/16.
 */
// Controllers for login
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/registration")
    public String regPage() {
        return "registration";
    }

    @RequestMapping("/save")
    public String registration(@RequestParam String login,
                               @RequestParam String password,
                               @RequestParam String email,
                               Model model ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User(login, SHA1.SHA1(password), email, User_Role.EDITOR, new Date(), new Date());
        userService.addUser(user);
        return "login";
    }



}
