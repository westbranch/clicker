package com.nick.clicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClickerService {

    public static final long COUNTER_ID = 1L;
    private AtomicInteger clickCount = new AtomicInteger();
    @Autowired
    private ClickerRepository repository;

    public int incrementAndGet() {
        int result = clickCount.incrementAndGet();
        repository.save(new CounterEntity(COUNTER_ID, result));
        return result;
    }

    public int getClickCount() {
        return clickCount.get();
    }

    @PostConstruct
    private void setInitialValue() {
        clickCount.set(this.getLatestValue());
    }

    private int getLatestValue() {
        return repository.findById(1L).map(CounterEntity::getValue).orElse(0);
    }
}
