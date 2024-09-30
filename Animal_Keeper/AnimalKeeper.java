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

    MyZoo zoo = new MyZoo();

    int command;

    int animalType;
    String animalName;
    int homeNumber;
    int foodType;
    int foodAmount;

    void run() {
        while (true) {
            command = sc.nextInt();
            switch (command) {
                case 0:
                    animalType = sc.nextInt();
                    animalName = sc.next();
                    homeNumber = sc.nextInt();
                    zoo.addAnimal(animalType, animalName, homeNumber);
                    break;
                case 1:
                    animalName = sc.next();
                    homeNumber = sc.nextInt();
                    zoo.moveAnimal(animalName, homeNumber);
                    break;
                case 2:
                    animalName = sc.next();
                    zoo.removeAnimal(animalName);
                    break;
                case 3:
                    foodType = sc.nextInt();
                    foodAmount = sc.nextInt();
                    zoo.buyFood(foodType, foodAmount);
                    break;
                case 4:
                    foodType = sc.nextInt();
                    foodAmount = sc.nextInt();
                    homeNumber = sc.nextInt();
                    zoo.feedAnimal(foodType, foodAmount, homeNumber);
                    break;
                default:
                    System.out.println();
                    return;
            }
        }
    }

    public static void main(String[] args) {
        new AnimalKeeper().run();
    }
}
