package com.elenasuslova.peopledbweb.web.controller;

import com.elenasuslova.peopledbweb.biz.model.Person;
import com.elenasuslova.peopledbweb.data.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private PersonRepository personRepository;

    public PeopleController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @ModelAttribute("people")
    public Iterable<Person> getPeople() {
        return personRepository.findAll();
    }
    @GetMapping
    public String showPeoplePage(){
        return "people";
  }

    @ModelAttribute()
    public Person getPerson(){
        return new Person();
    }
    @PostMapping
    public String savePerson(@Valid Person person, Errors errors){
        System.out.println(person);
        if(!errors.hasErrors()) {
            personRepository.save(person);
            return "redirect:people";
        }
        else {
            return "people";
        }
    }

    @PostMapping(params = "delete=true")
    public String deletePeople(@RequestParam Optional<List<Long>> selections) {
        if (selections.isPresent()) {
            personRepository.deleteAllById(selections.get());
        }
        return "redirect:people";
    }
}
