import java.util.ArrayList;
import java.util.List;

/**
 * This class parses files that contain asymmetric data, and forms a 2D distance matrix on which
 * the TSP algorithm runs.
 */
public class AsymmetricParser {
    /**
     * This method removes the unnecessary lines in the input file, and keeps only the line containing
     * the DIMENSION parameter.
     * @param readResult
     * @return int which is the DIMENSION parameter or the number of cities in the asymmetric file
     */
    public int parseGetCities(List<String> readResult) {
        int cities = 0;
        for (String row : readResult) {
            if (row.contains("DIMENSION")) {
                int cut = row.indexOf(':');
                String city = row.substring(cut + 1, row.length()).replaceAll("\\s+", "");
                cities = Integer.parseInt(city);
            }
        }
        return cities;
    }

    /**
     * This method generates the 2D matrix for the TSP algorithm.
     * @param readResult
     * @return double[][] which is the 2D distance matrix
     */
    public double[][] generateMatrix(List<String> readResult) {
        int cities = parseGetCities(readResult);
        List<String> flatStrings = new ArrayList<>();
        int ignoreAfter = -7;
        for (String row : readResult) {
            if(ignoreAfter++ < 0)
                continue;
            String[] ans = row.replace("\n", "").split("\\s+");
            for (String str : ans)
                flatStrings.add(str);
        }
        int k = 0;
        double[][] matrix = new double[cities][cities];
        for (int i = 0; i < cities; i++) {
            for (int j = 0; j < cities; j++) {
                while (flatStrings.get(k).isEmpty())
                    k += 1;
                matrix[i][j] = Double.parseDouble(flatStrings.get(k));
                k += 1;
            }
        }
        return matrix;
    }
}
