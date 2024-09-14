import java.util.Arrays;
import java.util.Scanner;

/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 *
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
            numbers[i] = sc.next(); //
        }

        String answer = "";
        switch (taskNumber) {
            case 1:
                answer = taskOne();
            case 2:
                answer = taskTwo();
            case 3:
                answer = taskThree();
        }
        System.out.println(answer);
    }

    public String taskOne() {
        String correctList = "";
        for (String original : numbers) {
            String reversed = new StringBuilder(original).reverse().toString();
            if (reversed.equals(original)) {
                correctList = correctList + original + " ";
            } else {
                int number = Integer.valueOf(original);
                while (reversed.equals(original) == false) {
                    number += 1;
                    original = Integer.toString(number);
                    reversed = new StringBuilder(original).reverse().toString();
                }
                correctList = correctList + original + " ";
            }

        }
        System.out.println(correctList);
        return correctList.trim();
    }

    public String taskTwo() {
        int maxMagicNumber = 1;
        for (String number : numbers) {
            int currentMagicNumber = 1;
            if (number.length() > maxMagicNumber * 2) {
                for (int i = 0; i < (number.length() - 1) / 2; i++) {
                    for (String comparing : numbers) {
                        if (comparing.equals(number.substring(i+1, number.length()-(i+1)))) {
                            currentMagicNumber += 1;
                            System.out.println(currentMagicNumber + " " + comparing + " " + number.substring(i, number.length()-i));
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

    public String taskThree() {
        return "";
    }

    public static void main(String[] args) {
        new KingsPalindromeList().run();
    }
}