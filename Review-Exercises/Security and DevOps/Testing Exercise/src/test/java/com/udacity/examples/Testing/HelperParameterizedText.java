package com.udacity.examples.Testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotEquals;

// Example using a parameterized tests.
@RunWith(Parameterized.class) // We have to add this line to make this class a parameterized class.
public class HelperParameterizedText {

    private String input;
    private String output;

    public HelperParameterizedText(String input, String output) {
        super();
        this.input = input;
        this.output = output;
    }

    @Parameters
    public static Collection initData() {
        String empName[][] = {{"Joan","Joan"}, {"Joan","Harpreet"}};
        return Arrays.asList(empName);
    }

    @Test
    public void verify_inputNameIsNotSameAsOutput() {
        assertNotEquals(input, output);
    }

    // Note: Parameterized tests allow you to run the same test with different parameters.

}
