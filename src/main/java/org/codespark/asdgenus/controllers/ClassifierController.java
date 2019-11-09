package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.models.Result;
import org.codespark.asdgenus.services.ClassifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classifier")
@CrossOrigin(origins = "*")
public class ClassifierController {

    @Autowired
    private ClassifierService classifierService;

    @PostMapping("/predict")
    public Result predictASD(@RequestHeader("uid") int uid, @RequestParam("eegPath") String eegPath) {

        Result result = classifierService.getPrediction(eegPath);
        return result;
    }
}
