import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Brute force algorithm for finding all line segments containing 4 or more collinear points from a set.
 */
public class BruteCollinearPoints {

//    private final Point[] points;

    private LineSegment[] lineSegments;

    /**
     * Finds all line segments containing 4 points
     *
     * @param points the input points to analise
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array is null.");
        }

//        this.points = points;

        this.analise(points);
    }

    /**
     * Get number of line segments formed by these points.
     *
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    /**
     * Get line segments formed by these points.
     *
     * @return the line segments
     */
    public LineSegment[] segments() {
        return this.lineSegments;
    }

    /**
     * Analise these points and find all segments.
     *
     * @param points the points to analise
     */
    private void analise(Point[] points) {
        List<LineSegment> tempSegments = new ArrayList<>();
        Point[] tempPoints = new Point[4];

        for (int i = 0; i < points.length - 3; i++) {
            Point point0 = points[i];
            tempPoints[0] = point0;

            if (point0 == null) {
                throw new IllegalArgumentException("Point is null.");
            }

            for (int j = i + 1; j < points.length - 2; j++) {
                Point point1 = points[j];
                tempPoints[1] = point1;

                if (point1 == null) {
                    throw new IllegalArgumentException("Point is null.");
                } else if (point1.compareTo(point0) == 0) {
                    throw new IllegalArgumentException("Point is identical.");
                }

                Double slope1 = point0.slopeTo(point1);

                for (int k = j + 1; k < points.length - 1; k++) {
                    Point point2 = points[k];
                    tempPoints[2] = point2;

                    if (point2 == null) {
                        throw new IllegalArgumentException("Point is null.");
                    } else if (point2.compareTo(point0) == 0) {
                        throw new IllegalArgumentException("Point is identical.");
                    }

                    Double slope2 = point0.slopeTo(point2);

                    if (!slope1.equals(slope2)) {
                        continue;
                    }

                    for (int l = k + 1; l < points.length; l++) {
                        Point point3 = points[l];
                        tempPoints[3] = point3;

                        if (point3 == null) {
                            throw new IllegalArgumentException("Point is null.");
                        } else if (point3.compareTo(point0) == 0) {
                            throw new IllegalArgumentException("Point is identical.");
                        }

                        Double slope3 = point0.slopeTo(point3);

                        if (!slope1.equals(slope3)) {
                            continue;
                        }

                        Arrays.sort(tempPoints);

                        tempSegments.add(new LineSegment(tempPoints[0], tempPoints[3]));
                    }
                }
            }
        }

        this.lineSegments = tempSegments.toArray(new LineSegment[0]);
    }

}
