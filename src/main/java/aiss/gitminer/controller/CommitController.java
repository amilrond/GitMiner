package aiss.gitminer.controller;

import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/commits")
public class CommitController {

    @Autowired
    CommitRepository repository;

    //GET http://localhost:8080/gitminer/commits
    @GetMapping
    public List<Commit> findAll() { return repository.findAll(); }

    //GET http://localhost:8080/gitminer/commits/{id}
    @GetMapping("/{id}")
    public Commit findOne(@PathVariable String id) throws CommitNotFoundException {
        Optional<Commit> commit = repository.findById(id);
        if (!commit.isPresent()) {
            throw new CommitNotFoundException();
        }
        return commit.get();
    }

    //POST http://localhost:8080/gitminer/commits
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Commit update(@Valid @RequestBody Commit commit) {
        return repository.save(commit);
    }

}

