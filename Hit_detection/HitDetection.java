import java.util.Scanner;

/**
 * Purpose of the program:
 * 
 * The program performs a hit detection. Given two circles with coordinates of their centres,
 * the values of their radius and coordinates of the point of the investigation,
 * the program finds which circle the point hits.
 * Both circles and the point lie on a 2D cartesian plane.
 * A “hit” counts if the point lies inside the circle or on the radius of the circle.
 * It is possible that a point can hit both of the circles, only one of them or neither of them.
 *
 * Instructions on how to use the program:
 *
 * The user should input 8 numbers in one line of input.
 * Each number should be separated by a single space.
 * These numbers can be integers and decimals.
 * The first and the second numbers represent the x and y coordinates
 * of the centre of the first circle respectively.
 * The third number represents the radius of the first circle.
 * The fourth and the fifth numbers represent the x and y coordinates
 * of the centre of the second circle respectively.
 * The sixth number represents the radius of the second circle.
 * The seventh and eighth numbers represent the x and y coordinates of a point respectively.
 *
 * @author Ivan Sergeevich Mishin
 * @ID 2076209
 * @author Nikita Vladimirovich Gamolin
 * @ID 2091402
 */

class HitDetection {
    Scanner input = new Scanner(System.in);

    double xFirstCircle; //x-coordinate of the first circle
    double yFirstCircle; //y-coordinate of the first circle
    double radiusFirstCircle; //radius of the first circle
    double xSecondCircle; //x-coordinate of the second circle
    double ySecondCircle; //y-coordinate of the second circle
    double radiusSecondCircle; //radius of the second circle
    double xPoint; //x-coordinate of the point
    double yPoint; //y-coordinate of the point

    /**
     * The main method that reads input of all required numbers from the
     * user and calls singleHitDetection method
     * for each of the two given circles and checks which of them are hit.
     */
    public void run() {
        xFirstCircle = input.nextDouble();
        yFirstCircle = input.nextDouble();
        radiusFirstCircle = input.nextDouble();
        xSecondCircle = input.nextDouble();
        ySecondCircle = input.nextDouble();
        radiusSecondCircle = input.nextDouble();
        xPoint = input.nextDouble();
        yPoint = input.nextDouble();

        if (radiusFirstCircle < 0 | radiusSecondCircle < 0) {
            System.out.println("input error");
            return; //stops the run of the main method
        }

        boolean firstCircleHit = singleHitDetection(
                xFirstCircle, yFirstCircle, xPoint, yPoint, radiusFirstCircle);
        boolean secondCircleHit = singleHitDetection(
                xSecondCircle, ySecondCircle, xPoint, yPoint, radiusSecondCircle);

        if (firstCircleHit & secondCircleHit) {
            System.out.println("The point hits both circles");
        } else if (firstCircleHit & !secondCircleHit) {
            System.out.println("The point hits the first circle");
        } else if (secondCircleHit) {
            System.out.println("The point hits the second circle");
        } else {
            System.out.println("The point does not hit either circle");
        }
    }

    /**
    * A method that checks whether the given point will hit the circle
    * with given coordinates of the center point and the value of the radius.
    * @param xCircle - x-coordinate of a circle.
    * @param yCircle - y-coordinate of a circle.
    * @param xPoint - x-coordinate of a point.
    * @param yPoint - y-coordinate of a point.
    * @param radius - radius of the circle.
    * @return return the boolean value which holds true or false if the hit counts or does not.
    */
    public boolean singleHitDetection(double xCircle, double yCircle, double xPoint,
                                      double yPoint, double radius) {

        double distance = Math.sqrt((xPoint - xCircle) * (xPoint - xCircle)
                + (yPoint - yCircle) * (yPoint - yCircle));
        //Finding distance between the centre of the circle and the point using Pythagoras' theorem.
        return distance <= radius; //return true or false depending on the success of the hit.
    }

    public static void main(String[] args) {
        new HitDetection().run();
    }
}
