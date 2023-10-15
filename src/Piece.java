import java.util.HashMap;
public class Piece {
    private Square pos;
    private int type; // empty = 0, white (pawn, knight, bishop, rook, queen, king) = 1-6, black (pawn, knight, bishop, rook, queen, king) = 7-12
    private Board parentBoard;
    public Piece(Square pos, int type, Board board) {
        this.parentBoard = board;
        this.pos = pos;
        this.type = type;
    }
    public int getRank() {
        return pos.getRank();
    }
    public int getFile() {
        return pos.getFile();
    }
    public int getType() {
        return type;
    }
    public Board getParentBoard() {
        return parentBoard;
    }
    public char getPieceAsChar() {
        return getPieceIDToCharMap().get(type);
    }
    public void setType(int type) {
        this.type = type;
    }
    //Map for printing out piece abbreviations
    public HashMap<Integer, Character> getPieceIDToCharMap() {
        HashMap<Integer, Character> pieceIDToCharMap = new HashMap<Integer, Character>();
        pieceIDToCharMap.put(0, ' ');
        pieceIDToCharMap.put(1, 'P');
        pieceIDToCharMap.put(2, 'N');
        pieceIDToCharMap.put(3, 'B');
        pieceIDToCharMap.put(4, 'R');
        pieceIDToCharMap.put(5, 'Q');
        pieceIDToCharMap.put(6, 'K');
        pieceIDToCharMap.put(7, 'p');
        pieceIDToCharMap.put(8, 'n');
        pieceIDToCharMap.put(9, 'b');
        pieceIDToCharMap.put(10, 'r');
        pieceIDToCharMap.put(11, 'q');
        pieceIDToCharMap.put(12, 'k');
        return pieceIDToCharMap;
    }

    
}
