package com.example.demo.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChoiceTest {

    Choice choice;

    @Before
    public void setUp() {
        choice = new Choice();
    }

    @Test
    public void getId() {
        Long id = 4L;
        choice.setId(id);
        assertEquals(id, choice.getId());
    }

    @Test
    public void getText() {
    }

    @Test
    public void getPoll() {
    }
}