package tool;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 自定义的，固定容量的栈
 * @param <Type> 栈中保存的元素的类型
 */
public class MyStack<Type> {

    public static final int Default_CAPACITY = 5;

    private Type[] a;
    private int size;    //记录加入栈中的元素的个数

    public MyStack() {
        this(Default_CAPACITY);
    }

    /**
     * 按照指定的容量初始化栈
     * @param size 栈的最大容量
     */
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
