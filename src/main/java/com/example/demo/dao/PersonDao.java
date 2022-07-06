package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    public int insertPerson(UUID id,Person person);
    default int pushData(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);
    }

    public List<Person> getAllObjects();

    Optional<Person> getPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id,Person person);
}
