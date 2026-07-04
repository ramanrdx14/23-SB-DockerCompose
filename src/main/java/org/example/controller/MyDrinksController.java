package org.example.controller;

import org.example.entity.Drinks;
import org.example.service.DrinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class MyDrinksController {
    @Autowired
    DrinksService drinksService;

    @GetMapping("/")
    public ModelAndView drinks(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("drinks",drinksService.getAllDrinks());
        modelAndView.addObject("emptyObj",new Drinks());
        modelAndView.setViewName("showdrinks");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addDrinks(Drinks drinks){
        drinksService.addDrinks(drinks);
        return "redirect:/";
    }
}
