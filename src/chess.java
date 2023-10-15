public class chess {
    public static void main(String[] args) {
        Board board = new Board("r2qkbnr/p4ppp/2p1p3/2PpP3/6b1/5N1P/PPP2PP1/RNBQK2R b KQkq - 0 8");
        board.printBoard();
        
        board.makeMove(new Move(new Square(0, 1), new Square(0, 3), board));
        System.out.println("-----------------");
        board.printBoard();

    }
    public static void gameCycle() {
        System.out.println("How you doing!?");
    }
    public static void gameCycle(String fen) {
    }
    
}