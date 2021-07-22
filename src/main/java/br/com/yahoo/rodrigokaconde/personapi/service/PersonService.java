package br.com.yahoo.rodrigokaconde.personapi.service;

import br.com.yahoo.rodrigokaconde.personapi.dto.MessageReponseDTO;
import br.com.yahoo.rodrigokaconde.personapi.entity.Person;
import br.com.yahoo.rodrigokaconde.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageReponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageReponseDTO.builder().message("Created person with ID"+savedPerson.getId()).build();
    }
}
