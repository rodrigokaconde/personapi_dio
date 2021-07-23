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
        return createMessageResponse(savedPerson.getId(), "Created person with ID");
}
    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }


    public MessageReponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);
        Person updatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatePerson.getId(), "Update person with ID");

    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }


    private MessageReponseDTO createMessageResponse(Long id, String message) {
        return MessageReponseDTO
                .builder()
                .message(message + id).build();
    }
}
