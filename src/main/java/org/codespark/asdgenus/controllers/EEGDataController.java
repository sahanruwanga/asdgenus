package org.codespark.asdgenus.controllers;

import org.codespark.asdgenus.dtos.EEGDataDTO;
import org.codespark.asdgenus.models.EEGData;
import org.codespark.asdgenus.services.database_services.EEGDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eeg-data")
@CrossOrigin(origins = "*")
public class EEGDataController {

    @Autowired
    private EEGDataService eegDataService;

    @PostMapping(path = "/upload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EEGDataDTO> uploadEEG(@RequestHeader("uid") int uid,
                                                @RequestParam("files") MultipartFile[] files) {

        return new ResponseEntity<>(eegDataService.uploadEEG(uid, files), HttpStatus.OK);
    }

    @PostMapping(path = "/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Integer> save(@RequestHeader("uid") int uid, @RequestBody EEGDataDTO eegDataDTO) {

        return new ResponseEntity<>(eegDataService.save(eegDataDTO), HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EEGDataDTO> getEEGById(@RequestHeader("uid") int uid, @PathVariable("id") int id) {

        return new ResponseEntity<>(eegDataService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/get-all/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EEGDataDTO>> getAll(@RequestHeader("uid") int uid,
                                                @PathVariable("userId") int userId) {

        return new ResponseEntity<>(eegDataService.getAll(userId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{eegId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteSubject(@RequestHeader("uid") int uid,
                                                 @PathVariable("eegId") int eegId) {

        return new ResponseEntity<>(eegDataService.delete(eegId), HttpStatus.OK);
    }
}
