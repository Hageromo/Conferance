package com.example.accessingdatamysql;

import com.example.accessingdatamysql.Conferance.Conferance;
import com.example.accessingdatamysql.Conferance.ConferanceRepository;

import com.example.accessingdatamysql.Confirmations.Confirm;
import com.example.accessingdatamysql.Exceptions.*;


import com.example.accessingdatamysql.Path.Path;
import com.example.accessingdatamysql.Path.PathRepository;
import com.example.accessingdatamysql.Services.UpdateUser;
import com.example.accessingdatamysql.User.ConfirmedUsers;
import com.example.accessingdatamysql.User.ConfirmedUsersRepository;
import com.example.accessingdatamysql.User.User;
import com.example.accessingdatamysql.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;


@RestController// This means that this class is a Controller
public class MainController {

    @Autowired // This means to get the bean called userRepository
    private UserRepository userRepository;

    @Autowired
    private ConferanceRepository conferanceRepository;

    @Autowired
    private PathRepository pathRepository;

    @Autowired
    private ConfirmedUsersRepository confirmedUsersRepository;

    Confirm confirm = new Confirm();



    @PostMapping(path="/set/user") 							// add new user
    public @ResponseBody String addNewUser (@RequestBody UpdateUser update) {

        User n = new User();

        if(userRepository.findAllByName(update.getName()) == null){
            n.setName(update.getName());
        }else{
            throw new AlreadyExistException(update.getName());
        }

        n.setEmail(update.getEmail());
        userRepository.save(n);

        return "Saved";
    }


    @GetMapping(path="/get/users")													// return all users
    public @ResponseBody Iterable<User> getAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping(path="/get/confirmed")													// return all confirmed users
    public @ResponseBody Iterable<ConfirmedUsers> getAllConfirmedUsers() {

        return confirmedUsersRepository.findAll();
    }


    @GetMapping(path="/get/user/{id}")													// return users by id
    public User getUserById(@PathVariable(value = "id") Integer id) {

        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }


    @GetMapping(path="/get/conferances")													// return all conferances
    public @ResponseBody Iterable<Conferance> getAllConferances() {

        return conferanceRepository.findAll();

    }

    @GetMapping(path="/get/paths")													// return all paths
    public @ResponseBody Iterable<Path> getAllPaths() {

        return pathRepository.findAll();
    }


    @GetMapping("/get/conferance/{id}")                                                     //conferance by id (there are 3)
    public Conferance getConferanceById(@PathVariable(value = "id") Integer id) {

        return this.conferanceRepository.findById(id)
                .orElseThrow(() -> new ConferanceNotFoundException(id));

    }

    @GetMapping("/get/conferance/name/{name}")                                              //conferance by user's login
    public  Conferance getConferanceByLogin(@PathVariable(value = "name") String name) {

        ConfirmedUsers confirmedUsers = confirmedUsersRepository.findAllByName(name);

        if(confirmedUsers != null){
            return conferanceRepository.findAllById(confirmedUsers.getConferance());
        }else{
            throw new TooManyPrelectionException("user has no conferace");
        }

    }


    @PutMapping("/update/user/email/{id}")             // Update your email
    public @ResponseBody void updateUserName(@PathVariable("id") Integer id, @RequestBody UpdateUser update) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setEmail(update.getEmail());

        userRepository.save(user);

    }



    @PutMapping("/update/user/{name}/{email}")     // update your conferance status
    public @ResponseBody void updateUserConferance(@PathVariable("name") String name, @RequestBody UpdateUser update) throws IOException {

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String currentDateTime = dateFormatter.format(new Date());

        User user = userRepository.findAllByName(name);
        Conferance conferance = conferanceRepository.findAllById(update.getConferance());
        Path path = pathRepository.findAllById(update.getConferance());
        ConfirmedUsers confUser = confirmedUsersRepository.findAllByName(user.getName());


        if(confUser == null || !confUser.getConferance().equals(conferance.getId())){   //jezeli user nie chce sie zapisac na ta sama konferencje lub nie ma takiego usera w zapisanych

            ConfirmedUsers confirmedUsers = new ConfirmedUsers();

            confirmedUsers.setName(user.getName());

            if(conferance.getMembers() < 15){
                confirmedUsers.setConferance(conferance.getId()); //ustawia usera na wybrana konferencje
                conferance.setMembers(conferance.getMembers() + 1);   //zmniejsza limit o 1
            }else{
                throw new TooManyPrelectionException("Too many users on this conferation");
            }


            //dodanie sciezki userowi
            if(update.getPath() == 1) {
                if(path.getLimit1() < 5){
                    confirmedUsers.setPath(update.getPath());   //ustawia usera na wybrana sciezke
                    path.setLimit1(path.getLimit1() + 1);     //zmieniejsza limit o 1
                }else {
                    throw new TooManyPrelectionException("Maksymalna ilość osób na tej prelekcji");
                }

            }else if(update.getPath() == 2) {
                if(path.getLimit2() < 5){
                    confirmedUsers.setPath(update.getPath());   //ustawia usera na wybrana sciezke
                    path.setLimit2(path.getLimit2() + 1);     //zmieniejsza limit o 1
                }else {
                    throw new TooManyPrelectionException("Maksymalna ilość osób na tej prelekcji");
                }

            }else if(update.getPath() == 3) {
                if(path.getLimit3() < 5){
                    confirmedUsers.setPath(update.getPath());   //ustawia usera na wybrana sciezke
                    path.setLimit3(path.getLimit3() + 1);     //zmieniejsza limit o 1
                }else {
                    throw new TooManyPrelectionException("Maksymalna ilość osób na tej prelekcji");
                }
            }else{
                throw new TooManyPrelectionException("Too may users on this prelection");
            }

            confirmedUsersRepository.save(confirmedUsers);

            confirm.reservation(confirmedUsers.getName(), user.getEmail(), confirmedUsers.getConferance(), conferance.getHour(), conferance.getDate(), path.getId(), currentDateTime);

        }else{
            throw new TooManyPrelectionException("nie mozna zapisac do tej samej konferencji");
        }

    }


    @GetMapping("/get/path/{name}")                                              //prelections by user's login
    public @ResponseBody Iterable<ConfirmedUsers> getPrelectionsByLogin(@PathVariable(value = "name") String name) {


        if(confirmedUsersRepository.findByName(name) != null) {
            return confirmedUsersRepository.findByName(name);
        }else{
            throw new TooManyPrelectionException("This user has no prelections");
        }


    }


    @DeleteMapping("/cancel/{id}")     // cancel
    public String cancelConferance(@PathVariable("id") Integer id){

        ConfirmedUsers confirmedUsers = confirmedUsersRepository.findById(id)
                .orElseThrow(() -> new ConferanceNotFoundException(id));

        Conferance conferance = conferanceRepository.findAllById(confirmedUsers.getConferance());   //get into conferance and subtract 1 member
        conferance.setMembers(conferance.getMembers() - 1);

        Path path = pathRepository.findAllById(conferance.getId());   //get into path and extend limit for other users

        //deleting prelection
        for(int i = 1; i < 4; i++){
            if(conferance.getId() == i){
                if(confirmedUsers.getPath() == 1){
                    path.setLimit1(path.getLimit1() - 1);

                }else if(confirmedUsers.getPath() == 2){
                    path.setLimit2(path.getLimit2() - 1);

                }else if(confirmedUsers.getPath() == 3){
                    path.setLimit3(path.getLimit3() - 1);
                }
            }
        }

        confirmedUsersRepository.delete(confirmedUsers);

        return "Deleted";

    }


}