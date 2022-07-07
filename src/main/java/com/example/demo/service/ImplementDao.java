package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class ImplementDao implements PersonDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ImplementDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static List<Person> DB= new ArrayList<Person>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> getAllObjects() {
        String q = "SELECT id,name FROM person";
        return jdbcTemplate.query(q,(result,i)->{
            UUID id = UUID.fromString(result.getString("id"));
            String name = result.getString("name");
            return new Person(id,name);
        });
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
