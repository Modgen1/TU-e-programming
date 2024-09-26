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
public class MyZoo {
    Map<String, String> animals = new HashMap<String, String>();
    Food food = new Food();

    /**
     *
     * @param type
     * @param name
     * @param home
     */
    void addAnimal(String type, String name, int home) {

    }

    /**
     *
     * @param name
     * @param home
     */
    void moveAnimal(String name, int home) {

    }

    /**
     *
     * @param name
     */
    void removeAnimal(String name) {

    }

    /**
     *
     * @param type
     * @param amount
     */
    void buyFood(String type, int amount) {
        switch (type) {
            case "hay":
                food.hay = Math.min(amount + food.hay, 100);
                break;
            case "corn":
                food.corn = Math.min(amount + food.corn, 100);
                break;
            case "grain":
                food.grain = Math.min(amount + food.grain, 100);
                break;
            case "carrots":
                food.carrots = Math.min(amount + food.carrots, 100);
                break;
            case "chicken":
                food.chicken = Math.min(amount + food.chicken, 100);
                break;
            case "beef":
                food.beef = Math.min(amount + food.beef, 100);
                break;
            default:
                System.out.print("3!");
                return;
        }
        System.out.print("3");
    }

    /**
     *
     * @param type
     * @param amount
     * @param home
     */
    void feedAnimal(String type, int amount, int home) {

    }
}

class Animal {
    String type;
    String name;
    int home;
}

class Food {
    int hay = 0;
    int corn = 0;
    int grain = 0;
    int carrots = 0;
    int chicken = 0;
    int beef = 0;
}