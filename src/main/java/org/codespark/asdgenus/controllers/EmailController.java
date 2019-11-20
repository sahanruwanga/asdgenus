package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.EmailDTO;
import org.codespark.asdgenus.dtos.ResultDTO;
import org.codespark.asdgenus.dtos.ResultSavingDTO;
import org.codespark.asdgenus.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(path = "/send", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> sendMail(@RequestHeader("uid") int uid,
                                              @RequestBody EmailDTO  emailDTO) {

        return new ResponseEntity<>(emailService.sendMail(uid, emailDTO), HttpStatus.CREATED);
    }
}
