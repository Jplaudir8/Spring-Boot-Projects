package com.udacity.examples.Testing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AbacusTest {
    @Test
    public void evaluatesExpression() {
        Abacus abacus = new Abacus();
        int sum = abacus.evaluate("10+20+30");
        assertEquals(60, sum);
    }
}
