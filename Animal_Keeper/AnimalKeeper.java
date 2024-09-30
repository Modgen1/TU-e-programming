import java.util.*;

/**
 * Purpose:
 *
 * Usage:
 *
 * @author Ivan Sergeevich Mishin
 * @ID 2076209
 * @author Nikita Vladimirovich Gamolin
 * @ID 2091402
 *
 */
public class AnimalKeeper {

    Scanner sc = new Scanner(System.in);

    MyZoo zoo = new MyZoo(); // object of MyZoo class from second file MyZoo.java

    int command; // command number that is inputted every iteration

    // different types of arguments that can be used for certain commands
    int animalType;
    String animalName;
    int homeNumber;
    int foodType;
    int foodAmount;

    /**
     * Main method that runs in the loop until faces invalid command number, then stops.
     * For each command it gets required inputs and passes them to relative methods in MyZoo class.
     */
    void run() {
        while (true) {
            command = sc.nextInt();
            switch (command) {
                case 0: // command to add new animal
                    animalType = sc.nextInt();
                    animalName = sc.next();
                    homeNumber = sc.nextInt();
                    zoo.addAnimal(animalType, animalName, homeNumber);
                    break;
                case 1: // command to move an existing animal to other home
                    animalName = sc.next();
                    homeNumber = sc.nextInt();
                    zoo.moveAnimal(animalName, homeNumber);
                    break;
                case 2: // command to remove an existing animal
                    animalName = sc.next();
                    zoo.removeAnimal(animalName);
                    break;
                case 3: // command to buy certain amount of certain type of food
                    foodType = sc.nextInt();
                    foodAmount = sc.nextInt();
                    zoo.buyFood(foodType, foodAmount);
                    break;
                case 4: // command to spend certain amount of certain type of food to feed animals.
                    foodType = sc.nextInt();
                    foodAmount = sc.nextInt();
                    homeNumber = sc.nextInt();
                    zoo.feedAnimal(foodType, foodAmount, homeNumber);
                    break;
                default: // if command number is invalid, program stops without throwing exception
                    System.out.println();
                    // it is required by the task that program must end with the new line
                    return;
            }
        }
    }

    public static void main(String[] args) {
        new AnimalKeeper().run();
    }
}
