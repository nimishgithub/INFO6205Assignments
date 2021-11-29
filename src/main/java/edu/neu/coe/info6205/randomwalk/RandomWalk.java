/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.io.Console;
import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        // IMPLEMENTED
        /*
         * Possible Directions:
         * North (0, 1), South (0, -1), West (-1, 0), East (1, 0)
         */
//        System.out.println(String.format("-> Making a random move to: (%d, %d)", dx, dy));
        this.x += dx;
        this.y += dy;
//        System.out.println(String.format("-> Position after making the random move: (%d, %d)", this.x, this.y));
    }


    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        // To be implemented
        for (int i = 0; i < m; i++) {
            randomMove(); // make a random move
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        // To be implemented
        int x1 = 0, x2 = this.x, y1 = 0, y2 = this.y;

        double distance = Math.sqrt(Math.pow(Math.abs(y2 - y1), 2) + Math.pow(Math.abs(x2 - x1), 2));

        return distance;
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            double distance = walk.distance();
            totalDistance += distance;

//
//            String meanDistFormatted = String.format("%.2f", distance);
//            System.out.printf("For Steps = %-10s Distance = %-10s\n", m, meanDistFormatted);
        }

        double meanDistance = totalDistance / n;

//        System.out.println(String.format("Steps(m) = %d,    Mean distance(d) = %.6f", m, meanDistance));
        String meanDistFormatted = String.format("%.2f", meanDistance);
        System.out.printf("For Steps = %-10s Distance = %-10s\n", m, meanDistFormatted);

        return meanDistance;
    }

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        int m = Integer.parseInt(args[0]);
        int n = 30;
        if (args.length > 1) n = Integer.parseInt(args[1]);
        double meanDistance = randomWalkMulti(m, n);
        System.out.println(m + " steps: " + meanDistance + " over " + n + " experiments");
    }

}
