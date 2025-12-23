import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size; // actual number of items

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // resize the array to the given capacity
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        // assign the new array to the items field
        items = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(0, size);

        // store the item to be returned
        Item item = items[randomIndex];
        // swap the item with the last item
        items[randomIndex] = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniformInt(0, size);
        return items[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
        
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i;
        private Item[] copy;
        
        public RandomizedQueueIterator() {
            copy = (Item[]) new Object[size];
            for (int j = 0; j < size; j++) {
                copy[j] = items[j];
            }
            StdRandom.shuffle(copy);
            // initialize the index
            i = 0;
        }

        public boolean hasNext() {
            return i < size;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        for (int i : queue) {
            System.out.println(i);
        }
        System.out.println(queue.dequeue());
        System.out.println(queue.sample());
        for (int i : queue) {
            System.out.println(i);
        }
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        System.out.println(queue.dequeue());
        System.out.println(queue.sample());
        for (int i : queue) {
            System.out.println(i);
        } 
    }
}