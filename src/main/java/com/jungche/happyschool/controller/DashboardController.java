package com.jungche.happyschool.controller;

import com.jungche.happyschool.model.Person;
import com.jungche.happyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboardPage(Model model, Authentication authentication, HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities());
        session.setAttribute("loggedInPerson", person);
        return "dashboard.html";
    }
}
