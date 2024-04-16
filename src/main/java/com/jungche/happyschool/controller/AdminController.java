package com.jungche.happyschool.controller;

import com.jungche.happyschool.model.HappyClass;
import com.jungche.happyschool.repository.HappyClassRepository;
import com.jungche.happyschool.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    HappyClassRepository happyClassRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<HappyClass> happyClasses = happyClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("happyClasses",happyClasses);
        modelAndView.addObject("happyClass", new HappyClass());
        return modelAndView;
    }
}
