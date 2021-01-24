package com.nick.clicker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClickerRepository extends CrudRepository<CounterEntity, Long> {
    Optional<CounterEntity> findDistinctByValueType(String valueType);
}
