package pl.szymontomalik.PhotoGalleryApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.szymontomalik.PhotoGalleryApp.services.UserService;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;


    @GetMapping("/login")
    public String login() {
        if (userService.isLogged()) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
