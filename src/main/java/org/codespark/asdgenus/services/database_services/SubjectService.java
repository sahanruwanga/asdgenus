package org.codespark.asdgenus.services.database_services;

import org.codespark.asdgenus.dtos.SubjectDTO;
import org.codespark.asdgenus.models.Subject;
import org.codespark.asdgenus.models.User;
import org.codespark.asdgenus.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService {

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Create new subject and save
     *
     * @param subjectDto
     * @return
     */
    public int saveSubject(SubjectDTO subjectDto) {

        Subject newSub = new Subject();
        User user = userService.confirmUserPresent();
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
    public void deleteSubject(int id) {

        subjectRepository.deleteById(id);
    }

    /**
     * Retrieve the required subject from the subject id
     *
     * @param id
     * @return
     */
    public SubjectDTO getSubjectById(int id) {

        Subject subject = subjectRepository.findById(id).get();
        return new SubjectDTO(subject.getId(), subject.getName(), subject.getAge(), subject.getGender());
    }

    public ArrayList<Subject> getAllSubjects() {

        ArrayList<Subject> subjects = new ArrayList<>();
        subjectRepository.findAll().forEach(subjects::add);
//        for (Subject subject : subjectIterable)
//            subjects.add(subject);
        return subjects;
    }


}
