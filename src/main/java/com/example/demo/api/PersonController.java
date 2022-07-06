package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("mapi/v1/person")
@RestController
public class PersonController {
    PersonService service;
    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }
    @PostMapping
    public void addPerson(@RequestBody Person person)
    {
        service.postPerson(person);
    }
    @GetMapping
    public List<Person> getAllPersons(){
        return service.getAllPersons();
    }

}
