package org.codespark.asdgenus.services.database_services;

import org.codespark.asdgenus.dtos.EEGDataDTO;
import org.codespark.asdgenus.models.EEGData;
import org.codespark.asdgenus.models.Subject;
import org.codespark.asdgenus.repositories.EEGDataRepository;
import org.codespark.asdgenus.repositories.SubjectRepository;
import org.codespark.asdgenus.services.FileStorageService;
import org.codespark.asdgenus.services.visualization.VisualizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class EEGDataService {

    @Autowired
    private EEGDataRepository eegDataRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private VisualizationService visualizationService;

    /**
     * Save new EEG data in db
     *
     * @param eegDataDTO
     * @return
     */
    public int save(EEGDataDTO eegDataDTO) {

        Subject subject = subjectRepository.findById(eegDataDTO.getSubjectId()).get();
        EEGData eegData = new EEGData(eegDataDTO.getId(), eegDataDTO.getNumberOfChannels(),
                eegDataDTO.getChannelNames(), eegDataDTO.getDuration(), eegDataDTO.getRecordedDate(),
                eegDataDTO.getDataLocation(), eegDataDTO.getSignalLocation());
        eegData.setSubject(subject);
        return eegDataRepository.save(eegData).getId();
    }

    /**
     * Save new EEG data in db for the given subject ID
     *
     * @param eegDataDTO
     * @return
     */
    public EEGData saveForSubject(EEGDataDTO eegDataDTO, int subjectId) {

        Subject subject = null;
        if (subjectRepository.findById(subjectId).isPresent())
             subject = subjectRepository.findById(subjectId).get();
        EEGData eegData = new EEGData(eegDataDTO.getId(), eegDataDTO.getNumberOfChannels(),
                eegDataDTO.getChannelNames(), eegDataDTO.getDuration(), eegDataDTO.getRecordedDate(),
                eegDataDTO.getDataLocation(), eegDataDTO.getSignalLocation());
        eegData.setSubject(subject);
        return eegDataRepository.save(eegData);
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
                eegData.getChannelNames(), eegData.getDuration(), eegData.getRecordedDate(),
                eegData.getDataLocation(), eegData.getSignalLocation());
    }

    /**
     * Get all eeg data for the given user ID
     *
     * @param userId
     * @return
     */
    public List<EEGDataDTO> getAll(int userId) {

        List<Subject> subjects = subjectService.getAllSubjects(userId);
        List<EEGData> eegDataList = null;
        List<EEGDataDTO> eegDataDTOList = null;
        if (!subjects.isEmpty()) {
            eegDataList = new ArrayList<>();
            for (Subject subject : subjects) {
                eegDataRepository.findAllBySubjectId(subject.getId()).forEach(eegDataList::add);
            }
        }
        if (eegDataList != null) {
            eegDataDTOList = new ArrayList<>();
            for (EEGData eeg : eegDataList) {
                eegDataDTOList.add(new EEGDataDTO(eeg.getId(), eeg.getNumberOfChannels(), eeg.getSubject().getId(),
                        eeg.getChannelNames(), eeg.getDuration(), eeg.getRecordedDate(), eeg.getDataLocation(),
                        eeg.getSignalLocation()));
            }
        }
        return eegDataDTOList;
    }

    /**
     * Delete required saved EEG data
     *
     * @param eegId
     */
    public int delete(int eegId) {

        eegDataRepository.deleteById(eegId);
        return eegId;
    }

    /**
     * Upload and save the given EEG data
     *
     * @param files
     * @return
     */
    public EEGDataDTO uploadEEG(int uid, MultipartFile[] files) {
        String vhdrFilePath = "";
        for (int i = 0; i < files.length; i++) {
            if (files[i].getOriginalFilename().endsWith("vhdr"))
                vhdrFilePath = fileStorageService.storeFile(files[i]);
            else
                fileStorageService.storeFile(files[i]);
        }
//        vhdrFilePath = fileStorageService.storeFile(file);
        EEGDataDTO eegDataDTO = new EEGDataDTO();
        eegDataDTO.setDataLocation(vhdrFilePath);
        eegDataDTO.setSignalLocation(visualizationService.plotEEGSignal(uid, vhdrFilePath));
        return eegDataDTO;
    }
}
