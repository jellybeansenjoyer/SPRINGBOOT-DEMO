package com.example.demo.dao;

import com.example.demo.model.Person;

import java.util.UUID;

public interface PersonDao {

    public int insertPerson(UUID id,Person person);
    default int pushData(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id,person);
    }
}
