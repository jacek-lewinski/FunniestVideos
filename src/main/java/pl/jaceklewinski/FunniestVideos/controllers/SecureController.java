package pl.jaceklewinski.FunniestVideos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jaceklewinski.FunniestVideos.models.User;
import pl.jaceklewinski.FunniestVideos.models.forms.UserForm;
import pl.jaceklewinski.FunniestVideos.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class SecureController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        model.addAttribute("isLogged", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        model.addAttribute("username", request.getRemoteUser());
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model, HttpServletRequest request) {
        model.addAttribute("isLogged", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        model.addAttribute("username", request.getRemoteUser());
        model.addAttribute("userForm", new UserForm());
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("userForm") @Valid UserForm userForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("isLogged", false);
            model.addAttribute("registeredInfo", "Znaleziono błędy w formularzu!");
            return "registration";
        }

        if (userForm.getPassword().equals(userForm.getRepassword())) {
            if (userForm.getEmail().equals(userForm.getReemail())) {

                User user = new User(userForm);
                userRepository.save(user);
                model.addAttribute("isLogged", true);
                model.addAttribute("registeredInfo", "Zostałeś zarejestrowany");

                return "registration";
            }
            model.addAttribute("isLogged", false);
            model.addAttribute("registeredInfo", "Podane adresy email nie są jednakowe!");
            return "registration";
        }
        model.addAttribute("isLogged", false);
        model.addAttribute("registeredInfo", "Podane hasła nie są jednakowe!");
        return "registration";
    }

    @GetMapping("/userpanel")
    public String userpanel(Model model, HttpServletRequest request) {
        model.addAttribute("isLogged", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        model.addAttribute("username", request.getRemoteUser());
        return "userpanel";
    }

    @PostMapping("/userpanel")
    public String postUserpanel() {

        return "userpanel";
    }
}
