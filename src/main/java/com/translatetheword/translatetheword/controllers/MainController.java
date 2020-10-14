package com.translatetheword.translatetheword.controllers;

import com.translatetheword.translatetheword.models.Dictionary;
import com.translatetheword.translatetheword.models.User;
import com.translatetheword.translatetheword.repo.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "ttw";
    }


    @GetMapping("/dictionary/addword")
    public String addword(@AuthenticationPrincipal User user, Model model) {

        Iterable<Dictionary> dict = dictionaryRepository.findByAuthor(user);
        model.addAttribute("posts", dict);
        return "addword";
    }
}

