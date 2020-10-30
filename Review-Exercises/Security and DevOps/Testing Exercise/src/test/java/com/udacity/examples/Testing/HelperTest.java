package com.udacity.examples.Testing;

import org.junit.*;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HelperTest {

    @Before
    public void init() {
        System.out.println("init executed");
    }

    @After
    public void teardown() {
        System.out.println("teardown executed");
    }

    /**
     * these should be public static
     */

    @BeforeClass
    public static void initClass() {
        System.out.println("** init Class executed **");
    }

    @AfterClass
    public static void teardownclass() {
        System.out.println("** teardown Class executed **");
    }

    // Note: Calling the method test_something may be redundant since we already have a @Test annotation that tells we are referring to a test method
    @Test
    public void verify_getCount() {
        List<String> empNames = Arrays.asList("Joan", "Perez");
        final long actual = Helper.getCount(empNames);
        assertEquals(2, actual);

        List<String> empNames2 = Arrays.asList("sareeta", "", "john","");
        final long actual2 = Helper.getCount(empNames2);
        assertEquals(2, actual2);
    }

    @Test
    public void verify_getStats() {
        List<Integer> yrsOfExperience = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        List<Integer> expectedList = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        IntSummaryStatistics stats = Helper.getStats(yrsOfExperience);
        assertEquals(19, stats.getMax());

        assertEquals(expectedList, yrsOfExperience);
    }

    @Test
    public void compare_arrays() {
        int[] yrs = {10,14,2};
        int[] expectedYrs = {10,14,2};
        assertArrayEquals(expectedYrs, yrs);
    }

    @Test
    public void validate_3lengthString() {
        List<String> empNames = Arrays.asList("sareeta", "", "Jeff","sam");
        long nOfStrings = Helper.getStringsOfLength3(empNames);
        assertEquals(1, nOfStrings);
    }

    @Test
    public void verify_list_is_squared(){
        List<Integer> yrsOfExperience = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        List<Integer> expected = Arrays.asList(169, 16, 225, 36, 289, 64, 361, 1, 4, 9);
        assertEquals(expected, Helper.getSquareList(yrsOfExperience));
    }

    @Test
    public void verify_getMax(){
        List<Integer> empLevel = Arrays.asList(3,3,3,5,7,2,2,5,7,5);
        assertEquals(7, Helper.getStats(empLevel).getMax());
    }

    // Example using @Ignore. This is simply used to skip a test.
    @Ignore
    @Test
    public void verify_mergeList() {
        List<String> names = Arrays.asList("Joan", "Harpreet", "", "Emilio");
        String mergedNames = Helper.getMergedList(names);

        assertEquals("Joan, Harpreet, Emilio", mergedNames);
    }
}
