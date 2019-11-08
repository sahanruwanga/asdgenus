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
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> save(@RequestBody ResultDTO resultDTO) {

        return new ResponseEntity<>(resultService.saveResult(resultDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<ResultDTO> getEEDById(@PathVariable("id") int id) {

        return new ResponseEntity<>(resultService.getResultById(id), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSubject(@PathVariable("id") int id) {

        resultService.deleteResult(id);
        return "Delete successful";
    }
}
