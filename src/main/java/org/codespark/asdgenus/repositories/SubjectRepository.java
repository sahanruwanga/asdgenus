package org.codespark.asdgenus.repositories;

import org.codespark.asdgenus.models.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    List<Subject> findAllByUserId(int userId);
}
