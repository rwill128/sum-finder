package SumFinder;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SumFinderTest {

    // Some edge cases are tested for, but in general the assumption is that the
    // stated constraints in the problem statement are always met. Additional code for
    // catching and preventing errors in edge cases can be added for production code.

    @Test
    public void shouldThrowExceptionIfArrayShorterThanTwoOrNull() throws SumFinderException {

        assertThrowsExactly(SumFinderException.class,
                () -> SumFinder.findIndexesToSumToNumber(new int[]{2}, 2));

        assertThrowsExactly(SumFinderException.class,
                () -> SumFinder.findIndexesToSumToNumber(null, 2));

    }

    @Test
    public void shouldThrowExceptionIfNumberNotPresent() throws SumFinderException {
        int[] input = new int[]{2,7,11,15};


        assertThrowsExactly(SumFinderException.class, () -> SumFinder.findIndexesToSumToNumber(input, 16));
    }

    @Test
    public void shouldSolveTrivialCase() throws SumFinderException {
        int[] input = new int[]{2,7,11,15};
        int[] answer = SumFinder.findIndexesToSumToNumber(input, 9);

        assertArrayEquals(new int[]{1,2}, answer);
    }

    @Test
    public void shouldSolveSlightlyHarderCase() throws SumFinderException {
        int[] input = new int[]{1,2,7,11,15};
        int[] answer = SumFinder.findIndexesToSumToNumber(input, 9);

        assertArrayEquals(new int[]{2,3}, answer);
    }

    // This test accurately reflects the exact constraints of the document,
    // and will be repeated 1000 times to demonstrate consistency.
    @RepeatedTest(1000)
    public void shouldEfficientlySolveRandomCaseWithLargeArray() throws SumFinderException {
        int[] input = new Random()
                .ints(new Random().nextInt(29999) + 1, // The document says max bound of 30,000, but since we have to have distinct values and we're capped from
                        // {-1000, 1000}
                        // the actual max length will be 2001
                        -1000,
                        1000)
                .distinct() // Call distinct because the problem description says there is always only one correct answer
                .sorted(). // Sort in "non-decreasing" order
                toArray();

        // Pick a target number that we know exists in the array
        int targetNumber = input[new Random().nextInt(input.length - 1)] + input[new Random().nextInt(input.length - 1)];

        int[] answer = SumFinder.findIndexesToSumToNumber(input, targetNumber);

        // Confirm our indexes give us a matching answer
        assertEquals(input[answer[0]-1] + input[answer[1] - 1], targetNumber);
    }

    // This test exceeds the demands on the problem statement, with much larger arrays and bounds.
    // It will also be repeated 1000 times to demonstrate efficiency.
    @RepeatedTest(1000)
    public void shouldEfficientlySolveRandomCaseWithVeryLargeArray() throws SumFinderException {
        int[] input = new Random()
                .ints(new Random().nextInt(999999) + 1,
                        Integer.MIN_VALUE / 2,
                        Integer.MAX_VALUE / 2) // Divide by 2 to avoid potential integer overflows in the sum target number
                .distinct() // Call distinct because the problem description says there is always only one correct answer
                .sorted(). // Sort in "non-decreasing" order
                        toArray();

        // Pick a target number that we know exists in the array
        int targetNumber = input[new Random().nextInt(input.length - 1)] + input[new Random().nextInt(input.length - 1)];


        int[] answer = SumFinder.findIndexesToSumToNumber(input, targetNumber);

        // Confirm our indexes give us a matching answer
        assertEquals(input[answer[0]-1] + input[answer[1] - 1], targetNumber);
    }

}