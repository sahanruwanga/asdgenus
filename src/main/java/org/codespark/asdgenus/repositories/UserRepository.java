package org.codespark.asdgenus.repositories;

import org.codespark.asdgenus.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);
}
