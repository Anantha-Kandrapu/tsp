import java.io.IOException;
import java.util.List;

/**
 * The LongRunningTask class contains the methods which take longer to run
 * and may required to be stopped after a certain time.
 */
class LongRunningTask implements Runnable {
    int startingCity;
    String fileName;
    List<String> readResult;

    public LongRunningTask(int startingCity, String fileName, List<String> readResult) {
        this.startingCity = startingCity;
        this.fileName = fileName;
        this.readResult = readResult;
    }

    /**
     * The main TSP logic is called from here. A 2D matrix containing the cities and
     * their distances, is sent as an input.
     * 
     * @param matrix Contains the distances in matrix format
     */
    public void tspTask(double matrix[][]) {
        if (Thread.interrupted())
            return;
        TSPGreedy solver = new TSPGreedy(matrix, startingCity);
        double x = solver.findMinRoute();
        System.out.println("Tour: " + x);
        String finalPath = "";
        List<Integer> path = solver.getMinRoute();
        for (int city : path) {
            finalPath += "city : " + city + " \n";
        }
        GUI g = new GUI();
        g.showFinalPath(finalPath);
    }

    /**
     * This method decides whether the graph is symmetrical or asymmetrical. It is
     * done by
     * looking at the file extension. Based on the data, the matrix is formed
     * accordingly.
     */
    @Override
    public void run() {
        if (fileName.contains("atsp")) {
            AsymmetricParser asymmetricParser = new AsymmetricParser();
            double[][] matrix = asymmetricParser.generateMatrix(readResult);
            tspTask(matrix);
        } else {
            double[][] matrix;
            try {
                matrix = new SymmetricParser(readResult).resultArray();
                tspTask(matrix);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Thread.interrupted()) {
            System.out.println("heyllo");
            return;
        }
    }
}