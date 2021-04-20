import java.util.LinkedList;

public class MyStack<E> {
    private LinkedList<E> list = new LinkedList<>();

    public boolean empty() {
        return list.isEmpty();
    }

    public E peek() {
        return list.getLast();
    }

    public E pop() {
        return list.pollLast();
    }

    public E push (E item) {
        list.addLast(item);
        return item;
    }
}
