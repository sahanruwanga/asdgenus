package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.EEGDataDTO;
import org.codespark.asdgenus.services.database_services.EEGDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eeg-data")
@CrossOrigin(origins = "*")
public class EEGDataController {

    @Autowired
    private EEGDataService eegDataService;

    @PostMapping(path = "/upload")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<EEGDataDTO> uploadEEG(@RequestHeader("uid") int uid, @RequestParam("files") MultipartFile[] files) {

        int id = uid;
        return new ResponseEntity<>(eegDataService.uploadEEG(files), HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> save(@RequestHeader("uid") int uid, @RequestBody EEGDataDTO eegDataDTO) {

        return new ResponseEntity<>(eegDataService.save(eegDataDTO), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<EEGDataDTO> getEEGById(@RequestHeader("uid") int uid, @PathVariable("id") int id) {

        return new ResponseEntity<>(eegDataService.getById(id), HttpStatus.FOUND);
    }

    public void getAll() {
        // Todo
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSubject(@RequestHeader("uid") int uid, @PathVariable("id") int id) {

        eegDataService.delete(id);
        return "Delete successful";
    }
}
