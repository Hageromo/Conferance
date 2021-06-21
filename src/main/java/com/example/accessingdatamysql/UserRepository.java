package com.example.accessingdatamysql;

import com.example.accessingdatamysql.Conferance.Conferance;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findAllByName(String name);


    int findAllById(Optional<Conferance> byId);
}
