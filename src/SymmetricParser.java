import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the symmetric data and converts it into a 2D distance
 * matrix.
 */
public class SymmetricParser {
    public static Map<Integer, Point> points; // map of points
    public static ArrayList<ArrayList<Double>> finalArray = new ArrayList<ArrayList<Double>>();

    /**
     * This method takes the data from the file and removes the unnecessary data.
     * 
     * @param fileName
     * @throws IOException
     */
    public SymmetricParser(List<String> readResult) throws IOException {
        if (Thread.interrupted())
            return;
        points = new HashMap<Integer, Point>();
        String pattern = "(?m)^\\d+\\s\\d+\\.\\d+\\s\\d+\\.\\d+";
        Pattern r = Pattern.compile(pattern);
        for (String value : readResult) {
            Matcher m = r.matcher(value);
            if (m.find()) {
                Point p = new Point(Integer.parseInt(value.split(" ")[0]),
                        Double.parseDouble(value.split(" ")[1]),
                        Double.parseDouble(value.split(" ")[2]));
                points.put(p.name, p);
            }
        }
        showPoints();
    }

    /**
     * This method invokes the GUI class to create a panel to display the generated
     * coordinates.
     */
    public void showPoints() {
        GUI x = new GUI(points);
        x.utilDraw();
    }

    /**
     * This method converts the data into the 2D distance matrix which is used by
     * the TSP algorithm.
     * 
     * @return double[][] Which is the 2D distance matrix
     */
    public double[][] resultArray() {
        points.size();
        for (Integer p1 : points.keySet()) {
            ArrayList<Double> test = new ArrayList<>();
            for (Integer p2 : points.keySet()) {
                double result = Math.pow(points.get(p1).x - points.get(p2).x, 2)
                        + Math.pow(points.get(p1).y - points.get(p2).y, 2);
                double distance = Math.sqrt(result);
                test.add(distance);
            }
            finalArray.add(test);
        }
        double[][] arr = finalArray.stream()
                .map(l -> l.stream().mapToDouble(Double::intValue).toArray())
                .toArray(double[][]::new);
        return arr;
    }
}
