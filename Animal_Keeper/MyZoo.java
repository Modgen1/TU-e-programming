/**
 * Purpose:
 *
 * Usage:
 * From the AnimalKeeper object
 *
 * Available types of animal species:
 * 1. Lion;
 * 2. Tiger;
 * 3. Leopard;
 * 4. Zebra;
 * 5. Antelope;
 * 6. Giraffe;
 * 7. Bear.
 *
 * Available types of food:
 * 1. hay;
 * 2. corn;
 * 3. grain;
 * 4. carrots;
 * 5. chicken;
 * 6. beef.
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
     * Method called by AnimalKeeper object to move existing animal to another cage.
     * Animal is moved only if it is possible to have animal in a destination cage.
     *
     * @param name - name of the animal that needs to be moved.
     * @param home - destination where animal should be moved.
     */
    void moveAnimal(String name, int home) {
        // firstly, we iterate through all cages and animals to find animal with the given name
        for (Home cage : cages) {
            for (Animal animal : cage.animals) {
                if (animal != null && animal.name.equals(name)) {
                    try {
                        // if addAnimal method throws exception, animal will not be moved
                        cages[home].addAnimal(animal.type, animal.name);
                        cage.removeAnimal(animal.name);
                        System.out.print("1 ");
                    } catch (Exception e) {
                        System.out.print("1! ");
                    }
                    // stops iteration after we found animal that should be moved
                    return;
                }
            }
        }
    }

    /**
     * Method called by AnimalKeeper object to remove existing animal from its cage.
     * Removes only if there exists an animal with given name.
     *
     * @param name - name of the animal to be removed from the zoo.
     */
    void removeAnimal(String name) {
        // firstly, we iterate through all cages and animals to find animal with the given name
        for (Home cage : cages) {
            for (Animal animal : cage.animals) {
                if (animal.name.equals(name)) {
                    // when animal is found, we call remove method from cage object
                    cage.removeAnimal(animal.name);
                    System.out.print("2 ");
                    return;
                }
            }
        }
        // if animal was not found, method results unsuccessful completion
        System.out.print("2! ");
    }

    /**
     * Method called by AnimalKeeper object to buy certain amount of certain type of food.
     * Accepts only types of the food that are specified in the documentation.
     * Will not finish successfully if there is not enough space to fit all food in the storage.
     *
     * @param foodType - type of the food to be bought. See docs for list of available food types.
     * @param amount - amount of the food that will be bought
     */
    void buyFood(int foodType, int amount) {
        try {
            // check that we have enough space for the food
            if (food.storage[foodType - 1] + amount <= 100) {
                food.storage[foodType - 1] += amount;
                System.out.print("3 ");
            } else {
                throw new Exception();
            }
        } catch (Exception e) { // this catch also doubles as check for illegal food types
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
 * Class that handles interaction with every certain cage and open enclosure in the zoo.
 * Object of this class displays only one certain cage or open enclosure.
 * All interactions regarding animals in cages are handled by methods of this class.
 * Objects of this class should only be created by zoo object during its initialisation.
 */
class Home {

    int number;
    Animal[] animals;

    /**
     * Constructor that is called during initialisation of the cage.
     * Takes its cage number as an argument and uses it to determine its size - either 2 or 6.
     *
     * @param number - cage number. If between 0 and 9, cage size is 2, if between 10 and 14 - 6.
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
 * Class for every animal in the zoo that stores their type, name and type of food diet.
 * When object is created, type and name are passed as arguments, food diet is then determined.
 */
class Animal {
    int type;
    String name;
    String foodDiet; // can be herbivore, carnivore or omnivore

    /**
     * Constructor of the Animal object that takes type and the name as arguments.
     * Depending on the type of the animal, food diet is then determined.
     *
     * @param type - type of the animal specie. See list of possible animal types in file docs.
     * @param name - unique name of the animal.
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
 * Class for food storage that contains array of integers with values of different food types.
 * Initially all storages have 0 food in them but food can be added and then used from MyZoo class.
 * See the file documentation for list of available food types.
 */
class Food {
    int[] storage = new int[6];
}