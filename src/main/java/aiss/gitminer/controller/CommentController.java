package aiss.gitminer.controller;


import aiss.gitminer.model.Comment;
import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentRepository repository;

    //GET http://localhost:8080/api/comments
    @GetMapping
    public List<Comment> findAll() {
        return repository.findAll();
    }

    //GET http://localhost:8080/api/comments/{id}
    @GetMapping("/{id}")
    public Optional<Comment> findOne(@PathVariable long id) throws CommentNotFoundException {
        Optional<Comment> comment = repository.findById(id);
        if (!comment.isPresent()) {
            throw new CommentNotFoundException();
        }
        return comment;
    }

}
