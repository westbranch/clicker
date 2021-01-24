package com.nick.clicker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "clicker")
public class CounterEntity {

    public CounterEntity() {

    }

    public CounterEntity(Long id, Integer counter, String valueType, OffsetDateTime lastUpdated) {
        this.id = id;
        this.counter = counter;
        this.valueType = valueType;
        this.lastUpdated = lastUpdated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer counter;
    private String valueType;
    private OffsetDateTime lastUpdated;

    public Integer getCounter() {
        return counter;
    }

}
