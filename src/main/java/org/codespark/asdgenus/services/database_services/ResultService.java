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

        Subject subject = subjectRepository.findById(resultDTO.getSubjectId()).get();
        EEGData eegData = eegDataRepository.findById(resultDTO.getEegId()).get();
        Result result = new Result(resultDTO.getId(), resultDTO.getResult(), resultDTO.getResultDescription(),
                resultDTO.getDateOfTaken());
        result.setSubject(subject);
        result.setEegData(eegData);
        return resultRepository.save(result).getId();

    }

    /**
     * Retrieve result by the given result id
     *
     * @param id
     * @return
     */
    public ResultDTO getResultById(int id) {

        Result result = resultRepository.findById(id).get();
        return new ResultDTO(result.getId(), result.getEegData().getId(), result.getSubject().getId(),
                result.getResult(), result.getResultDescription(), result.getDateOfTaken());
    }

    public void getAll() {
        // Todo
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
