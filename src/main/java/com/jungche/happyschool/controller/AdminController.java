package com.jungche.happyschool.controller;

import com.jungche.happyschool.model.HappyClass;
import com.jungche.happyschool.model.Person;
import com.jungche.happyschool.repository.HappyClassRepository;
import com.jungche.happyschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("happyClass") HappyClass happyClass) {
        happyClassRepository.save(happyClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<HappyClass> happyClass = happyClassRepository.findById(id);
        for(Person person : happyClass.get().getPersons()){
            person.setHappyClass(null);
            personRepository.save(person);
        }
        happyClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        Optional<HappyClass> happyClass = happyClassRepository.findById(classId);
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("happyClass",happyClass.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("happyClass",happyClass.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }
    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        HappyClass happyClass = (HappyClass) session.getAttribute("happyClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if(personEntity==null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+happyClass.getClassId()
                    +"&error=true");
            return modelAndView;
        }
        personEntity.setHappyClass(happyClass);
        personRepository.save(personEntity);
        happyClass.getPersons().add(personEntity);
        happyClassRepository.save(happyClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+happyClass.getClassId());
        return modelAndView;
    }
    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        HappyClass happyClass = (HappyClass) session.getAttribute("happyClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setHappyClass(null);
        happyClass.getPersons().remove(person.get());
        HappyClass happyClassSaved = happyClassRepository.save(happyClass);
        session.setAttribute("happyClass",happyClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+happyClass.getClassId());
        return modelAndView;
    }
}
