package com.example.accessingdatamysql.User;

import com.example.accessingdatamysql.Conferance.Conferance;
import com.example.accessingdatamysql.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findAllByName(String name);

    //User findAllByLogin(String Login);

}
