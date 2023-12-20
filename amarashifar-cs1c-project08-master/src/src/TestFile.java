import java.time.Instant;

/**
 * This class examines the testing times of different array sizes
 * The array sizes vary from 20k to 10 million
 */
public class TestFile {
    //Main. Three different test cases.
    public static void main(String[] args) {
        final int [] TEST1 = {3000000, 4000000, 6000000};
        final int [] TEST2 = {20000, 600000, 80000, 100000, 150000, 200000};
        final int [] TEST3 = {800000, 900000, 1000000, 2000000,
                3000000, 4000000, 6000000};

        final int [] FINAL_ARRAY = {20000, 60000, 80000, 100000, 150000, 200000, 300000, 400000,
                500000, 550000, 600000, 700000, 800000, 900000, 1000000, 2000000,
                3000000, 4000000, 6000000, 10000000};

        for (int i = 0; i < TEST2.length; i++) {
            runRecursionLimits(TEST2[i]);
        }
    }

    /**
     * This method uses recursion limits to test the average time of the arrays
     * @param arraySize size of our desired array
     */
    public static void runRecursionLimits(int arraySize) {
        long endTime, startTime;
        long avgTime;
        long [] myTime = new long[3];
        System.out.println("Test size of: " + arraySize);

        Integer [][] tempArrays = new Integer[arraySize][3];
        Integer [][] newArrays = new Integer[arraySize][3];

        for (int i = 0; i < 3; i++) {
            tempArrays[i] = createRandomArray(arraySize);
        }

        for (int k = 2; k <= 300; k += 2) {
            FHsort.setRecursionLimit(k);

            for (int j = 0; j < 3; j++) {
                newArrays[j] = tempArrays[j].clone();
            }

            for (int n = 0; n < 3; n++) {
                startTime = Instant.now().toEpochMilli();
                FHsort.quickSort(newArrays[n]);
                endTime = Instant.now().toEpochMilli();
                myTime[n] = endTime - startTime;
            }
            avgTime = findAverage(myTime, 3);
            System.out.println(k + ", " + avgTime );

        }

    }

    /**
     * This method finds the average
     * @param time our desired array
     * @param y size of array
     * @return the average of the array
     */
    private static long findAverage(long[] time, int y) {
        int initialTotal = 0;
        for (int i = 0; i < y; i++)
            initialTotal += time[i];
        return initialTotal/y;
    }

    /**
     * This method creates random ints to fill the array
     * @param arraySize, our desired size
     * @return array with random numbers
     */
    public static Integer[] createRandomArray(int arraySize) {
        Integer[] myBeautifulArray = new Integer[arraySize];
        for (int i = 0; i < myBeautifulArray.length; i++) {
            myBeautifulArray[i] = (int)(arraySize * Math.random());
        }
        return myBeautifulArray;
    }

}
