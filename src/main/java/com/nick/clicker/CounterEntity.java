package com.nick.clicker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "clicker")
public class CounterEntity {

    public CounterEntity() {

    }

    public CounterEntity(Long id, Integer counter, LocalDateTime lastUpdated) {
        this.id = id;
        this.counter = counter;
        this.lastUpdated = lastUpdated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer counter;
    private LocalDateTime lastUpdated;

    public Integer getCounter() {
        return counter;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
