package Ivan.Vitsin.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import Ivan.Vitsin.springsecurity.model.User;
import Ivan.Vitsin.springsecurity.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAdminPage() {
        return "/admin/adminPage";
    }

    @GetMapping("/main")
    public String getAllUsersPage(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "/admin/allUsersPage";
    }

    @GetMapping(value = "/new")
    public String getNewUserPage(@ModelAttribute("user") User user) {
        return "/admin/createNewUserPage";
    }

    @GetMapping(value = "/{id}/edit")
    public String getEditUserPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admin/editUserPage";
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/admin/createNewUserPage";
        }
        userService.saveUser(user);
        return "redirect:/admin/main";
    }

    @PatchMapping("/updateUser/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/admin/editUserPage";
        }
        userService.updateUser(user);
        return  "redirect:/admin/main";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return  "redirect:/admin/main";
    }
}
