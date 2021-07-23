package br.com.yahoo.rodrigokaconde.personapi.service;

import br.com.yahoo.rodrigokaconde.personapi.dto.MessageReponseDTO;
import br.com.yahoo.rodrigokaconde.personapi.dto.request.PersonDTO;
import br.com.yahoo.rodrigokaconde.personapi.entity.Person;
import br.com.yahoo.rodrigokaconde.personapi.exception.PersonNotFoundException;
import br.com.yahoo.rodrigokaconde.personapi.mapper.PersonMapper;
import br.com.yahoo.rodrigokaconde.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException(id);
        }
        return personMapper.toDTO(optionalPerson.get());
    }
}
