package com.project.csci3130.dalrs;

import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class RegistActivityTest{
    @BeforeClass
    public static void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void conflictTest() throws ParseException {
        String leftStart="10:35";
        String leftEnd="14:55";
        String rightStart="10:55";
        String rightEnd="11:25";
        //RegistActivity test=new RegistActivity();
        assertTrue(RegistActivity.testConflict(leftStart, leftEnd, rightStart, rightEnd));
    }
}
