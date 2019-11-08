package org.codespark.asdgenus.services.database_services;

import org.codespark.asdgenus.dtos.EEGDataDTO;
import org.codespark.asdgenus.models.EEGData;
import org.codespark.asdgenus.models.Subject;
import org.codespark.asdgenus.repositories.EEGDataRepository;
import org.codespark.asdgenus.repositories.SubjectRepository;
import org.codespark.asdgenus.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EEGDataService {

    @Autowired
    private EEGDataRepository eegDataRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private FileStorageService fileStorageService;

    /**
     * Save new EEG data in db
     *
     * @param eegDataDTO
     * @return
     */
    public int save(EEGDataDTO eegDataDTO) {

        Subject subject = subjectRepository.findById(eegDataDTO.getSubjectId()).get();
        EEGData eegData = new EEGData(eegDataDTO.getId(), eegDataDTO.getNumberOfChannels(),
                eegDataDTO.getChannelNames(), eegDataDTO.getDuration(), eegDataDTO.getRecordedDate(), eegDataDTO.getDataLocation());
        eegData.setSubject(subject);
        return eegDataRepository.save(eegData).getId();
    }

    /**
     * Get saved EEG by the eeg id
     *
     * @param id
     * @return
     */
    public EEGDataDTO getById(int id) {

        EEGData eegData = eegDataRepository.findById(id).get();
        return new EEGDataDTO(eegData.getId(), eegData.getNumberOfChannels(), eegData.getSubject().getId(),
                eegData.getChannelNames(), eegData.getDuration(), eegData.getRecordedDate(), eegData.getDataLocation());
    }

    public void getAll() {
        // Todo
    }

    /**
     * Delete required saved EEG data
     *
     * @param id
     */
    public void delete(int id) {

        eegDataRepository.deleteById(id);
    }

    public EEGDataDTO uploadEEG(MultipartFile[] files){
        String vhdrFilePath = "";
        for (int i = 0; i < files.length; i++) {
            if (files[i].getOriginalFilename().endsWith("vhdr"))
                vhdrFilePath = fileStorageService.storeFile(files[i]);
            else
                fileStorageService.storeFile(files[i]);
        }
        EEGDataDTO eegDataDTO = new EEGDataDTO();
        eegDataDTO.setDataLocation(vhdrFilePath);
        return eegDataDTO;
    }
}
