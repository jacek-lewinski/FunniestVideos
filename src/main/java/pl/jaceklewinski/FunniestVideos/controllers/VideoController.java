package pl.jaceklewinski.FunniestVideos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jaceklewinski.FunniestVideos.models.Video;
import pl.jaceklewinski.FunniestVideos.models.forms.VideoForm;
import pl.jaceklewinski.FunniestVideos.repositories.VideoRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    @GetMapping("/addvideo")
    public String addVideo(Model model) {
        model.addAttribute("isLogged", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        model.addAttribute("videoForm", new VideoForm());
        return "addvideo";
    }

    @PostMapping("/addvideo")
    public String postAddVideo(@ModelAttribute("videoForm") @Valid VideoForm videoForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("info", "Znaleziono błędy w formularzu!");
            return "addvideo";
        }

        Video video = new Video(videoForm);
        videoRepository.save(video);
        model.addAttribute("addInfo", "Dodano video!");

        return "addvideo";
    }
}
