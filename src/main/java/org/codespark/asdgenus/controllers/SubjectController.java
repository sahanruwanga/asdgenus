package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.SubjectDTO;
import org.codespark.asdgenus.models.Subject;
import org.codespark.asdgenus.services.database_services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@CrossOrigin(origins = "*")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> saveSubject(@RequestHeader("uid") int uid, @RequestBody SubjectDTO subjectDTO) {

        return new ResponseEntity<>(subjectService.saveSubject(uid, subjectDTO), HttpStatus.CREATED);
    }

    @PutMapping(path = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubjectDTO> updateSubject(@RequestHeader("uid") int uid, @PathVariable("id") int id,
                                                    @RequestBody SubjectDTO subjectDTO) {

        return new ResponseEntity<>(subjectService.updateSubject(id, subjectDTO), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SubjectDTO> getSubjectById(@RequestHeader("uid") int uid, @PathVariable("id") int id) {

        return new ResponseEntity<>(subjectService.getSubjectDTOById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/get-all/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Subject>> getSubjects(@RequestHeader("uid") int uid, @PathVariable("userId") int userId) {

        return new ResponseEntity<>(subjectService.getAllSubjects(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/ids/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Integer>> getAllSubjectIds(@RequestHeader("uid") int uid, @PathVariable("userId") int userId) {

        return new ResponseEntity<>(subjectService.getAllIds(userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteSubject(@RequestHeader("uid") int uid, @PathVariable("id") int id) {

        return new ResponseEntity<>(subjectService.deleteSubject(id), HttpStatus.OK);
    }
}
