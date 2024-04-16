package com.jungche.happyschool.controller;

import com.jungche.happyschool.model.Person;
import com.jungche.happyschool.model.Profile;
import com.jungche.happyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(HttpSession session) {
        Person person = (Person)session.getAttribute("loggedInPerson");
        Profile profile = new Profile();
        profile.setName(person.getName());
        profile.setEmail(person.getEmail());
        profile.setMobileNumber(person.getMobileNumber());
        if (null != person.getAddress() && person.getAddress().getAddressId()>0) {
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile",profile);
        return modelAndView;

    }
}
