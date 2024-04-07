package com.jungche.happyschool.controller;

import com.jungche.happyschool.model.Holiday;
import com.jungche.happyschool.repository.HolidaysRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class HolidaysController {

    @Autowired
    private HolidaysRepository holidaysRepository;
    @GetMapping("/holidays/{display}")
    public String displayHolidays(Model model, @PathVariable String display) {
        model.addAttribute("display", display);
        if (display != null && "all".equals(display)) {
            model.addAttribute("festival", true);
            model.addAttribute("federal", true);
        }else if (display != null && "federal".equals(display)) {
            model.addAttribute("federal", true);
        }else {
            model.addAttribute("festival", true);
        }
        Iterable<Holiday> holidays = holidaysRepository.findAll();
        List<Holiday> holidayList = StreamSupport
                .stream(holidays.spliterator(), false)
                .collect(Collectors.toList());
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}