import java.util.*;

/**
 * Purpose:
 * The program helps to keep track of the animal situation in the Zoo.
 * It scans user input and performs required actions from the MyZoo class in the file MyZoo.java.
 *
 * Usage:
 * There are 5 different commands that the user can input.
 * 1. 0 t "name" h: adds an animal of type t with a name "name" to the home with number h.
 * 2. 1 "name" h: moves the animal with a name "name" to a home with number h.
 * 3. 2 "name": remove the animal with name "name" from the Zoo.
 * 4. 3 f x: buys food of type f the amount x.
 * 5. 4 f x h: feeds food of type f the amount x to home h.
 *
 * There are several restrictions listed below which should not be violated.
 * There are 10 cages number 0 to 9 and 5 enclosures numbered 10 to 14.
 * Each animal should have a unique name.
 * There is a single storage for each type of food which has a maximum capacity of 100.
 * The food has to be bought before it can be fed to the animals.
 * Herbivores cannot be kept with carnivores and omnivores.
 * Carnivores and omnivores do not like to live with other carnivore and omnivore species.
 * Tigers, Leopards and Bears are solitary creatures that do not want to live in a group.
 * A cage can fit up to two animals. An open enclosure up to six.
 *
 * @author Ivan Sergeevich Mishin
 * @ID 2076209
 * @author Nikita Vladimirovich Gamolin
 * @ID 2091402
 *
 */
public class AnimalKeeper {

    Scanner sc = new Scanner(System.in);

    MyZoo zoo = new MyZoo(); // object of MyZoo class from the second file MyZoo.java

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
