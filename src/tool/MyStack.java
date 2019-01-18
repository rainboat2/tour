package tool;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyStack<Type> {

    public static final int MAX_CAPACITY = 5;

    private Type[] a;
    private int size;

    public MyStack() {
        this(MAX_CAPACITY);
    }

    public MyStack(int size) {
        a = (Type[]) new Object[size];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == a.length;
    }

    public Type pop() {
        if (isEmpty())
            throw new NoSuchElementException();
        Type t = a[--size];
        a[size] = null;
        return t;
    }

    public void push(Type t) {
        if (isFull())
            throw new IndexOutOfBoundsException();
        a[size++] = t;
    }

    public Iterator<Type> iterator() {
        return new MyIterator();
    }

    public Type peek() {
        return a[size - 1];
    }


    private class MyIterator implements Iterator<Type> {

        private int current;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public Type next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return a[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
