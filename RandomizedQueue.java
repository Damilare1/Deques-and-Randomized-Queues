import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {


    private Item[] s;
    private int n = 0;

    public RandomizedQueue() {
        // construct an empty randomized queue
        s = (Item[]) new Object[3];
    }

    public boolean isEmpty() {
        // is the randomized queue empty?
        return n == 0;
    }

    public int size() {
        // return the number of items on the randomized queue
        return n;
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (n > 0 && n == s.length / 2) resize(s.length * 2);
        s[n++] = item;
    }

    public Item dequeue() {
        // remove and return a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randomInt = StdRandom.uniform(0, n);
        Item randomElem = s[randomInt];
        if (randomElem == null) dequeue();
        for (int i = randomInt; i < n; i++) {
            s[i] = s[i + 1];
        }
        n--;
        return randomElem;
    }

    private void resize(int length) {
        Item[] copy = (Item[]) new Object[length];
        System.arraycopy(s, 0, copy, 0, n);
        s = copy;

    }

    public Item sample() {
        // return a random item (but do not remove it)
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randomInt = StdRandom.uniform(0, n);
        return s[randomInt];

    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new ListRandomIterator();

    }

    private class ListRandomIterator implements Iterator<Item> {
        private int i = size();
        private int[] index;

        public ListRandomIterator() {
            index = new int[i];
            for (int j = 0; j < i; ++j) {
                index[j] = j;
            }
            StdRandom.shuffle(index);
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return s[index[--i]];
        }
    }

    public static void main(String[] args) {
        // unit testing (optional)
    }
}
