package com.example.accessingdatamysql.Path;


import org.springframework.data.repository.CrudRepository;

public interface PathRepository extends CrudRepository<Path, Integer> {

    Path findAllById(Integer conferance);

}