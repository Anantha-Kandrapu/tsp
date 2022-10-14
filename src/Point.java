/**
 * This class defines the basic structure of the point. It contains the name of the city and
 * the X and Y coordinates.
 */
public class Point {
    int name;
    double x, y;
    Point(int name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    void setName(int a) {
        this.name = a;
    }
    double getX() {
        return x;
    }
    double getY() {
        return y;
    }
}
