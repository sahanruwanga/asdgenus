package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.models.Result;
import org.codespark.asdgenus.services.ClassifierService;
import org.codespark.asdgenus.services.database_services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/classifier")
public class ClassifierController {

    @Autowired
    private ClassifierService classifierService;

    @Autowired
    private UserService userService;


    @PostMapping("/predict")
    public Result predictASD(@RequestParam("eegPath") String eegPath) {

        Result result = classifierService.getPrediction(eegPath);
        return result;
    }

    @GetMapping("/test")
    public void test(){

        userService.confirmUserPresent();
    }
}
