package sand_box.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sand_box.SHA1;
import sand_box.services.interfaces.UserService;
import sand_box.tables.login.User;
import sand_box.tables.login.User_Role;

import java.util.Date;

/**
 * Created by Zinoviy on 9/9/16.
 */
// Controller for interaction with user data
@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users")
    public String getUserPage(Model model) {
        model.addAttribute("users",userService.getUsers());
        return "users_page";
    }

    @RequestMapping(value = "/add_user_page")
    public String getAddUserPage(Model model) {
        User_Role[] listroles  = User_Role.values();
        model.addAttribute("roles", listroles);
        return "user_add_page";
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "toDelete[]", required = false) long[] id, Model model) {
        userService.deleteUser(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@RequestParam(name = "login") String login,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "email") String email,
                          @RequestParam(name = "user_role") User_Role role) {
        try {
            userService.addUser(new User(login, SHA1.SHA1(password), email, role, new Date(), new Date()));
        }
        catch (Exception e) {
            System.out.println("Can`t add new user");
        }
        return "redirect:/admin/users";
    }

}
