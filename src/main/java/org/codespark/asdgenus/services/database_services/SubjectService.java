package org.codespark.asdgenus.services.database_services;

import org.codespark.asdgenus.dtos.SubjectDTO;
import org.codespark.asdgenus.models.Subject;
import org.codespark.asdgenus.models.User;
import org.codespark.asdgenus.repositories.SubjectRepository;
import org.codespark.asdgenus.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Create new subject and save
     *
     * @param subjectDto
     * @return
     */
    public int saveSubject(int uid, SubjectDTO subjectDto) {

        Subject newSub = new Subject();
        User user = userRepository.findById(uid).get();
        newSub.setName(subjectDto.getName());
        newSub.setAge(subjectDto.getAge());
        newSub.setGender(subjectDto.getGender());
        newSub.setUser(user);
        return subjectRepository.save(newSub).getId();
    }

    /**
     * Update an existing subject as per the given data
     *
     * @param id
     * @param subjectDTO
     * @return
     */
    public SubjectDTO updateSubject(int id, SubjectDTO subjectDTO) {

        Subject newSub = new Subject();
        newSub.setId(id);
        newSub.setName(subjectDTO.getName());
        newSub.setAge(subjectDTO.getAge());
        newSub.setGender(subjectDTO.getGender());
        newSub.setUser(subjectRepository.findById(id).get().getUser());
        subjectRepository.save(newSub);

        return new SubjectDTO(newSub.getId(), newSub.getName(), newSub.getAge(), newSub.getGender());
    }

    /**
     * Delete the subject of the given subject id
     *
     * @param id
     */
    public int deleteSubject(int id) {

        subjectRepository.deleteById(id);
        return id;
    }

    /**
     * Retrieve the required subject DTO from the subject id
     *
     * @param id
     * @return
     */
    public SubjectDTO getSubjectDTOById(int id) {

        Subject subject = this.getSubjectById(id);
        return new SubjectDTO(subject.getId(), subject.getName(), subject.getAge(), subject.getGender());
    }

    /**
     * Retrieve the required subject from the subject id
     *
     * @param id
     * @return
     */
    public Subject getSubjectById(int id) {

        Subject subject = null;
        if (subjectRepository.findById(id).isPresent())
            subject = subjectRepository.findById(id).get();
        return subject;
    }

    public List<Subject> getAllSubjects(int userId) {

        List<Subject> subjects = new ArrayList<>();
        subjectRepository.findAllByUserId(userId).forEach(subjects::add);
        return subjects;
    }

    public List<Integer> getAllIds(int userId) {

        List<Subject> subjects = new ArrayList<>();
        List<Integer> ids = null;
        subjectRepository.findAllByUserId(userId).forEach(subjects::add);
        if (!subjects.isEmpty()) {
            ids = new ArrayList<>();
            for (Subject subject : subjects) {
                ids.add(subject.getId());
            }
        }
        return ids;
    }
}
