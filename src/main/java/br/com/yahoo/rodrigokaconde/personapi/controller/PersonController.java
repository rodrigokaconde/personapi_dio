package br.com.yahoo.rodrigokaconde.personapi.controller;

import br.com.yahoo.rodrigokaconde.personapi.dto.MessageReponseDTO;
import br.com.yahoo.rodrigokaconde.personapi.entity.Person;
import br.com.yahoo.rodrigokaconde.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageReponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }
}
