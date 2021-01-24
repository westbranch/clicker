package com.nick.clicker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name = "clicker")
public class CounterEntity {

    public CounterEntity() {

    }

    public CounterEntity(Long id, Integer value) {
        this.id = id;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "counter")
    private Integer value;

    @Column(name = "value_type")
    private String valueType = "counter";

    @Column(name = "last_updated")
    private OffsetDateTime lastUpdated;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
