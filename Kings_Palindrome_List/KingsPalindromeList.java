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
            numbers[i] = sc.next(); //
        }

        String answer = switch (taskNumber) {
            case 1 -> taskOne();
            case 2 -> taskTwo();
            case 3 -> taskThree();
            default -> "Invalid task number";
        };
        System.out.println(answer);
    }

    public String taskOne() {
        StringBuilder correctList = new StringBuilder();
        for (String original : numbers) {
            String reversed = new StringBuilder(original).reverse().toString();
            if (!reversed.equals(original)) {
                int number = Integer.parseInt(original);
                while (!reversed.equals(original)) {
                    number += 1;
                    original = Integer.toString(number);
                    reversed = new StringBuilder(original).reverse().toString();
                }
            }
            correctList.append(original).append(" ");

        }
        return correctList.toString().trim();
    }

    public String taskTwo() {
        numbers = taskOne().split(" ");
        int maxMagicNumber = 1;
        for (String number : numbers) {
            int currentMagicNumber = 1;
            if (number.length() > maxMagicNumber * 2) {
                for (int i = 0; i < (number.length() - 1) / 2; i++) {
                    for (String comparing : numbers) {
                        if (comparing.equals(number.substring(i+1, number.length()-(i+1)))) {
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

    public String taskThree() {
        numbers = taskOne().split(" ");
        ArrayList<String> longestMagicSet = new ArrayList<>();
        for (String number : numbers) {
            ArrayList<String> currentMagicSet = new ArrayList<>();
            currentMagicSet.add(number);
            if (number.length() > longestMagicSet.size() * 2) {
                for (int i = 0; i < (number.length() - 1) / 2; i++) {
                    for (String comparing : numbers) {
                        if (comparing.equals(number.substring(i+1, number.length()-(i+1)))) {
                            currentMagicSet.add(comparing);
                            break;
                        }
                    }
                }
                if (currentMagicSet.size() > longestMagicSet.size()) {
                    longestMagicSet = currentMagicSet;
                } else if (currentMagicSet.size() == longestMagicSet.size() &&
                        Integer.parseInt(currentMagicSet.get(0)) > Integer.parseInt(longestMagicSet.get(0))) {
                    longestMagicSet = currentMagicSet;
                }
            }
        }
        Collections.reverse(longestMagicSet);
        StringBuilder correctList = new StringBuilder();
        for (String number : longestMagicSet) {
            correctList.append(number).append(" ");
        }
        return correctList.toString().trim();
    }

    public static void main(String[] args) {
        new KingsPalindromeList().run();
    }
}