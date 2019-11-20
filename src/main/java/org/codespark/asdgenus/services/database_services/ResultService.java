package org.codespark.asdgenus.services.database_services;

import org.codespark.asdgenus.dtos.EEGDataDTO;
import org.codespark.asdgenus.dtos.ResultDTO;
import org.codespark.asdgenus.dtos.ResultSavingDTO;
import org.codespark.asdgenus.dtos.SubjectDTO;
import org.codespark.asdgenus.models.EEGData;
import org.codespark.asdgenus.models.Result;
import org.codespark.asdgenus.models.Subject;
import org.codespark.asdgenus.repositories.EEGDataRepository;
import org.codespark.asdgenus.repositories.ResultRepository;
import org.codespark.asdgenus.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private EEGDataRepository eegDataRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private EEGDataService eegDataService;

    /**
     * Save result for an existing subject
     *
     * @param resultSavingDTO
     * @return
     */
    public ResultDTO saveForSubject(int uid, ResultSavingDTO resultSavingDTO) {

        EEGDataDTO eegDataDTO = new EEGDataDTO(resultSavingDTO.getEegId(), resultSavingDTO.getNumberOfChannels(),
                resultSavingDTO.getSubjectId(), resultSavingDTO.getChannelNames(), resultSavingDTO.getDuration(),
                resultSavingDTO.getRecordedDate(), resultSavingDTO.getDataLocation(), resultSavingDTO.getSignalLocation());
        int subjectId = resultSavingDTO.getSubjectId();
        Subject subject = null;
        int subId;
        if (subjectId != -1) {
            subject = subjectService.getSubjectById(subjectId);
            subId = subjectId;
        } else {
            subId = subjectService.saveSubject(uid, new SubjectDTO(subjectId, resultSavingDTO.getSubjectName(),
                    resultSavingDTO.getAge(), resultSavingDTO.getSubjectGender()));
            subject = subjectService.getSubjectById(subId);
        }

        EEGData eegData = eegDataService.saveForSubject(eegDataDTO, subId);
        Result result = new Result(resultSavingDTO.getResultId(), resultSavingDTO.getResult(), resultSavingDTO.getResultDescription(),
                resultSavingDTO.getDateOfTaken());
        if (subject != null && eegData != null) {
            result.setSubject(subject);
            result.setEegData(eegData);
            Result newResult = resultRepository.save(result);
            return new ResultDTO(newResult.getId(), eegData.getId(), subId, newResult.getResult(),
                    newResult.getResultDescription(), newResult.getDateOfTaken());
        } else
            return null;
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
        } else
            return new ResultDTO();
    }

    /**
     * Get all results for the given user
     *
     * @param userId
     * @return
     */
    public List<ResultDTO> getAll(int userId) {

        List<Subject> subjects = subjectService.getAllSubjects(userId);
        List<Result> results = null;
        List<ResultDTO> resultDTOs = null;
        if (!subjects.isEmpty()) {
            results = new ArrayList<>();
            for (Subject subject : subjects) {
                resultRepository.findAllBySubjectId(subject.getId()).forEach(results::add);
            }
        }
        if (results != null) {
            resultDTOs = new ArrayList<>();
            for (Result rs : results) {
                resultDTOs.add(new ResultDTO(rs.getId(), rs.getEegData().getId(), rs.getSubject().getId(),
                        rs.getResult(), rs.getResultDescription(), rs.getDateOfTaken()));
            }
        }
        return resultDTOs;
    }

    /**
     * Get all results for the given subject of the user
     *
     * @param subjectId
     * @return
     */
    public List<ResultDTO> getAllForSubject(int subjectId) {

        List<Result> results = new ArrayList<>();
        List<ResultDTO> resultDTOs = null;
        resultRepository.findAllBySubjectId(subjectId).forEach(results::add);
        if (!results.isEmpty()) {
            resultDTOs = new ArrayList<>();
            for (Result rs : results) {
                resultDTOs.add(new ResultDTO(rs.getId(), rs.getEegData().getId(), rs.getSubject().getId(),
                        rs.getResult(), rs.getResultDescription(), rs.getDateOfTaken()));
            }
        }
        return resultDTOs;
    }

    /**
     * Delete the result by the given result id
     *
     * @param resultId
     */
    public int deleteResult(int resultId) {

        resultRepository.deleteById(resultId);
        return resultId;
    }
}
