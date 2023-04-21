public class DropNumberGame {
    static final int rowBound = 7, colBound = 5;
    MLinkedList list;

    public DropNumberGame() {
        this.list = new MLinkedList(rowBound,colBound);
        this.list.head = null;
    }

    public void printGrid() {
        Node current = list.head;
        int maxRow = 0;
        while(current.up != null){
            current = current.up;
            maxRow++;
        }
        for (int i = maxRow; i < rowBound; i++) {

        }

        while (current != null) {
            Node rowCurrent = current;
            while (rowCurrent != null) {
                System.out.print(rowCurrent.data + " ");
                rowCurrent = rowCurrent.right;
            }
            System.out.println();
            current = current.up;
        }
    }
    public void isGameOver(int rowSize){
        // Check if game is over
        if (rowSize == rowBound - 1) {
            System.out.println("Game Over :(");
            System.exit(1);
        }
    }


    public static void main(String[] args) {
        // Sequence of numbers and their columns
        int[] valueSequence = {2, 2, 4, 2, 4, 2, 4, 8, 8, 32, 2, 64, 16, 64, 32, 16, 16, 32, 64, 8, 4, 2, 2, 2, 64, 32, 16, 8, 8, 4, 8};
        int[] colSequence = {0, 3, 1, 2, 4, 1, 4, 0, 0, 1, 2, 2, 3, 1, 2, 0, 4, 2, 1, 3, 3, 3, 3, 1, 2, 2, 2, 2, 2, 1, 1};
        DropNumberGame game = new DropNumberGame();
        // Drop each tile according to the sequences
        for (int i = 0; i < valueSequence.length; i++) {
            int num = valueSequence[i];
            int col = colSequence[i];
            System.out.println("Number: "+num+", Column: "+col+"\n");
            game.list.dropTile(num,col);
            game.printGrid();
            System.out.println("-----------------");
            game.isGameOver(game.list.rowSize);
        }
    }
}
