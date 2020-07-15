package com.geekbrains.septembermarket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpyTest {
    @Spy
    private List<Integer> spiedList = new ArrayList<>();

    @Test
    public void spyTest() {
        spiedList.add(1);
        spiedList.add(2);
        spiedList.add(3);

        Mockito.verify(spiedList).add(1);
        Mockito.verify(spiedList).add(2);
        Mockito.verify(spiedList).add(3);

        assertEquals(3, spiedList.size());

        Mockito.doReturn(100).when(spiedList).size();

        assertEquals(100, spiedList.size());
    }

    @Test(expected = ArithmeticException.class)
    public void mockThrow() {
        List<String> listMock = Mockito.mock(List.class);
        when(listMock.add(anyString())).thenThrow(ArithmeticException.class);
        listMock.add("Hello");
    }
}
