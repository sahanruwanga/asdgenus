package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.SubjectDTO;
import org.codespark.asdgenus.models.Subject;
import org.codespark.asdgenus.services.database_services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> saveSubject(@RequestBody SubjectDTO subjectDto) {

        return new ResponseEntity<>(subjectService.saveSubject(subjectDto), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubjectDTO> updateSubject(@PathVariable("id") int id,
                                                @RequestBody SubjectDTO subjectDTO){

        return new ResponseEntity<>(subjectService.updateSubject(id, subjectDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSubject(@PathVariable("id") int id) {

        subjectService.deleteSubject(id);
        return "Delete successful";
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable("id") int id){

        return new ResponseEntity<>(subjectService.getSubjectById(id), HttpStatus.FOUND);
    }

    @GetMapping("/get")
    public ArrayList<Subject> getSubjects() {

        // Todo
        return subjectService.getAllSubjects();
    }
}
