package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;

@Controller
@RequestMapping("users")
public class UsersController {
    private final UserDao userDaoImp;

    public UsersController(UserDao userDaoImp) {
        this.userDaoImp = userDaoImp;
    }

    @GetMapping()
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("message", userDaoImp.getAllUsers());
        return "users/index";
    }
    @GetMapping("/serves")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/serves";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDaoImp.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/new/{id}")
    public String serves(@PathVariable("id") long id, ModelMap modelMap) {
        modelMap.addAttribute("message", userDaoImp.findUserId(id));
        return "users/new";
    }
    @PostMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userDaoImp.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userDaoImp.findUserId(id));
        return "users/edit";
    }
    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userDaoImp.updateUser(id, user);
        return "redirect:/users";
    }
}
