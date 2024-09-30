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
class MyZoo {

    // array containing all Home objects, each of them containing corresponding animals
    // living in these cages
    Home[] cages = new Home[15];

    // object of Food class which contains array containing all values of all food types
    Food food = new Food();

    /**
     * Constructor of MyZoo class which is called every time when object of this class is created.
     * It fills cages array with new objets of Home class.
     */
    MyZoo() {
        for (int i = 0; i < 15; i++) {
            cages[i] = new Home(i);
        }
    }

    /**
     * Method called by AnimalKeeper object to add new animal
     * with a certain name and type to the certain cage.
     *
     * @param type - type of animal that determines where this animal can be placed.
     * @param name - name of this animal specie.
     * @param home - number of the cage where animal is asked to be placed.
     */
    void addAnimal(int type, String name, int home) {
        // firstly, it checks that no animal with the same name exists
        for (Home cage : cages) {
            for (Animal animal : cage.animals) {
                if (animal != null && animal.name.equals(name)) {
                    System.out.print("0! ");
                    return;
                }
            }
        }
        // secondly, it calls addAnimal method from cage object that does the rest of checks
        // regarding placing the animal in a given cage
        try {
            cages[home].addAnimal(type, name);
            System.out.print("0 ");
        } catch (Exception e) {
            // if addAnimal method throws exception, we catch it instead of stopping the program
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
            if (food.storage[type - 1] + amount <= 100) {
                food.storage[type - 1] += amount;
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
     * @param foodType
     * @param amount
     * @param home
     */
    void feedAnimal(int foodType, int amount, int home) {
        try {
            cages[home].feedAnimal(foodType);

            if (food.storage[foodType - 1] - amount >= 0) {
                food.storage[foodType - 1] -= amount;
            } else {
                throw new Exception();
            }

            System.out.print("4 ");
        } catch (Exception e) {
            System.out.print("4! ");
        }
    }
}


/**
 *
 */
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

    /**
     *
     * @param foodType
     */
    void feedAnimal(int foodType) throws Exception {
        for (Animal animal : animals) {
            if (animal != null && animal.foodDiet.equals("carnivore")) {
                if (!(foodType == 5 || foodType == 6)) {
                    throw new Exception();
                }
            } else if (animal != null && animal.foodDiet.equals("omnivore")) {
                if (!(foodType == 4 || foodType == 5 || foodType == 6)) {
                    throw new Exception();
                }
            } else if (animal != null && animal.type == 5) {
                if (!(foodType == 1 || foodType == 2 || foodType == 3)) {
                    throw new Exception();
                }
            } else if (animal != null && animal.foodDiet.equals("herbivore")) {
                if (!(foodType == 1 || foodType == 2 || foodType == 3 || foodType == 4)) {
                    throw new Exception();
                }
            }
        }
    }
}


/**
 *
 */
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


/**
 *
 */
class Food {
    int[] storage = new int[6];
}