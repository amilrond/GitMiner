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
@RequestMapping("/gitminer/users")
public class UserController {

    @Autowired
    UserRepository repository;

    //GET http://localhost:8080/gitminer/users
    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }


    //GET http://localhost:8080/gitminer/users/{id}
    @GetMapping("/{id}")
    public  User findOne(@PathVariable String id) throws UserNotFoundException {
        Optional<User> user = repository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }
        return user.get();
    }

    // POST http://localhost:8080/gitminer/users
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return repository.save(user);
    }




}
