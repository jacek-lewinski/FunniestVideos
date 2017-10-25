package pl.jaceklewinski.FunniestVideos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jaceklewinski.FunniestVideos.repositories.VideoRepository;

@Controller
public class MainController {

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("videos", videoRepository.findAll());
        return "index";
    }
}
