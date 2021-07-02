package com.example.accessingdatamysql;

import com.example.accessingdatamysql.Exceptions.AlreadyExistException;
import com.example.accessingdatamysql.Services.UpdateUser;
import com.example.accessingdatamysql.User.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainControllerTest {

    @Test
    void addNewUser() {
        //given
        UpdateUser user = new UpdateUser();
        MainController controller = new MainController();
        User n = new User();

        n.setEmail(user.getEmail());
        n.setName(user.getName());



        //when
        String result = controller.addNewUser(user);

        //then
        Assertions.assertThrows(AlreadyExistException.class, () -> controller.addNewUser(user));
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void getAllConfirmedUsers() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void getAllConferances() {
    }

    @Test
    void getAllPaths() {
    }

    @Test
    void getConferanceById() {
    }

    @Test
    void getConferanceByLogin() {
    }

    @Test
    void updateUserName() {
    }

    @Test
    void updateUserConferance() {
    }

    @Test
    void getPrelectionsByLogin() {
    }

    @Test
    void cancelConferance() {
    }
}