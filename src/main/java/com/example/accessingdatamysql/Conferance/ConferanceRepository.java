package com.example.accessingdatamysql.Conferance;

import com.example.accessingdatamysql.Conferance.Conferance;
import org.springframework.data.repository.CrudRepository;

public interface ConferanceRepository extends CrudRepository<Conferance, Integer> {

    Conferance findAllById(Integer conferance);
}