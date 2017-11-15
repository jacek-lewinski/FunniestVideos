package pl.jaceklewinski.FunniestVideos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jaceklewinski.FunniestVideos.models.User;
import pl.jaceklewinski.FunniestVideos.models.Video;
import pl.jaceklewinski.FunniestVideos.models.forms.VideoForm;
import pl.jaceklewinski.FunniestVideos.repositories.UserRepository;
import pl.jaceklewinski.FunniestVideos.repositories.VideoRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/addvideo")
    public String addVideo(Model model) {
        model.addAttribute("isLogged", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));
        model.addAttribute("videoForm", new VideoForm());
        return "addvideo";
    }

    @PostMapping("/addvideo")
    public String postAddVideo(@ModelAttribute("videoForm") @Valid VideoForm videoForm, BindingResult result, Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        Optional<User> user = userRepository.findByUsername(username);
        int userId = user.get().getId();

        model.addAttribute("isLogged", !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken));

        if (result.hasErrors()) {
            model.addAttribute("info", "Znaleziono błędy w formularzu!");
            return "addvideo";
        }

        Video video = new Video(videoForm, userId);
        videoRepository.save(video);
        model.addAttribute("addInfo", "Dodano video!");

        return "redirect:/video/addvideo";
    }

    @GetMapping("/deleteVideo")
    public String deleteVideo (@RequestParam("videoId") int videoId) {
        videoRepository.delete(videoId);
        return "redirect:/userpanel";
    }
}
