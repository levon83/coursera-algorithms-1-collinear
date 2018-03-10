import org.junit.Test;

public class BruteCollinearPointsTest {

    @Test(expected = IllegalArgumentException.class)
    public void anyPointIsNull() {
        Point[] points = new Point[]{
                new Point(19934, 17243),
                null,
                new Point(17182, 17203)
        };

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    }

    @Test(expected = IllegalArgumentException.class)
    public void anyPointIsDuplicate() {
        Point[] points = new Point[]{
                new Point(24937, 9227),
                new Point(31883, 16632),
                new Point(24937, 9227)
        };

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    }

}
