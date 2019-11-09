package org.codespark.asdgenus.services.database_services;

import org.codespark.asdgenus.dtos.ResultDTO;
import org.codespark.asdgenus.models.EEGData;
import org.codespark.asdgenus.models.Result;
import org.codespark.asdgenus.models.Subject;
import org.codespark.asdgenus.repositories.EEGDataRepository;
import org.codespark.asdgenus.repositories.ResultRepository;
import org.codespark.asdgenus.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private EEGDataRepository eegDataRepository;

    @Autowired
    private ResultRepository resultRepository;

    /**
     * Save the result in db with corresponding eeg and subject details
     *
     * @param resultDTO
     * @return
     */
    public int saveResult(ResultDTO resultDTO) {

        Subject subject = null;
        EEGData eegData = null;
        if (subjectRepository.findById(resultDTO.getSubjectId()).isPresent())
            subject = subjectRepository.findById(resultDTO.getSubjectId()).get();
        if (eegDataRepository.findById(resultDTO.getEegId()).isPresent())
            eegData = eegDataRepository.findById(resultDTO.getEegId()).get();
        Result result = new Result(resultDTO.getId(), resultDTO.getResult(), resultDTO.getResultDescription(),
                resultDTO.getDateOfTaken());
        if (subject != null && eegData != null) {
            result.setSubject(subject);
            result.setEegData(eegData);
            return resultRepository.save(result).getId();
        } else
            return 0;
    }

    /**
     * Retrieve result by the given result id
     *
     * @param id
     * @return
     */
    public ResultDTO getResultById(int id) {

        Result result = null;
        if (resultRepository.findById(id).isPresent()) {
            result = resultRepository.findById(id).get();
            return new ResultDTO(result.getId(), result.getEegData().getId(), result.getSubject().getId(),
                    result.getResult(), result.getResultDescription(), result.getDateOfTaken());
        }else
            return new ResultDTO();
    }

    public ResultDTO getAll(int userId) {
        // Todo
        return new ResultDTO();
    }

    /**
     * Delete the result by the given result id
     *
     * @param id
     */
    public void deleteResult(int id) {

        resultRepository.deleteById(id);
    }
}
