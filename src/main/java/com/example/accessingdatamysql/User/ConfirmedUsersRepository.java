package com.example.accessingdatamysql.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ConfirmedUsersRepository extends CrudRepository<ConfirmedUsers, Integer> {

    ConfirmedUsers findAllByName(String name);

    List<ConfirmedUsers> findByName(String conf);


}

