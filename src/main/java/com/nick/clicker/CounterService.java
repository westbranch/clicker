package com.nick.clicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Performs operations with clicker counter, such as increment and get current value
 */
@Service
public class CounterService {

    private static final String COUNTER_VALUE_TYPE = "counter";
    private static final long SINGLE_ENTITY_ID = 1L;
    private AtomicInteger clickCount = new AtomicInteger();

    @Autowired
    private CounterRepository repository;

    /**
     * Increments counter and returns post-increment value.
     * @return post-increment counter value.
     */
    public int incrementCounterAndGet() {
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
        repository.save(new CounterEntity(SINGLE_ENTITY_ID, counterValue, COUNTER_VALUE_TYPE, OffsetDateTime.now()));
    }

    private int getCurrentCounterValueFromDB() {
        var counterValue = repository.findDistinctByValueType(COUNTER_VALUE_TYPE);
        return counterValue.map(CounterEntity::getCounter).orElse(0);
    }
}
