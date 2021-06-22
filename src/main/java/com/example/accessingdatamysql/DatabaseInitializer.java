package com.example.accessingdatamysql;

import com.example.accessingdatamysql.Conferance.Conferance;
import com.example.accessingdatamysql.Conferance.ConferanceRepository;
import com.example.accessingdatamysql.Path.Path;
import com.example.accessingdatamysql.Path.PathRepository;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@Component
public class DatabaseInitializer {

    @Autowired
    private ConferanceRepository conferanceRepository;

    @Autowired
    private PathRepository pathRepository;

    private static final Logger LOG
            = Logger.getLogger(DatabaseInitializer.class);

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        LOG.info(Arrays.asList(environment.getDefaultProfiles()));


        Conferance conf = new Conferance();

        conf.setId(1);
        conf.setHour("10:00");
        conf.setEnd("11:45");
        conf.setMembers(0);
        conf.setDate("01.06.2021");
        conferanceRepository.save(conf);

        conf.setId(2);
        conf.setHour("12:00");
        conf.setEnd("13:45");
        conf.setMembers(0);
        conf.setDate("01.06.2021");
        conferanceRepository.save(conf);

        conf.setId(3);
        conf.setHour("14:00");
        conf.setEnd("15:45");
        conf.setMembers(0);
        conf.setDate("01.06.2021");
        conferanceRepository.save(conf);



        Path path = new Path();

        path.setId(1);
        path.setLimit1(0);
        path.setLimit2(0);
        path.setLimit3(0);
        pathRepository.save(path);

        path.setId(2);
        path.setLimit1(0);
        path.setLimit2(0);
        path.setLimit3(0);
        pathRepository.save(path);

        path.setId(3);
        path.setLimit1(0);
        path.setLimit2(0);
        path.setLimit3(0);
        pathRepository.save(path);
    }


}