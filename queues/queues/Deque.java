import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    
    private Node first;
    private Node last;
    private int size;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;

        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;

        if (oldFirst == null) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
        
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldLast = last;
        last = new Node();

        last.item = item;
        last.prev = oldLast;
        last.next = null;

        if (oldLast == null) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        first = oldFirst.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }
        oldFirst.next = null;
        size--;
        return oldFirst.item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldLast = last;
        last = oldLast.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        oldLast.prev = null;
        size--;
        return oldLast.item;
        
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { 
        return new DequeIterator();
        
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }


    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        for (int i : deque) {
            System.out.println(i);
        }
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        for (int i : deque) {
            System.out.println(i);
        }
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        deque.addFirst(5);
        deque.addLast(6);
        for (int i : deque) {
            System.out.println(i);
        }
    }
}