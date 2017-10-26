package pl.jaceklewinski.FunniestVideos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jaceklewinski.FunniestVideos.models.User;
import pl.jaceklewinski.FunniestVideos.models.forms.UserForm;
import pl.jaceklewinski.FunniestVideos.repositories.UserRepository;

import javax.validation.Valid;
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

    @GetMapping("/registration")
    public String registration(Model model) {
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
}
