package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private PersonDao dao;
    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao dao) {
        this.dao = dao;
    }

    public int postPerson(Person person)
    {
        return dao.pushData(person);
    }
    public List<Person> getAllPersons(){
        return dao.getAllObjects();
    }
    public Optional<Person> searchPersonById(UUID id){
        return dao.getPersonById(id);
    }
    public int deletePersonById(UUID id){
        return dao.deletePersonById(id);
    }

    public int updatePersonById(UUID id,Person person){
        return dao.updatePersonById(id,person);
    }
}
