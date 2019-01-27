import java.util.Arrays;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Item[] s;
    private int n, next, front, rear;

    public Deque() {
        // construct an empty deque
        s = (Item[]) new Object[10];
        front = 0;
        rear = 0;
        n = 0;
        Arrays.fill(s, null);
    }


    public boolean isEmpty() {
        // is the deque empty?
        return n == 0;

    }

    public int size() {
        // return the number of items on the deque
        return n;
    }

    private void resize(int length) {


        Item[] copy = (Item[]) new Object[length];
        int k = 0;
        int i = front + 1, iCopy = i;
        if (front == s.length - 1) i = 0;
        copy[k++] = s[i++];
        while (i != iCopy) {
            if (i > s.length - 1) {
                i = 0;
            }
            copy[k++] = s[i];
            if (k >= copy.length) break;
            i++;
        }
        //  if (n < s.length / 4) front = -1;
        front = -1;
        rear = n;

        s = copy;
    }


    public void addFirst(Item item) {
        // add the item to the front
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }
        if (isEmpty()) s[rear++] = s[front--] = item;
        else if (size() == s.length) {
            resize(2 * s.length);
            front = s.length - 1;
            s[front--] = item;
        } else if (!isEmpty() && front > 0) s[front--] = item;
        else if (!isEmpty() && front < 0) {
            front = s.length - 1;
            s[front--] = item;
        }
        next = front + 1;
        n++;

    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (isEmpty()) {
            s[front--] = s[rear++] = item;

        } else if (size() == s.length) {
            resize(2 * s.length);
            s[rear++] = item;
        } else if (rear == s.length) {
            rear = 0;
            s[rear++] = item;
        } else s[rear++] = item;


        n++;

    }


    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (!isEmpty() && front >= (s.length - 1)) front = -1;
        Item item = s[++front];
        s[front] = null;
        n--;
        if (n < s.length / 4) resize(s.length / 2);
        next = front + 1;
        return item;
    }


    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (!isEmpty() && rear <= 0) rear = s.length;
        Item item = s[--rear];
        s[rear] = null;
        n--;
        if (isEmpty()) {
            front = 0;
            rear = 0;
        }
        if (n < s.length / 4) resize(s.length / 2);
        return item;
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to
        return new ListIterator();

    }

    private class ListIterator implements Iterator<Item> {
        private int next2 = next;

        public boolean hasNext() {
            if (next2 == s.length) next2 = 0;
            return s[next2] != null;

        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            else {
                return s[next2++];

            }

        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();

        }
    }
}

