public class MLinkedList {

    Node head;
    int rowBound, colBound;
    int rowSize = 0;


    public MLinkedList(int rowBound, int colBound) {
        this.rowBound = rowBound;
        this.colBound = colBound;
    }

    public void dropTile(int data, int supposedCol) {
        if (supposedCol < 0 || supposedCol >= colBound) {
            throw new IllegalArgumentException("Invalid column");
        }

        Node newNode = new Node(data, supposedCol);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            // Move to the desired column, stop if there is space between current column and desired column
            int colIndex = 0;
            for (int i = 0; (current.right != null) && (i < supposedCol); i++, colIndex++) {
                current = current.right;
            }
            // If current is at the bottom row and its upper node is null
            if (current.up == null && current.right == null) {
                // Place the tile to current tile's left or right
                if (newNode.supposedCol < current.supposedCol) { // Left
                    current.left.right = newNode;
                    newNode.left = current.left;
                    newNode.right = current;
                    current.left = newNode;

                    newNode.currentCol = colIndex;
                    current.currentCol++;
                } else { // Right
                    // If right tile is empty, simply place the tile
                    current.right = newNode;
                    newNode.left = current;
                    newNode.currentCol = colIndex + 1;
                }
            } else {
                // Move up until there is a free space above current node
                while (current.up != null) {
                    current = current.up;
                    rowSize++;
                }
                // Drop the tile
                current.up = newNode;
                newNode.down = current;
                current.up.currentCol = colIndex;
                mergeTiles(current, newNode);
            }
        }
    }

    public void mergeTiles(Node current, Node newNode) {
        // If the values are equal, double the value of current node
        if (current.data == newNode.data) {
            current.data *= 2;
            current.up = null;
            // Merge down if current and below nodes have equal values
            while (current.down != null) {
                if (current.down.data == current.data) {
                    current = current.down;
                    current.data *= 2;
                    current.up = null; // Remove the upper node after merging
                }
            }
        }
    }

}
