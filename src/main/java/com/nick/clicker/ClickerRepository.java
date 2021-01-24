package com.nick.clicker;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickerRepository extends CrudRepository<CounterEntity, Long> {

}
