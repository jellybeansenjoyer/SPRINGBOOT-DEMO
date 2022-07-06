package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class ImplementDao implements PersonDao {
    public static List<Person> DB= new ArrayList<Person>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllObjects() {
        return DB;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person->
            person.getId().equals(id)
        ).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personOrNull = getPersonById(id);
        if(!personOrNull.isEmpty()){
            DB.remove(personOrNull.get());
                    return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(UUID id,Person mPerson) {
        if(!getPersonById(id).isEmpty()){
            getPersonById(id).map(p->{
                UUID uid;
                if(mPerson.getId()==null)
                    uid = p.getId();
                else
                    uid = mPerson.getId();
                Person _p = new Person(uid, mPerson.getName());
                DB.set(DB.indexOf(p),_p);
                return 1;
            });
        }
        return 0;
    }
}
