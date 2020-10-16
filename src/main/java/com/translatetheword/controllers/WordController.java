package com.translatetheword.controllers;

import com.translatetheword.models.Dictionary;
import com.translatetheword.repo.DictionaryRepository;
import com.translatetheword.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import java.util.Optional;

@Controller
public class WordController {
    @Autowired
    private DictionaryRepository dictionaryRepository;

    @GetMapping("/dictionary/addword")
    public String addword(@AuthenticationPrincipal User user, Model model) {

        Iterable<Dictionary> dict = dictionaryRepository.findByAuthor(user);
        model.addAttribute("posts", dict);
        return "addword";
    }

    @PostMapping("/dictionary/addword")
    public String blogPostAdd(@AuthenticationPrincipal User user, @RequestParam String engword, @RequestParam String rusword, Model model){
        Dictionary dictionary  = new Dictionary(engword, rusword, user);
        dictionaryRepository.save(dictionary);
        return "redirect:/dictionary";
    }

    @GetMapping("/dictionary")
    public String dictionary(@AuthenticationPrincipal User user, Model model) {
        Iterable<Dictionary> dictionary = dictionaryRepository.findByAuthor(user);
        model.addAttribute("posts", dictionary);
        return "dictionary";
    }

    @PostMapping("/dictionary/filter")
    public String blogPostAdd(@AuthenticationPrincipal User user,@RequestParam String text,  Model model){
        Iterable<Dictionary> dictionary;
        if (text != null && !text.isEmpty()) {
            dictionary  = dictionaryRepository.findByEngwordOrRusword(text, text);
        }else{
            dictionary = dictionaryRepository.findByAuthor(user);
        }
        model.addAttribute("posts", dictionary);
        return "dictionary";
    }

    @GetMapping("dictionary/{id}/edit")
    public String dictionaryEdit(@AuthenticationPrincipal User user, @PathVariable(value = "id") Long id, Model model){
        if(!dictionaryRepository.existsById(id)){
            return "redirect:/dictionary";
        }

        Optional<Dictionary> dictionary = dictionaryRepository.findById(id);
        ArrayList<Dictionary> res = new ArrayList<>();
        dictionary.ifPresent(res::add);

        Iterable<Dictionary> res2 = dictionaryRepository.findByAuthor(user);
//        res2 = dictionaryRepository.findByAuthor(user);
        for(Dictionary dictionary5 : res2){
            System.out.println(dictionary5.getEngword() + " " + dictionary5.getRusword() );
        }
//        res.contains()
        model.addAttribute("post", res);

        Iterable<Dictionary> dict = dictionaryRepository.findByAuthor(user);
        model.addAttribute("posts", dict);

        return "dictionary-edit";
    }


    @GetMapping("dictionary/{id}/remove")
    public String dictionaryRemove(@PathVariable(value = "id") Long id, Model model){
        Dictionary dictionary = dictionaryRepository.findById(id).orElseThrow();
        dictionaryRepository.delete(dictionary);
        return "redirect:/dictionary";
    }

    @PostMapping("dictionary/{id}/edit")
    public String dictionaryEditSave(@PathVariable(value = "id") Long id, @RequestParam String engword, @RequestParam String rusword, Model model){
        Dictionary dictionary = dictionaryRepository.findById(id).orElseThrow();
        dictionary.setEngword(engword);
        dictionary.setRusword(rusword);
        dictionaryRepository.save(dictionary);

        return "redirect:/dictionary";
    }

}
