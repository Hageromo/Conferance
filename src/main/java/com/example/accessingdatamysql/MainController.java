package com.example.accessingdatamysql;

import com.example.accessingdatamysql.Conferance.Conferance;
import com.example.accessingdatamysql.Conferance.ConferanceRepository;

import com.example.accessingdatamysql.Confirmations.Confirm;
import com.example.accessingdatamysql.Exceptions.AlreadyExistException;
import com.example.accessingdatamysql.Exceptions.ConferanceNotFoundException;
import com.example.accessingdatamysql.Exceptions.UserNotFoundException;

import com.example.accessingdatamysql.Services.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;


@RestController// This means that this class is a Controller
public class MainController {
	@Autowired // This means to get the bean called userRepository
	private UserRepository userRepository;

	@Autowired
	private ConferanceRepository conferanceRepository;

	Confirm confirm = new Confirm();



    @PostMapping(path="/set/user") 							// add new user
	public @ResponseBody String addNewUser (@RequestBody UpdateUser update) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

        User n = new User();

        if(userRepository.findAllByName(update.getName()) == null){
            n.setName(update.getName());
        }else{
            throw new AlreadyExistException(update.getName());
        }

		n.setEmail(update.getEmail());
		n.setMember(false);
		userRepository.save(n);

		return "Saved";
	}


	@GetMapping(path="/get/users")													// return all users
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path="/get/user/{id}")													// return users by id
    public User getUserById(@PathVariable(value = "id") Integer id) {

        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

    }


	@GetMapping(path="/get/conferances")													// return all conferances
	public @ResponseBody Iterable<Conferance> getAllConferances() {
		// This returns a JSON or XML with the users

		return conferanceRepository.findAll();
	}


	@GetMapping("/get/conferance/{id}")                                                     //conferance by id (there are 3)
	public Conferance getConferanceById(@PathVariable(value = "id") Integer id) {

		return this.conferanceRepository.findById(id)
				.orElseThrow(() -> new ConferanceNotFoundException(id));

	}

    @GetMapping("/get/conferance/name/{name}")                                              //conferance by user's login
    public Conferance getConferanceByLogin(@PathVariable(value = "name") String name) {

	    User user = userRepository.findAllByName(name);

        if(user.getConferance() != null){
            return conferanceRepository.findAllById(user.getConferance());
        }else{
            throw new ConferanceNotFoundException(user.getConferance());
        }


    }


	@PutMapping("/update/user/email/{id}")             // Update your email
	public @ResponseBody void updateUserName(@PathVariable("id") Integer id, @RequestBody UpdateUser update) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));

		user.setEmail(update.getEmail());

		userRepository.save(user);

    }


    @PutMapping("/update/user/conferance/{id}")     // update your conferance status
    public @ResponseBody void updateUserConferance(@PathVariable("id") Integer id, @RequestBody UpdateUser update) throws IOException {


        User user = userRepository.findById(id)
                .orElseThrow(() -> new ConferanceNotFoundException(id));

        Integer presentConf = user.getConferance();       // conferance that user is on

        user.setConferance(update.getConferance());     // user pick conferance 1 2 3 or null

        userRepository.save(user);

        if(user.getConferance() != null){
            Conferance conf = conferanceRepository.findById(user.getConferance())     // id conference 1 2 or 3
                    .orElseThrow(() -> new ConferanceNotFoundException(user.getConferance()));


            if(conf.getMembers() < 5){
                conf.setMembers(conf.getMembers() + 1);
                user.setMember(true);

                confirm.reservation(user.getName(), user.getEmail(), user.getConferance(), conf.getHour(), conf.getDate());

            }else if(conf.getMembers() == null){
                conf.setMembers(1);
                user.setMember(true);

            } else{
                throw new ConferanceNotFoundException(conf.getMembers());   // there cannot be any more members in this conferance
            }

            conferanceRepository.save(conf);
        }else{
            user.setMember(false);
        }

        if(presentConf != null){
            Conferance conf2 = conferanceRepository.findById(presentConf)     // id conference 1 2 or 3 that user was before
                    .orElseThrow(() -> new ConferanceNotFoundException(presentConf));

            if(conf2.getMembers() != 0){
                conf2.setMembers(conf2.getMembers() - 1);
            }

            conferanceRepository.save(conf2);
        }

    }






    // Raport ze wszystkich użytkowników którzy maja


	/*	To do
	wysłanie wiadomosci po poprawnym dokonaniu rezerwacji
	*/
}
