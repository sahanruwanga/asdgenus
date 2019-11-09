package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.EEGDataDTO;
import org.codespark.asdgenus.dtos.ResultDTO;
import org.codespark.asdgenus.services.database_services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/result")
@CrossOrigin(origins = "*")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> save(@RequestHeader("uid") int uid, @RequestBody ResultDTO resultDTO) {

        return new ResponseEntity<>(resultService.saveResult(resultDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResultDTO> getResultById(@RequestHeader("uid") int uid, @PathVariable("id") int id) {

        return new ResponseEntity<>(resultService.getResultById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSubject(@RequestHeader("uid") int uid, @PathVariable("id") int id) {

        resultService.deleteResult(id);
        return "Delete successful";
    }

    @GetMapping("/get-all/{userId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResultDTO> getAllResult(@RequestHeader("uid") int uid, @PathVariable("userId") int userId) {

        return new ResponseEntity<>(resultService.getAll(userId), HttpStatus.FOUND);
    }
}
