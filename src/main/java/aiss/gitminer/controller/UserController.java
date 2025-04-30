package aiss.gitminer.controller;

import aiss.gitminer.exception.UserNotFoundException;
import aiss.gitminer.model.User;
import aiss.gitminer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    UserRepository repository;

    //GET http://localhost:8080/api/users
    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }


    //GET http://localhost:8080/api/users/{id}
    @GetMapping("/{id}")
    public  User findOne(@PathVariable long id) throws UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }



}
