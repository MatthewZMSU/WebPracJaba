package cmc.msu.webpracjaba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/", "/home"})
public class IndexController {
    @GetMapping
    public String index(Model model) {
        return "home";
    }
}
