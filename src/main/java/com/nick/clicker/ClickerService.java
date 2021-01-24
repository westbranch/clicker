package com.nick.clicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClickerService {

    private static final String COUNTER_VALUE_TYPE = "counter";
    private final CounterEntity entity = new CounterEntity(1L, 0);
    private AtomicInteger clickCount = new AtomicInteger();

    @Autowired
    private ClickerRepository repository;

    @PostConstruct
    private void setInitialValue() {
        clickCount.set(getLatestValue());
    }

    public int incrementAndGet() {
        int result = clickCount.incrementAndGet();
        saveToDB(result);
        return result;
    }

    public int getClickCount() {
        return clickCount.get();
    }

    private void saveToDB(int result) {
        entity.setValue(result);
        entity.setLastUpdated(getCurrentTime());
        repository.save(entity);
    }

    private OffsetDateTime getCurrentTime() {
        return OffsetDateTime.now();
    }

    private int getLatestValue() {
        Optional<CounterEntity> counterValue = repository.findDistinctByValueType(COUNTER_VALUE_TYPE);
        return counterValue.map(CounterEntity::getValue).orElse(0);
    }
}
