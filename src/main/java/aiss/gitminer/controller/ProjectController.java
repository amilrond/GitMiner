package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.*;
import aiss.gitminer.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {

    @Autowired
    ProjectRepository repository;


    //GET http://localhost:8080/api/projects
    @GetMapping
    public List<Project> findAll() { return repository.findAll(); }

    //GET http://localhost:8080/api/projects/{id}
    @GetMapping("/{id}")
    public Project findOne(@PathVariable String id) throws ProjectNotFoundException {
        Optional<Project> project = repository.findById(id);
        if (!project.isPresent()) {
            throw new ProjectNotFoundException();
        }
        return project.get();
    }

    //POST http://localhost:8080/api/projects
   @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Project create(@Valid @RequestBody Project project) {
       return repository.save(project);
    }


    //PUT http://localhost:8080/api/projects/{id}
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Project updatedProject, @PathVariable String id) throws ProjectNotFoundException {
        Optional<Project> projectData = repository.findById(id);
        if (!projectData.isPresent()) {
            throw new ProjectNotFoundException();
        }

        Project _project = projectData.get();
        _project.setName(updatedProject.getName());
        _project.setWebUrl(updatedProject.getWebUrl());
        _project.setCommits(updatedProject.getCommits());
        _project.setIssues(updatedProject.getIssues());

        repository.save(_project);
    }

    //DELETE http://localhost:8080/api/projects/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws ProjectNotFoundException {
        if (!repository.existsById(id)) {
            throw new ProjectNotFoundException();
        }
        repository.deleteById(id);
    }
}

