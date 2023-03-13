package com.elenasuslova.peopledbweb.data;

import com.elenasuslova.peopledbweb.biz.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@Component
public class PersonDataLoader implements ApplicationRunner {
    private PersonRepository personRepository;

    public PersonDataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(personRepository.count() == 0) {
            List<Person> people = List.of(
//                    new Person(null, "Jen", "Larson", LocalDate.of(1960, 1, 3), new BigDecimal("50000"), "address@gmail.com"),
//                    new Person(null, "Mark", "Smith", LocalDate.of(1982, 11, 12), new BigDecimal("80000"), "address@gmail.com"),
//                    new Person(null, "Polly", "Polon", LocalDate.of(1975, 3, 19), new BigDecimal("90000"), "address@gmail.com"),
//                    new Person(null, "Olly", "Mag", LocalDate.of(1976, 1, 12), new BigDecimal("110000"), "address@gmail.com"),
//                    new Person(null, "Pete", "Lonny", LocalDate.of(1999, 3, 5), new BigDecimal("75000"), "address@gmail.com"),
//                    new Person(null, "Marty", "Snake", LocalDate.of(1989, 4, 7), new BigDecimal("120000"), "address@gmail.com")
            );

            personRepository.saveAll(people);
        }
    }
}
