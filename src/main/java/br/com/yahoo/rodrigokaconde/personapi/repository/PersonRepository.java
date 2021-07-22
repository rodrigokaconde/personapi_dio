package br.com.yahoo.rodrigokaconde.personapi.repository;

import br.com.yahoo.rodrigokaconde.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// passar a classe que vai ser adionada ao repositorio e o tipo do id
// implementa as operações basicas do JPA
public interface PersonRepository extends JpaRepository<Person, Long> {
}
