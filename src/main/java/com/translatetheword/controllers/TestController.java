package com.translatetheword.translatetheword.controllers;

import com.translatetheword.translatetheword.models.Dictionary;
import com.translatetheword.translatetheword.models.Test;
import com.translatetheword.translatetheword.models.User;
import com.translatetheword.translatetheword.repo.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class TestController {
    private ArrayList<Dictionary> arrayList = new ArrayList<>();
    private boolean param;
    @Autowired
    DictionaryRepository dictionaryRepository;

    @GetMapping("/test")
    public String test(Model model) {
        return "test";
    }

    @PostMapping("/test")
    public String testword(@AuthenticationPrincipal User user, @RequestParam Integer number,
                           @RequestParam Boolean radio, Model model) {
        param = radio;
        ArrayList<Dictionary> test = dictionaryRepository.findByAuthor(user);
        if (number > test.size()) {
            Boolean message = true;
            model.addAttribute("message", message);
            return "test";
        }

            Random rnd = new Random();
            int n = test.size();
            for (int i = 0; i < test.size(); i++) {
                int index = i + rnd.nextInt(n - i);
                Dictionary a = test.get(i);
                test.set(i, test.get(index));
                test.set(index, a);
            }
            for (int i = n - 1; i >= number; i--) {
                test.remove(i);
            }
        arrayList = test;
        model.addAttribute("radio", radio);
        model.addAttribute("posts", test);
        return "testword";
    }

    @PostMapping("/testword")
    public String testcontrol(@AuthenticationPrincipal User user,
                              @RequestParam ArrayList<String> translation,
                              Model model) {
        ArrayList<Test> testresult = new ArrayList<>();
        if (param) {
            for (int i = 0; i < translation.size(); i++) {
                Test test = new Test(arrayList.get(i).getEngword(), arrayList.get(i).getRusword(), translation.get(i));
                if (translation.get(i).equals(arrayList.get(i).getRusword())) {
                    test.setResult(true);
                    testresult.add(i, test);
                } else {
                    test.setResult(false);
                    testresult.add(i, test);
                }
            }
        }else{
            for (int i = 0; i < translation.size(); i++) {
                Test test = new Test(arrayList.get(i).getEngword(), arrayList.get(i).getRusword(), translation.get(i));
                if (translation.get(i).equals(arrayList.get(i).getEngword())) {
                    test.setResult(true);
                    testresult.add(i, test);
                } else {
                    test.setResult(false);
                    testresult.add(i, test);
                }
            }
        }
        model.addAttribute("radio", param);

        model.addAttribute("testresult", testresult);
        System.out.println(param);

        return "testresult";

    }
}
