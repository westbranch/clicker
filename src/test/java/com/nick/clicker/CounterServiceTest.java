package com.nick.clicker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = CounterService.class)
public class CounterServiceTest {

    @Autowired
    private CounterService counterService;

    @MockBean
    private CounterRepository counterRepository;

    @Mock
    CounterEntity entityMock;

    @Before
    public void setUp() throws Exception {
        when(counterRepository.findById(anyLong())).thenReturn(Optional.of(entityMock));
        when(counterRepository.save(any(CounterEntity.class))).thenReturn(entityMock);
        when(entityMock.getCounter()).thenReturn(0);
    }

    @Test
    public void shouldReturn5After5Increments() {
        int expectedClickCount = 5;
        int valueBeforeExecution = counterService.getCurrentClickCount();
        Assert.assertEquals(0, valueBeforeExecution);

        for (int i = 0; i < expectedClickCount; i++) {
            counterService.incrementCounterAndGet();
        }
        int valueAfterExecution = counterService.getCurrentClickCount();
        Assert.assertEquals(expectedClickCount, valueAfterExecution);
    }

    @Test
    public void shouldCallRepositorySaveAndFindWhenIncremented() {
        counterService.incrementCounterAndGet();
        verify(counterRepository).save(any(CounterEntity.class));
        verify(counterRepository, atLeast(1)).findById(anyLong());
    }

    @Test
    public void shouldReturnCurrentCounterValue() {
        int expectedClickCount = 5;
        when(entityMock.getCounter()).thenReturn(expectedClickCount);
        counterService.setCurrentCounterValue();

        int actualClickCount = counterService.getCurrentClickCount();
        Assert.assertEquals(expectedClickCount, actualClickCount);

        int actualClickCountAfterIncrement = counterService.incrementCounterAndGet();
        Assert.assertEquals(6, actualClickCountAfterIncrement);
    }


}

