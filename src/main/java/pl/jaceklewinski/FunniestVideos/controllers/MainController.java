package pl.jaceklewinski.FunniestVideos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jaceklewinski.FunniestVideos.repositories.VideoRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/")
    public String main(Model model, HttpServletRequest request) {
        model.addAttribute("isLogged", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        model.addAttribute("username", request.getRemoteUser());
        model.addAttribute("videos", videoRepository.findAll());
        return "index";
    }
}
