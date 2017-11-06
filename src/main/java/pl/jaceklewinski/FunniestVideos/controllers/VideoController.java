package pl.jaceklewinski.FunniestVideos.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoController {

    @GetMapping("/addvideo")
    public String addVideo(Model model) {
        model.addAttribute("isLogged", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        return "addvideo";
    }
}
