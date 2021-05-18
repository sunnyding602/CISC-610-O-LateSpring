class Node {
    Node parent = null;
    Node left = null;
    Node right = null;
    int priority = 0;
    int data = 0;
    Node(int priority, int data) {
        this.priority = priority;
        this.data = data;
    }
}