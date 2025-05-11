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
    // GET http://localhost:8080/gitminer/issues?state=open
    // GET http://localhost:8080/gitminer/issues?authorId=5122337
    // GET http://localhost:8080/gitminer/issues?state=open&authorId=5122337
    @GetMapping
    public List<Issue> findAll(
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String authorId) throws IssueNotFoundException, UserNotFoundException {

        List<Issue> issues;

        if (state != null && authorId != null) {
            if (!userRepository.existsById(authorId)) {
                throw new UserNotFoundException();
            }
            issues = repository.findByStateAndAuthor_Id(state, authorId);
        } else if (state != null) {
            issues = repository.findByState(state);
        } else if (authorId != null) {
            if (!userRepository.existsById(authorId)) {
                throw new UserNotFoundException();
            }
            issues = repository.findByAuthor_Id(authorId);
        } else {
            issues = repository.findAll();
        }
        if (issues.isEmpty()) {
            throw new IssueNotFoundException();
        }
        return issues;
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
}


