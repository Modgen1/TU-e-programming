import java.util.*;

/**
 *
 * Purpose:
 * Find an order in which the fuel cans should be used such that the truck exactly reaches
 * the finish line and does not stop in the places where it is not allowed to stop for a refuel.
 *
 * Usage:
 * First line of input reads the number of fuel cans in the truck
 * Second line of input reads the mileage of each individual can
 * The third line of input read the locations of the places where the truck cannot stop, given
 * relative to the starting location at 0 km.
 *
 * @author Ivan Sergeevich Mishin
 * @ID 2076209
 * @author Nikita Vladimirovich Gamolin
 * @ID 2091402
 *
 */
public class MadTrucker {
    /**
     * Reads user's input, creates arrays for later use in the solve method,
     * calls a solve method and outputs the answer.
     */
    void run() {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int distance = 0;

        ArrayList<Integer> mileages = new ArrayList<>();
        ArrayList<Integer> locations = new ArrayList<>();

        // filling mileages ArrayList with input values and adding total distances
        for (int i = 0; i < n; i++) {
            int fuel = sc.nextInt();
            mileages.add(fuel);
            distance += fuel;
        }

        // filling locations ArrayList with input values
        for (int i = 0; i < (n - 1); i++) {
            locations.add(sc.nextInt());
        }

        // creating copy of mileages ArrayList and sorting it to use in solve method
        ArrayList<Integer> sortedMileages = new ArrayList<>(mileages);
        sortedMileages.sort(Collections.reverseOrder());

        // calling solve method that will give answer using recursion
        ArrayList<Integer> answer = solve(
                n, distance, mileages, sortedMileages, locations, new ArrayList<>());

        // creating the answer string from elements of arraylist using StringBuilder
        StringBuilder answerString = new StringBuilder();
        for (int number : answer) {
            answerString.append(number).append(" ");
        }

        System.out.println(answerString.toString().trim());
    }

    /**
     * Method for solvig the problem using recursion.
     *
     * @param n - amount of full fuel tanks left, also is the depth of recursion
     * @param distance - distance from the start point (0) to the current position of algorithm
     * @param mileages - ArrayList of all fuel tanks with their value
     * @param sortedMileages - sorted mileages ArrayList to make this sort only once
     * @param locations - ArrayList of all locations where truck is not allowed to stop
     * @param answer - ArrayList that is initially empty but will be filled by recursive calls
     * @return ArrayList with the answer containing indexes of all fuel tanks in correct order
     */
    ArrayList<Integer> solve(int n, int distance, ArrayList<Integer> mileages, 
                             ArrayList<Integer> sortedMileages, ArrayList<Integer> locations, 
                             ArrayList<Integer> answer) {

        // firstly, check for value of n to finish the recursion when algorithm finishes
        if (n == 0) {
            Collections.reverse(answer);
            return answer;
        }

        // looping through all elements of sorted ArrayList to check which one to add to answer
        for (int i: sortedMileages) {
            // boolean value that shows whether location where we want to stop is not possible
            boolean inLocation = false;
            // iterating through all locations where we can't stop to check each of them
            for (int j: locations) {
                // if we want to stop at location where we aren't allowed to, break from the loop
                if ((distance - i) == j) {
                    inLocation = true;
                    break;
                }

            }
            // if location we chose is not in the list of prohibited locations, we move to this
            // location by adding it to answer ArrayList and changing our current position
            if (!inLocation) {
                answer.add(mileages.indexOf(i));
                sortedMileages.remove(Integer.valueOf(i));
                distance -= i;
                break;
            }
        }
        // if recursion haven't stopped by having n == 0, then we continue recursion with n = n - 1
        return solve(n - 1, distance, mileages, sortedMileages, locations, answer);
    }

    public static void main(String[] args) {
        new MadTrucker().run();
    }
}
