package org.codespark.asdgenus.repositories;

import org.codespark.asdgenus.models.EEGData;
import org.springframework.data.repository.CrudRepository;

public interface EEGDataRepository extends CrudRepository<EEGData, Integer> {
}
