package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.ResultDTO;
import org.codespark.asdgenus.dtos.ResultSavingDTO;
import org.codespark.asdgenus.services.database_services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
@CrossOrigin(origins = "*")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping(path = "/save-for-subject", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> saveForExistingSubject(@RequestHeader("uid") int uid,
                                                   @RequestBody ResultSavingDTO resultForSubjectDTO) {

        return new ResponseEntity<>(resultService.saveForSubject(uid, resultForSubjectDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResultDTO> getResultById(@RequestHeader("uid") int uid, @PathVariable("id") int id) {

        return new ResponseEntity<>(resultService.getResultById(id), HttpStatus.FOUND);
    }

    @GetMapping("/get-all/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ResultDTO>> getAllResult(@RequestHeader("uid") int uid, @PathVariable("userId") int userId) {

        return new ResponseEntity<>(resultService.getAll(userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{resultId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteResult(@RequestHeader("uid") int uid,
                                                 @PathVariable("resultId") int resultId) {

        return new ResponseEntity<>(resultService.deleteResult(resultId), HttpStatus.OK);
    }
}
