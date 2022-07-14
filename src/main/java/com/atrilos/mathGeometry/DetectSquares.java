package com.atrilos.mathGeometry;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a stream of points on the X-Y plane. Design an algorithm that:
 * <p>
 * Adds new points from the stream into a data structure. Duplicate points are allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from the data structure such that the three points and
 * the query point form an axis-aligned square with positive area.
 * An axis-aligned square is a square whose edges are all the same length and are either parallel or perpendicular to the x-axis and y-axis.
 * <p>
 * Implement the DetectSquares class:
 * <p>
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned squares with point point = [x, y] as described above.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
 * Output
 * [null, null, null, null, 1, 0, null, 2]
 * <p>
 * Explanation
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // return 1. You can choose:
 * //   - The first, second, and third points
 * detectSquares.count([14, 8]);  // return 0. The query point cannot form a square with any points in the data structure.
 * detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
 * detectSquares.count([11, 10]); // return 2. You can choose:
 * //   - The first, second, and third points
 * //   - The first, third, and fourth points
 * <p>
 * <p>
 * Constraints:
 * <p>
 * point.length == 2
 * 0 <= x, y <= 1000
 * At most 3000 calls in total will be made to add and count.
 */
public class DetectSquares {

    private final Map<Point, Integer> pointsCount;
    private final List<int[]> coordinates;

    public DetectSquares() {
        pointsCount = new HashMap<>();
        coordinates = new ArrayList<>();
    }

    //store the frequency of all the points, look at the unique way of storing it splitting
    //the points int two parts and forming a string of a point
    public void add(int[] point) {
        coordinates.add(point);
        Point coordinate = new Point(point[0], point[1]);
        pointsCount.merge(coordinate, 1, Integer::sum);
    }

    public int count(int[] point) {
        int total = 0;
        int x = point[0];
        int y = point[1];

        for (int[] key : coordinates) {
            //check if a diagonal point exists
            if (Math.abs(key[0] - x) != 0 && Math.abs(key[0] - x) == Math.abs(key[1] - y)) {
                //take y of diagonal and x of current point
                Point leftCoordinate = new Point(key[0], y);
                //take x of diagonal and y of current point
                Point rightCoordinate = new Point(x, key[1]);
                if (pointsCount.containsKey(leftCoordinate) && pointsCount.containsKey(rightCoordinate))
                    total += pointsCount.get(leftCoordinate)
                            * pointsCount.get(rightCoordinate);
            }
        }
        return total;
    }
}
