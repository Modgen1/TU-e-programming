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

    Home[] cages = new Home[15];
    Food food = new Food();

    /**
     *
     */
    MyZoo() {
        for (int i = 0; i < 15; i++) {
            cages[i] = new Home(i);
        }
    }

    /**
     *
     * @param type
     * @param name
     * @param home
     */
    void addAnimal(int type, String name, int home) {
        for (Home cage : cages) {
            for (Animal animal : cage.animals) {
                if (animal != null && animal.name.equals(name)) {
                    System.out.print("0! ");
                    return;
                }
            }
        }

        try {
            cages[home].addAnimal(type, name);
            System.out.print("0 ");
        } catch (Exception e) {
            System.out.print("0! ");
        }
    }

    /**
     *
     * @param name
     * @param home
     */
    void moveAnimal(String name, int home) {
        for (Home cage : cages) {
            for (Animal animal : cage.animals) {
                if (animal != null && animal.name.equals(name)) {
                    try {
                        cages[home].addAnimal(animal.type, animal.name);
                        cage.removeAnimal(animal.name);
                        System.out.print("1 ");
                    } catch (Exception e) {
                        System.out.print("1! ");
                    }
                    return;
                }
            }
        }
    }

    /**
     *
     * @param name
     */
    void removeAnimal(String name) {
        for (Home cage : cages) {
            for (Animal animal : cage.animals) {
                if (animal.name.equals(name)) {
                    cage.removeAnimal(animal.name);
                    System.out.print("2 ");
                    return;
                }
            }
        }
        System.out.print("2! ");
    }

    /**
     *
     * @param type
     * @param amount
     */
    void buyFood(int type, int amount) {
        try {
            if (food.food[type - 1] + amount <= 100) {
                food.food[type - 1] += amount;
                System.out.print("3 ");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.print("3! ");
        }
    }

    /**
     *
     * @param type
     * @param amount
     * @param home
     */
    void feedAnimal(int type, int amount, int home) {

    }
}


class Home {

    int number;
    Animal[] animals;

    /**
     *
     * @param number
     */
    Home(int number) {
        this.number = number;
        if (this.number < 10) {
            this.animals = new Animal[2];
        } else {
            this.animals = new Animal[6];
        }
    }

    /**
     *
     * @param type
     * @param name
     */
    void addAnimal(int type, String name) throws Exception {

        if ((type == 4 || type == 5 || type == 6) && this.number < 10) {
            throw new Exception();
        }
        for (Animal animal : animals) {
            if (animal != null) {
                if (animal.type == 1 || animal.type == 3 || animal.type == 7) {
                    throw new Exception();
                }
                if ((animal.foodDiet.equals("carnivore") || animal.foodDiet.equals("omnivore"))
                        && animal.type != type) {
                    throw new Exception();
                }
            }

        }
        for (int i = 0; i < this.animals.length; i++) {
            if (animals[i] == null) {
                animals[i] = new Animal(type, name);
                return;
            }
        }
        throw new Exception();
    }

    /**
     *
     * @param name
     */
    void removeAnimal(String name) {
        for (int i = 0; i < this.animals.length; i++) {
            if (animals[i].name.equals(name)) {
                animals[i] = null;
            }
        }
    }
}


class Animal {
    int type;
    String name;
    String foodDiet;

    /**
     *
     * @param type
     * @param name
     */
    Animal(int type, String name) throws Exception {
        this.type = type;
        this.name = name;
        this.foodDiet = switch (this.type) {
            case 1, 2, 3 -> "carnivore";
            case 4, 5, 6 -> "herbivore";
            case 7 -> "omnivore";
            default -> throw new Exception();
        };
    }
}


class Food {
    int[] food = new int[6];
}