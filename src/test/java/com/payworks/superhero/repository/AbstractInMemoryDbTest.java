package com.payworks.superhero.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public abstract class AbstractInMemoryDbTest {
    @Before
    public void setUp() throws Exception {
        // Anything needed for all tests in the db should be added here
    }
}
