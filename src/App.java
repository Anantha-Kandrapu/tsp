import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * The App class contains the driver code for the TSP algorithm. It takes the
 * input file
 * and decides whether to parse it symmetrically or asymmetrically based on the
 * file
 * extension.
 *
 * @author Ananth R
 * @author Paromita R
 * @author Danish K
 * @Author Chaitanya K
 */
public class App {
    static String fileName;
    static int startingCity = 0;

    /**
     * Fetches the content of the file and creates a list out of it.
     * 
     * @param fileName
     * @return List<String> Containing the contents of the file
     */
    public static List<String> readFile(String fileName) {
        List<String> readResult = new ArrayList<>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                readResult.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return readResult;
    }

    /**
     * This is the driver code. It passes the filename. It also ensures that the
     * algorithm does
     * not run for more than 300 seconds.
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cities file with extension tsp or atsp : ");
        fileName = sc.next();
        System.out.println("Enter a Starting City ");
        startingCity = sc.nextInt();
        List<String> readResult = readFile(fileName);
        sc.close();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(new LongRunningTask(startingCity, fileName, readResult));
        try {
            future.get(300, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdownNow();
        }
    }
}
