import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
   public static void main(String[] args) {
    int k = Integer.parseInt(args[0]);
    if (k <= 0) return;

    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    int count = 0;

    while (!StdIn.isEmpty()) {
        String item = StdIn.readString();
        count++;
        // if the count is less than or equal to k, enqueue the item
        if (count <= k) {
            queue.enqueue(item);
        } else {
            if (StdRandom.uniformInt(count) < k) {
                queue.dequeue();
                queue.enqueue(item);
            }
        }
    }

    for (String item : queue) {
        StdOut.println(item);
    }

   }
}