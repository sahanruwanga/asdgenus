package org.codespark.asdgenus.repositories;

import org.codespark.asdgenus.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
