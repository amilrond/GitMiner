package aiss.gitminer.controller;

import aiss.gitminer.exception.UserNotFoundException;
import aiss.gitminer.model.User;
import aiss.gitminer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public  User findOne(@PathVariable String id) throws UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    // POST http://localhost:8080/api/users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }




}
