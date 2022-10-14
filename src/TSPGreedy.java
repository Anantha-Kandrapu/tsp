import java.util.*;

/**
 * This is the class which contains all the logic for the TSP problem. It uses
 * the Greedy
 * approach. It takes a 2D distance matrix to calculate the minimum path which
 * connects all
 * the cities.
 */
public class TSPGreedy {
    double tsp[][];
    int startingCity;
    List<Integer> minRoute;

    public TSPGreedy(double tsp[][], int startingCity) {
        this.tsp = tsp;
        this.startingCity = startingCity;
    }

    /**
     * Traverse the 2D matrix. While traversing, update the minimum distance as and
     * when it is
     * encountered, only if the city is unvisited.
     * 
     * @return Minimum distance
     */
    public double findMinRoute() {

        if (Thread.interrupted())
            return 0;
        double distance = 0;
        int counter = 0;
        int j = 0, i = 0;
        double min = Integer.MAX_VALUE;
        List<Integer> visitedRouteList = new ArrayList<>();
        visitedRouteList.add(startingCity);
        int[] route = new int[tsp.length];

        while (i < tsp.length && j < tsp[i].length) {
            if (counter >= tsp[i].length - 1) {
                break;
            }
            if (j != i && !(visitedRouteList.contains(j))) {
                if (tsp[i][j] < min) {
                    min = tsp[i][j];
                    route[counter] = j + 1;
                }
            }
            j++;
            if (j == tsp[i].length) {
                distance += min;
                min = Integer.MAX_VALUE;
                visitedRouteList.add(route[counter] - 1);
                j = 0;
                i = route[counter] - 1;
                counter++;
                minRoute = visitedRouteList;
            }
        }
        i = route[counter - 1] - 1;
        for (j = 0; j < tsp.length; j++) {
            if ((i != j) && tsp[i][j] < min) {
                min = tsp[i][j];
                route[counter] = j + 1;
            }
        }
        distance += min;
        return distance;
    }

    public List<Integer> getMinRoute() {
        return minRoute;
    }
}
