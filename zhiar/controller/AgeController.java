package com.zhiar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Controller
public class AgeController {


    @GetMapping("/")
    public String showForm(Model model) {

        model.addAttribute("years", 0);
        model.addAttribute("months", 0);
        model.addAttribute("days", 0);
        model.addAttribute("hours", 0);
        model.addAttribute("minutes", 0);
        model.addAttribute("seconds", 0);
        model.addAttribute("birthdate", "");
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateAge(@RequestParam("birthdate") String birthdate, Model model) {
        LocalDateTime birthDate = LocalDateTime.parse(birthdate + "T00:00:00");
        LocalDateTime currentDate = LocalDateTime.now();

        long years = ChronoUnit.YEARS.between(birthDate, currentDate);
        long months = ChronoUnit.MONTHS.between(birthDate, currentDate) % 12;
        long days = ChronoUnit.DAYS.between(birthDate.plusYears(years).plusMonths(months), currentDate);
        long hours = ChronoUnit.HOURS.between(birthDate.plusDays(days), currentDate) % 24;
        long minutes = ChronoUnit.MINUTES.between(birthDate.plusHours(hours), currentDate) % 60;
        long seconds = ChronoUnit.SECONDS.between(birthDate.plusMinutes(minutes), currentDate) % 60;

        model.addAttribute("years", years);
        model.addAttribute("months", months);
        model.addAttribute("days", days);
        model.addAttribute("hours", hours);
        model.addAttribute("minutes", minutes);
        model.addAttribute("seconds", seconds);
        model.addAttribute("birthdate", birthdate);

        return "index";
    }
}