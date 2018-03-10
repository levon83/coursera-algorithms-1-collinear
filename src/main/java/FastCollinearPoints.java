import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

/**
 * Faster algorithm for finding all line segments containing 4 or more collinear points from a set.
 */
public class FastCollinearPoints {

    private List<LineSegment> tempSegments = new LinkedList<>();

    /**
     * Finds all line segments containing 4 points
     *
     * @param points the input points to analise
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array is null.");
        }

        this.analise(points);
    }

    /**
     * Get number of line segments formed by these points.
     *
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return this.tempSegments.size();
    }

    /**
     * Get line segments formed by these points.
     *
     * @return the line segments
     */
    public LineSegment[] segments() {
        return this.tempSegments.toArray(new LineSegment[0]);
    }

    /**
     * Analise these points and find all segments.
     *
     * @param points the points to analise
     */
    private void analise(Point[] points) {
        StdRandom.shuffle(points);

        for (int i = 0; i < points.length; i++) {
            Point point = points[i];

            if (point == null) {
                throw new IllegalArgumentException("Point is null.");
            }

            sort(point, points, i + 1, points.length - 1);
        }
    }

    /**
     * Modified implementation of QuickSort With Duplicates to also find line segments.
     * Sorting is based on the slope to the provided point.
     *
     * @param point  the point to compare slopes to
     * @param points the collection of all points
     * @param lo     lowest index of subset
     * @param hi     highest index of subset
     */
    private void sort(Point point, Point[] points, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int lt = lo;
        int gt = hi;
        Point part = points[lo];
        int i = lo;
        double slopePart = point.slopeTo(part);
        Point segmentFrom = point;
        Point segmentTo = point;

        while (i <= gt) {
            Point next = points[i];

            if (next == null) {
                throw new IllegalArgumentException("Point is null.");
            } else if (point.compareTo(next) == 0) {
                throw new IllegalArgumentException("Point is identical.");
            }

            double slopeNext = point.slopeTo(next);
            int slopeCmp = Double.compare(slopeNext, slopePart);

            if (slopeCmp < 0) {
                exchange(points, lt++, i++);
            } else if (slopeCmp > 0) {
                exchange(points, gt--, i);
            } else {
                i++;

                if (next.compareTo(segmentFrom) < 0) {
                    segmentFrom = next;
                } else if (next.compareTo(segmentTo) > 0) {
                    segmentTo = next;
                }
            }
        }

        if (gt - lt + 1 >= 3) {
            this.tempSegments.add(new LineSegment(segmentFrom, segmentTo));
        }

        sort(point, points, lo, lt - 1);
        sort(point, points, gt + 1, hi);
    }

    /**
     * Switch two elements in the array.
     *
     * @param a the array
     * @param i first element
     * @param j second element
     */
    private void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

}
