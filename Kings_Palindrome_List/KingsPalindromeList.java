import java.util.*;

/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * First line of input is the task that should be performed.
 * Second line of input is the Natural number which indicates the amount of numbers in the list.
 * Third line of input consists of numbers separated by a space which make up the King's palindrome list.
 * 
 * @author Ivan Sergeevich Mishin
 * @ID 2076209
 * @author Nikita Vladimirovich Gamolin
 * @ID 2091402
 *
 */
class KingsPalindromeList {
    Scanner sc = new Scanner(System.in);

    int taskNumber; //number of the task
    int amountNumbers; //amount of numbers following in the input
    String[] numbers; //array containing the natural numbers from the advisor's list

    public void run() {
        taskNumber = sc.nextInt();
        amountNumbers = sc.nextInt();
        numbers = new String[amountNumbers];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = sc.next(); //scanning for all input numbers and filling numbers list
        }

        String answer = switch (taskNumber) { //calling methods accordingly to the input task number and storing answer
            case 1 -> taskOne();
            case 2 -> taskTwo();
            case 3 -> taskThree();
            default -> "Invalid task number";
        };
        System.out.println(answer);
    }

    /**
     * Method for finding the smallest palindromes greater than or equal to the numbers from the King's list.
     * @return result string containing the correct list of palindromes.
     */
    String taskOne() {
        StringBuilder correctList = new StringBuilder(); //creating modifiable array that will collect the correct numbers
        for (String original : numbers) { //for-loop going through each number from the King's list
            String reversed = new StringBuilder(original).reverse().toString(); //reversing the number
            if (!reversed.equals(original)) {
                int number = Integer.parseInt(original); //convert the number from the list to int data type
                while (!reversed.equals(original)) { //while loop until the number becomes a palindrome
                    number += 1;
                    original = Integer.toString(number); //convert the number from int to str
                    reversed = new StringBuilder(original).reverse().toString();
                }
            }
            correctList.append(original).append(" "); //add the number to the array with the correct numbers

        }
        return correctList.toString().trim();
    }

    /**
     * Method for finding the longest magic set in the list by iterating through all given numbers.
     * @return result string containing the length of longest magic set.
     */
    String taskTwo() {
        numbers = taskOne().split(" "); //calling first method to convert all numbers to palindromes
        int maxMagicNumber = 1; //integer value for storing the maximum length of magic set
        for (String number : numbers) {
            int currentMagicNumber = 1;
            if (number.length() > maxMagicNumber * 2) { //checks whether number is longer than already found magic set
                for (int i = 0; i < (number.length() - 1) / 2; i++) {
                    for (String comparing : numbers) {
                        if (comparing.equals(number.substring(i + 1, number.length() - (i + 1)))) {
                            currentMagicNumber += 1;
                            break;
                        }
                    }
                }
                if (currentMagicNumber > maxMagicNumber) {
                    maxMagicNumber = currentMagicNumber;
                }
            }
        }
        return String.valueOf(maxMagicNumber);
    }

    /**
     * Method for finding all elements of the longest magic set. Uses the same logic as task two
     * but works with arraylists instead of incrementing integer.
     * @return string containing all elements of the longest magic set in ascending order.
     */
    String taskThree() {
        numbers = taskOne().split(" "); //calling first method to convert all numbers to palindromes
        ArrayList<String> longestMagicSet = new ArrayList<>(); //arraylist containing the longest magic set
        for (String number : numbers) {
            ArrayList<String> currentMagicSet = new ArrayList<>();
            currentMagicSet.add(number);
            if (number.length() > longestMagicSet.size() * 2) {
                for (int i = 0; i < (number.length() - 1) / 2; i++) {
                    for (String comparing : numbers) {
                        if (comparing.equals(number.substring(i + 1, number.length() - (i + 1)))) {
                            currentMagicSet.add(comparing);
                            break;
                        }
                    }
                }
                // the third task requires additional check for the case when there are multiple longest magic sets
                if (currentMagicSet.size() > longestMagicSet.size()) {
                    longestMagicSet = currentMagicSet;
                } else if (currentMagicSet.size() == longestMagicSet.size()
                        && Integer.parseInt(currentMagicSet.get(0)) > Integer.parseInt(longestMagicSet.get(0))) {
                    longestMagicSet = currentMagicSet;
                }
            }
        }
        Collections.reverse(longestMagicSet); // reversing the arraylist to make elements in ascending order
        StringBuilder correctList = new StringBuilder();
        for (String number : longestMagicSet) { // this cycle is to build the answer string from elements of arraylist
            correctList.append(number).append(" ");
        }
        return correctList.toString().trim();
    }

    public static void main(String[] args) {
        new KingsPalindromeList().run();
    }
}