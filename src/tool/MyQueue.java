package tool;

import java.util.NoSuchElementException;

public class MyQueue<Type> {

    private Node first;
    private Node rear;

    private class Node {
        Type element;
        Node next;

        public Node(Type element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    public MyQueue() {
        clear();
    }

    public void clear() {
        first = rear = new Node(null, null);
    }

    public boolean isEmpty() {
        return first == rear;
    }


    public void enQueue(Type element) {
        rear.next = new Node(element, null);
        rear = rear.next;
    }

    public Type deQueue() {
        if (isEmpty())
            throw new NoSuchElementException();
        first = first.next;
        return first.element;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Node temp = first.next; temp != null; temp = temp.next)
            s.append(temp.element);
        return s.toString();
    }
}
