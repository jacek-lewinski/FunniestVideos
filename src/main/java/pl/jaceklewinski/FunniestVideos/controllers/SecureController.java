package pl.jaceklewinski.FunniestVideos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jaceklewinski.FunniestVideos.models.User;
import pl.jaceklewinski.FunniestVideos.repositories.UserRepository;

import java.util.Optional;

@Controller
public class SecureController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            if (password.equals(user.get().getPassword())) {
                model.addAttribute("isLogged", true);
                model.addAttribute("loggedInfo", "Zalogowano poprawnie.");
                return "login";
            }
            model.addAttribute("isLogged", false);
            model.addAttribute("loggedInfo", "Błędne hasło!");
            return "login";
        }
        model.addAttribute("isLogged", false);
        model.addAttribute("loggedInfo", "Użytkownik o takiej nazwie nie istnieje!");
        return "login";
    }
}
