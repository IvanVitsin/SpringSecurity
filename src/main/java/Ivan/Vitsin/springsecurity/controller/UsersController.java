package Ivan.Vitsin.springsecurity.controller;

import Ivan.Vitsin.springsecurity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import Ivan.Vitsin.springsecurity.service.UserService;

@Controller
@RequestMapping("/")
public class UsersController {

    private final UserService userService;

    public UsersController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getMainPage() {
        return "/user/index";
    }

    @GetMapping("/user")
    public String getCurrentUserInfo(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "/user/user";
    }

    @GetMapping("/user/{userName}")
    public String getUserPage(Model model, @PathVariable("userName") String userName) {
        model.addAttribute("user", userService.getUserByName(userName));
        return "/user/user";
    }
}
