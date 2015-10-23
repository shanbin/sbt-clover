package com.example.calc;

import static org.junit.Assert.assertEquals;

import com.example.calc.MyCalc;
import org.junit.Test;

public class MyCalcTest {

    @Test
    public void testAdd() {    
        MyCalc calc = new MyCalc();
        assertEquals(4, calc.add(1, 3));
    }
}

