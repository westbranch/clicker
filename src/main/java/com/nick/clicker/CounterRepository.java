package com.nick.clicker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounterRepository extends CrudRepository<CounterEntity, Long> {
    /**
     * Accepts value type and returns first unique record.
     * @param valueType value type
     * @return
     */
    Optional<CounterEntity> findDistinctByValueType(String valueType);
}
