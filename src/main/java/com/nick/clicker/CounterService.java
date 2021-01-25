package com.nick.clicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Performs operations with clicker counter, such as increment and get current value
 */
@Service
public class CounterService {

    private static final long SINGLE_ENTITY_ID = 1L;
    private AtomicInteger clickCount = new AtomicInteger();

    @Autowired
    private CounterRepository repository;

    /**
     * Increments counter and returns post-increment value.
     * @return post-increment counter value.
     */
    public synchronized int incrementCounterAndGet() {
        int result = clickCount.incrementAndGet();
        saveToDB(result);
        return result;
    }

    /**
     * Retrieves current counter variable value.
     * @return current counter value.
     */
    public int getCurrentClickCount() {
        return clickCount.get();
    }

    /**
     * Sets current counter value from DB.
     * Automatically triggered after server launch.
     */
    @PostConstruct
    public void setCurrentCounterValue() {
        clickCount.set(getCurrentCounterValueFromDB());
    }

    private void saveToDB(int counterValue) {
        var entityOpt = repository.findById(SINGLE_ENTITY_ID);
        if (entityOpt.isPresent()) {
            CounterEntity counterEntity = entityOpt.get();
            counterEntity.setCounter(counterValue);
            counterEntity.setLastUpdated(LocalDateTime.now());
            repository.save(counterEntity);
        }
    }

    private int getCurrentCounterValueFromDB() {
        var counterValue = repository.findById(SINGLE_ENTITY_ID);
        return counterValue.map(CounterEntity::getCounter).orElse(0);
    }
}
