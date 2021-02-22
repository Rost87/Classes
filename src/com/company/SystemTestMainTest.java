package com.company;

import org.junit.Test;

import static com.company.SystemTestMain.estimate;
import static org.junit.Assert.*;

public class SystemTestMainTest {

    @Test
    public void estimateTest(){
        double percent = 33.0;
        int expectedEstimate = 2;

        assertEquals(expectedEstimate, estimate(percent));


    }

}