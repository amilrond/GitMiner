package aiss.gitminer.controller;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    @Autowired
    IssueRepository repository;

    // GET http://localhost:8080/api/issues
    // GET http://localhost:8080/api/issues?state=open
    @GetMapping
    public List<Issue> findAll(@RequestParam(required = false) String state) {
        if (state != null) {
            return repository.findByState(state);
        }
        return repository.findAll();
    }

    // GET http://localhost:8080/api/issues/{id}
    @GetMapping("/{id}")
    public Issue findOne(@PathVariable long id) throws IssueNotFoundException {
        Optional<Issue> issue = repository.findById(id);
        if (!issue.isPresent()) {
            throw new IssueNotFoundException();
        }
        return issue.get();
    }
}
