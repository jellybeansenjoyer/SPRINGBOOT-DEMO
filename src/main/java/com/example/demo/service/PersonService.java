package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonDao dao;
    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao dao) {
        this.dao = dao;
    }

    public int postPerson(Person person)
    {
        return dao.pushData(person);
    }
}
