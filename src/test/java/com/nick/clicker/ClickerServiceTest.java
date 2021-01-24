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
@ContextConfiguration(classes = ClickerService.class)
public class ClickerServiceTest {

    @Autowired
    private ClickerService clickerService;

    @MockBean
    private ClickerRepository clickerRepository;

    @Mock
    CounterEntity entityMock;

    @Before
    public void setUp() throws Exception {
        when(clickerRepository.findDistinctByValueType(anyString())).thenReturn(Optional.of(entityMock));
        when(clickerRepository.save(any(CounterEntity.class))).thenReturn(entityMock);
        when(entityMock.getCounter()).thenReturn(0);
    }

    @Test
    public void shouldReturn5After5Increments() {
        int expectedClickCount = 5;
        int valueBeforeExecution = clickerService.getCurrentClickCount();
        Assert.assertEquals(0, valueBeforeExecution);

        for (int i = 0; i < expectedClickCount; i++) {
            clickerService.incrementCounterAndGet();
        }
        int valueAfterExecution = clickerService.getCurrentClickCount();
        Assert.assertEquals(expectedClickCount, valueAfterExecution);
    }

    @Test
    public void shouldCallRepositorySaveAndFindWhenIncremented() {
        clickerService.incrementCounterAndGet();
        verify(clickerRepository).save(any(CounterEntity.class));
        verify(clickerRepository).findDistinctByValueType(anyString());
    }

    @Test
    public void shouldReturnCurrentCounterValue() {
        int expectedClickCount = 5;
        when(entityMock.getCounter()).thenReturn(expectedClickCount);
        clickerService.setCurrentCounterValue();

        int actualClickCount = clickerService.getCurrentClickCount();
        Assert.assertEquals(expectedClickCount, actualClickCount);

        int actualClickCountAfterIncrement = clickerService.incrementCounterAndGet();
        Assert.assertEquals(6, actualClickCountAfterIncrement);
    }


}

