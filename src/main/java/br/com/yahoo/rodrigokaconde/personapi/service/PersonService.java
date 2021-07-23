package br.com.yahoo.rodrigokaconde.personapi.service;

import br.com.yahoo.rodrigokaconde.personapi.dto.MessageReponseDTO;
import br.com.yahoo.rodrigokaconde.personapi.dto.request.PersonDTO;
import br.com.yahoo.rodrigokaconde.personapi.entity.Person;
import br.com.yahoo.rodrigokaconde.personapi.mapper.PersonMapper;
import br.com.yahoo.rodrigokaconde.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageReponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);
        return MessageReponseDTO
                .builder()
                .message("Created person with ID"+savedPerson.getId()).build();
    }
}
