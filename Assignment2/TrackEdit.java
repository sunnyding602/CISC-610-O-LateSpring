import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TrackEdit {
    // store states
    private LinkedList<List<Integer>> doubleLinkedList = new LinkedList<>();

    // a pointer which points to the current state
    private int current = 0;

    TrackEdit() {
        List<Integer> list = new LinkedList<>();
        list.add(5);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(4);
        list.add(8);
        doubleLinkedList.add(list);
        current = 0;
    }

    public void myDo(String action, Integer value) {
        List<Integer> list = new LinkedList<>(doubleLinkedList.get(current));
        if (action.equals("sort")) {
            Collections.sort(list);
        } else if (action.equals("reverse")) {
            Collections.reverse(list);
        } else if (action.equals("add")) {
            list.add(value);
        }

        // making sure we are adding next state at position current+1
        while( doubleLinkedList.size()-1 > current) {
            doubleLinkedList.pollLast();
        }
        doubleLinkedList.add(list);
        current++;
    }

    public void myDo(String action) {
        this.myDo(action, null);
    }

    public void undo() {
        if (current - 1 < 0) {
            System.out.println("unable to undo");
            return;
        }
        current--;
    }

    public void printCurrentState() {
        System.out.println(doubleLinkedList.get(current));
    }

    public void redo() {
        // we don't need this method
        if (current + 1 >= doubleLinkedList.size()) {
            System.out.println("cannot redo");
        }
        current++;
    }

    public static void main(String[] args) {
        TrackEdit te = new TrackEdit();
        te.printCurrentState();

        te.myDo("sort");
        te.printCurrentState();

        te.myDo("reverse");
        te.printCurrentState();

        te.myDo("add", 9);
        te.printCurrentState();

        // Undo back to before you reversed the list.
        te.undo();
        te.undo();
        te.printCurrentState();

        te.myDo("add", 9);
        te.printCurrentState();

        te.undo();
        te.undo();
        te.printCurrentState();
    }
}
