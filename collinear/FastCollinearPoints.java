import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
   // list of collinear segments
   private List<LineSegment> collinearSegments =new ArrayList<>(); 

   // finds all line segments containing 4 or more points
   public FastCollinearPoints(Point[] points) {
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
         for (int i = 0; i < copyPoints.length; i++) {
            Point p = copyPoints[i];

            Point[] slopeSorted = Arrays.copyOf(copyPoints, copyPoints.length);
            Arrays.sort(slopeSorted, p.slopeOrder());

            int j = 1;
            while (j < slopeSorted.length) {
               int k = j + 1;
               while (k < slopeSorted.length && p.slopeTo(slopeSorted[j]) == p.slopeTo(slopeSorted[k])) {
                  k++;
               }
               if (k - j >= 3) {
                  if (p.compareTo(slopeSorted[j]) < 0) {
                     collinearSegments.add(new LineSegment(p, slopeSorted[k - 1]));
                  }
               }
               j = k;

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