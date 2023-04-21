public class Node {
    int data;
    Node up, down, left, right;
    int supposedCol, currentCol;

    public Node(int data, int supposedCol) {
        this.data = data;
        this.supposedCol = supposedCol;
    }
}
