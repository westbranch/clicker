package com.nick.clicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClickerService {

    private static final String COUNTER_VALUE_TYPE = "counter";
    private static final long SINGLE_ENTITY_ID = 1L;
    private AtomicInteger clickCount = new AtomicInteger();

    @Autowired
    private ClickerRepository repository;

    public int incrementCounterAndGet() {
        int result = clickCount.incrementAndGet();
        saveToDB(result);
        return result;
    }

    public int getCurrentClickCount() {
        return clickCount.get();
    }

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
