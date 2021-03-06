package pl.szymontomalik.PhotoGalleryApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.szymontomalik.PhotoGalleryApp.converters.UserConverter;
import pl.szymontomalik.PhotoGalleryApp.models.RegistrationForm;
import pl.szymontomalik.PhotoGalleryApp.services.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final UserConverter converter;

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("registration", new RegistrationForm());
        return "registration";

    }

    @PostMapping("/registration")
    public String processForm(@Valid @ModelAttribute RegistrationForm registration, BindingResult bindingResult) {
        if (!userService.isEmailUnique(registration.getEmail())) {
            bindingResult.rejectValue("email", "registration.email.invalid", "This Email adress is already exist.");
        }
        if (!userService.isLoginUnique(registration.getLogin())) {
            bindingResult.rejectValue("login", "registration.login.invalid", "This Login is already exist.");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.addUser(converter.convertRegistrationToUser(registration));
        return "login";
    }
}
