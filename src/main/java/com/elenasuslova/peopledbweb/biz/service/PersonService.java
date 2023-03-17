package com.elenasuslova.peopledbweb.biz.service;

import com.elenasuslova.peopledbweb.biz.model.Person;
import com.elenasuslova.peopledbweb.data.FileStorageRepository;
import com.elenasuslova.peopledbweb.data.PersonRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final FileStorageRepository storageRepository;

    public PersonService(PersonRepository personRepository, FileStorageRepository storageRepository) {
        this.personRepository = personRepository;
        this.storageRepository = storageRepository;
    }

    @Transactional
    public Person save(Person person, InputStream photoStream) {
        Person savedPerson = personRepository.save(person);
        storageRepository.save(person.getPhotoFileName(), photoStream);
        return savedPerson;
    }

    public Optional<Person> findById(Long aLong) {
        return personRepository.findById(aLong);
    }

    public void deleteAllById(Iterable<Long> ids) {
        Set<String> filenames = personRepository.findFilenamesByIDs(ids);
        personRepository.deleteAllById(ids);
        storageRepository.deleteAllByName(filenames);
    }

    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }
}
