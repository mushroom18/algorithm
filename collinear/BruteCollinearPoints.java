import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    // list of collinear segments
    private List<LineSegment> collinearSegments =new ArrayList<>(); 

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        // check if points is null
        if (points == null) {
            throw new IllegalArgumentException("points is null");
        }
        //copy points to a new array
        Point[] copyPoints = Arrays.copyOf(points, points.length);
        // check if copyPoints contains null
        for (Point point : copyPoints) {
            if (point == null) {
                throw new IllegalArgumentException("copyPoints contains null");
            }
        }

        // sort copyPoints by y-coordinate, then by x-coordinate
        Arrays.sort(copyPoints);

        // check if copyPoints contains duplicate points
        for (int i = 0; i < copyPoints.length - 1; i++) {
            if (copyPoints[i].compareTo(copyPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }

        // check length of copyPoints
        if (copyPoints.length < 4) {
            return;
        }
        

        // check if 4 points are collinear
        for (int i = 0; i < copyPoints.length - 3; i++) {
            for (int j = i + 1; j < copyPoints.length - 2; j++) {
                for (int k = j + 1; k < copyPoints.length - 1; k++) {
                    for (int l = k + 1; l < copyPoints.length; l++) {
                        if (copyPoints[i].slopeTo(copyPoints[j]) == copyPoints[i].slopeTo(copyPoints[k]) && copyPoints[i].slopeTo(copyPoints[j]) == copyPoints[i].slopeTo(copyPoints[l])) {
                            collinearSegments.add(new LineSegment(copyPoints[i], copyPoints[l]));
                        }
                    }
                }
            }
        }
    }


    // the number of line segments
    public int numberOfSegments() {
        return collinearSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return collinearSegments.toArray(new LineSegment[0]);

    }
}