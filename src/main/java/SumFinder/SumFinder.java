package SumFinder;

public class SumFinder {

    public static int[] findIndexesToSumToNumber(int[] searchArray, int targetNumber) throws SumFinderException {

        if (searchArray == null || searchArray.length < 2) {
            throw new SumFinderException("Search Array is null or too short for a search");
        }


        // Since we're returning indexes that start from 1 instead of 0, initialize this way,
        // and we will just subtract one every time we actually index the array
        int lowIndex = 1;
        int highIndex = searchArray.length;

        while (searchArray[lowIndex - 1] + searchArray[highIndex - 1] != targetNumber) {

            // Our search has met in the middle without a match, break
            if (lowIndex >= highIndex) {
                break;
            }

            int currentSum = searchArray[lowIndex - 1] + searchArray[highIndex - 1];

            // We're still too high, keep lowering the highIndex
            if (currentSum > targetNumber) {
                highIndex--;
            }

            // We dropped below our target, bump up the low index
            // and move the high index back to a point where the sum is above the target
            if (currentSum < targetNumber) {
                lowIndex++;
                while (searchArray[lowIndex - 1] + searchArray[highIndex - 1] < targetNumber
                        && highIndex < searchArray.length) {
                    highIndex++;
                }
            }

        }

        if (searchArray[lowIndex - 1] + searchArray[highIndex - 1] == targetNumber) {
            return new int[]{lowIndex, highIndex};
        } else {
            throw new SumFinderException("Target number not present in array.");
        }
    }

}