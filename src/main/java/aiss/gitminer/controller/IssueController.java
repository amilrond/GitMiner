package aiss.gitminer.controller;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.exception.UserNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import aiss.gitminer.repository.UserRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {

    @Autowired
    IssueRepository repository;

    @Autowired
    UserRepository userRepository;

    // GET http://localhost:8080/gitminer/issues
    @GetMapping
    public List<Issue> findAll() { return repository.findAll(); }

    // GET http://localhost:8080/gitminer/issues?state=open
    @GetMapping("/state")
    public List<Issue> findByState(@RequestParam(required = false) String state) {
        if (state != null) {
            return repository.findByState(state);
        }
        return repository.findAll();
    }

    // GET http://localhost:8080/gitminer/issues/{id}
    @GetMapping("/{id}")
    public Issue findOne(@PathVariable String id) throws IssueNotFoundException {
        Optional<Issue> issue = repository.findById(id);
        if (!issue.isPresent()) {
            throw new IssueNotFoundException();
        }
        return issue.get();
    }

    //POST http://localhost:8080/gitminer/issues
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Issue create(@Valid @RequestBody Issue issue) {
        return repository.save(issue);
    }

    // GET http://localhost:8080/gitminer/issues/{id}/comments
    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsByIssueId(@PathVariable String id) throws IssueNotFoundException {
        Optional<Issue> issue = repository.findById(id);
        if (!issue.isPresent()) {
            throw new IssueNotFoundException();
        }
        return issue.get().getComments();
    }

    // GET http://localhost:8080/gitminer/issues?authorId=5122337
    @GetMapping("/author")
    public List<Issue> findByAuthorId(@RequestParam String authorId) throws UserNotFoundException {
        if (!userRepository.existsById(authorId)) {
            throw new UserNotFoundException();
        }
        return repository.findByAuthor_Id(authorId);
    }
}


