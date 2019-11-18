package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.ResultDTO;
import org.codespark.asdgenus.services.ClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classifier")
@CrossOrigin(origins = "*")
public class ClassifierController {

    @Autowired
    private ClassifierService classifierService;

    @PostMapping("/predict")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResultDTO> predictASD(@RequestHeader("uid") int uid,
                                                @RequestParam("eegPath") String eegPath) {

        return new ResponseEntity<>(classifierService.getPrediction(eegPath), HttpStatus.OK);
    }
}
