import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyMinHeap {
    Node root = null;
    Node lastNode = null;
    private int size = 0;

    public void add(Node newNode) {
        if (root == null) {
            root = newNode;
            lastNode = newNode;
        } else {
            // find the slot
            lastNode = this.addNewNode(newNode);
            this.siftUp(newNode);
        }
        this.size++;
    }

    public Node remove() {
        if (this.size > 0 && this.root != null) {
            Node returnNode = new Node(root.priority, root.data);
            this.swap(root, lastNode);

            if (lastNode.parent == null) {
                root = null;
                this.size--;
            } else {
                if (lastNode.parent.left == lastNode) {
                    lastNode.parent.left = null;
                    this.size--;
                    // find lastNode
                    lastNode = this.findLastNode();

                } else if (lastNode.parent.right == lastNode) {
                    lastNode.parent.right = null;
                    this.size--;
                    lastNode = lastNode.parent.left;
                }
                this.siftDown(root);
            }

            return returnNode;
        }
        return null;
    }

    private void swap(Node a, Node b) {
        int tmpPriority = a.priority;
        a.priority = b.priority;
        b.priority = tmpPriority;

        int tmpData = a.data;
        a.data = b.data;
        b.data = tmpData;
    }

    private void siftUp(Node node) {
        while( node.parent != null && node.parent.priority > node.priority) {
            this.swap(node.parent, node);
            node = node.parent;
        }
    }

    private void siftDown(Node node) {
        while(node.left != null || node.right != null) {
            if (node.left != null && node.right != null) {
                if(node.left.priority < node.right.priority && node.left.priority < node.priority) {
                    this.swap(node.left, node);
                    node = node.left;
                } else if (node.right.priority < node.left.priority && node.right.priority < node.priority){
                    this.swap(node.right, node);
                    node = node.right;
                } else {
                    break;
                }
            } else if (node.left != null && node.right == null) {
                if (node.left.priority < node.priority) {
                    this.swap(node.left, node);
                    node = node.left;
                } else {
                    break;
                }
            } else if (node.right != null && node.left == null) {
                if (node.right.priority < node.priority) {
                    this.swap(node.right, node);
                    node = node.right;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

    }

    public Node peak() {
        if (root != null) {
            return root;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        return this.size;
    }

    public void enqueue(Node newNode) {
        this.add(newNode);
    }

    public Node dequeue() {
        return this.remove();
    }

    private Node findLastNode() {
        Node root = this.root;
        if (this.root == null) {
            return null;
        }
        String binaryStr = Integer.toBinaryString(this.size);
        char[] charArr = binaryStr.toCharArray();
        for (int i = 1; i < binaryStr.length(); i++) {
            if (charArr[i] == '0') {
                root = root.left;
            } else if (charArr[i] == '1') {
                root = root.right;
            }
        }
        return root;
    }

    private Node addNewNode(Node node) {
        Node root = this.root;
        if (root == null) {
            this.root = node;
        } else {
            String binaryStr = Integer.toBinaryString(this.size + 1);
            char[] charArr = binaryStr.toCharArray();
            for (int i = 1; i < binaryStr.length(); i++) {

                if (charArr[i] == '0') {
                    if (i == binaryStr.length()-1) {
                        node.parent = root;
                        root.left = node;
                    } else {
                        root = root.left;
                    }
                } else if (charArr[i] == '1') {
                    if (i == binaryStr.length()-1) {
                        node.parent = root;
                        root.right = node;
                    } else {
                        root = root.right;
                    }
                }
            }
        }
        return node;
    }

    public static void main(String[] args) throws Exception {
        MyMinHeap heap = new MyMinHeap();
        // 4, 7, 5, 11, 8, 6, 9

        boolean exitFlag = false;
        while(exitFlag == false) {
            System.out.println("Welcome to priority queue CLI!");
            System.out.println("Please enter as the following format: priority[space]data. e.g. 22 123");
            System.out.println("Type \"deque\" to dequeue an item from the priority queue");
            System.out.println("Type \"peek\" to peek the highest priority item");
            System.out.println("Type \"size\" to check the size of the priority queue");
            System.out.println("Type exit to quit this program");

            while(true) {
                System.out.print("Waiting for input: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String str = reader.readLine();
                if (str.contentEquals("exit")) {
                    exitFlag = true;
                    break;
                }
                if (str.contentEquals("deque")) {
                    if (heap.size() > 0) {
                        Node n = heap.dequeue();
                        System.out.println("An item with priority: " + n.priority + " and data: " + n.data + " has been dequeued");
                    } else {
                        System.out.println("Priority queue is empty!");
                    }
                    continue;
                }

                if (str.contentEquals("peek")) {
                    if (heap.size() > 0) {
                        Node n = heap.peak();
                        System.out.println("An item with priority: " + n.priority + " and data: " + n.data + " has been dequeued");
                    } else {
                        System.out.println("Priority queue is empty!");
                    }
                    continue;
                }

                if (str.contentEquals("size")) {
                    System.out.println("The size of the current priority queue is: " + heap.size());
                    continue;
                }
                String[] strArr = str.split(" ");
                if (strArr.length != 2) {
                    System.out.println("ERROR INPUT, please try again (take a look at the expected format)");
                    continue;
                }
                int priority = Integer.parseInt(strArr[0]);
                int data = Integer.parseInt(strArr[1]);
                Node node = new Node(priority, data);
                heap.enqueue(node);
            }

        }



    }
}
