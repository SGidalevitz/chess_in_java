import java.util.HashMap;
import java.util.ArrayList;
public class Board {
    private Piece[][] board;
    private int toMove; // 0 for white, 1 for black
    private String castlingRights;
    private Square enPassantTargetSquare;
    private int halfMoveClock;
    private int fullMoveNumber;
    //Construct a board with a given FEN code, which is the standard for a chess position
    public Board(String fen) {
        this.board = readFen(fen);
        String[] partsOfFen = fen.split(" ");
        if (partsOfFen[1].equals("w")) {
            this.toMove = 0;
        }
        else {
            this.toMove = 1;
        }
        this.castlingRights = partsOfFen[2];
        if (partsOfFen[3].equals("-")) {
            this.enPassantTargetSquare = null;
        }
        else {
            this.enPassantTargetSquare = new Square(partsOfFen[3]);
        }
        this.halfMoveClock = Integer.parseInt(partsOfFen[4]);
        this.fullMoveNumber = Integer.parseInt(partsOfFen[5]);
    }
    //If we already have the board info, such as if we have to simulate moves, then we can construct the class with given information usually extracted from FEN
    public Board(Piece[][] board, int toMove, String castlingRights, Square enPassantTargetSquare, int halfMoveClock, int fullMove) {
        this.board = board;
        this.toMove = toMove;
        this.castlingRights = castlingRights;
        this.enPassantTargetSquare = enPassantTargetSquare;
        this.halfMoveClock = halfMoveClock;
        this.fullMoveNumber = fullMove;
    }
    public Piece[][] readFen(String fen) {
        HashMap<Character, Integer> CharToPieceIDMap = getCharToPieceIDMap();
        String[] partsOfFen = fen.split(" ");
        String[] ranks = partsOfFen[0].split("/");
        Piece[][] board = new Piece[8][8];
        for (int i = 0; i < ranks.length; i++) {
            String rank = ranks[i];
            int fileCount = 0;
            for (int j = 0; j < rank.length(); j++) {
                char c = rank.charAt(j);
                //In FEN format, a number in the board representation indicates the number of empty squares, so basically we just iterate through it here
                if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8') {
                    for (int k = 0; k < Integer.parseInt("" + c); k++) {
                        //type: 0 because the piece is empty
                        board[7 - i][fileCount] = new Piece(new Square(7 - i, fileCount), 0, this);
                        fileCount++;
                    }
                }
                //if it is not a number, so a piece, then we just put it as a piece
                else {
                    //Get the piece type from the pre-made map
                    board[7 - i][fileCount] = new Piece(new Square(7 - i, fileCount), CharToPieceIDMap.get(c), this);
                    fileCount++;
                }
                
            }
        }
        return board;
    }
    public HashMap<Character, Integer> getCharToPieceIDMap() {
        HashMap<Character, Integer> charToPieceIDMap = new HashMap<Character, Integer>();
        charToPieceIDMap.put('P', 1);
        charToPieceIDMap.put('N', 2);
        charToPieceIDMap.put('B', 3);
        charToPieceIDMap.put('R', 4);
        charToPieceIDMap.put('Q', 5);
        charToPieceIDMap.put('K', 6);
        charToPieceIDMap.put('p', 7);
        charToPieceIDMap.put('n', 8);
        charToPieceIDMap.put('b', 9);
        charToPieceIDMap.put('r', 10);
        charToPieceIDMap.put('q', 11);
        charToPieceIDMap.put('k', 12);
        return charToPieceIDMap;
    }
    //For printing out the board when a move is played, or for troubleshooting
    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("" + board[7 - i][j].getPieceAsChar() + " ");
            }
            System.out.println();
        }
    }
    public Piece[][] getBoard() {
        return board;
    }
    
    public Piece getPiece(Square pos) {
        return board[pos.getFile()][pos.getRank()];
    }
    //To make the move, we just set the types of the pieces in the move
    public void makeMove(Move move) {
        board[move.getDestinationFile()][move.getDestinationRank()].setType(move.getType());
        board[move.getOriginFile()][move.getOriginRank()].setType(0);
    }
    //Get the valid moves in a given position, so the move the player is trying to play can be compared to this list
    public ArrayList<Move> getValidMoves(int color) {
        ArrayList<Move> moves = new ArrayList<Move>();
        int[] flags = {0, 0, 0, 0, 0, 0};
        //Iterate through every piece in the board
        for (Piece[] rank : board) {
            for (Piece piece : rank) {
                int type = piece.getType();
                //Ignore empty pieces (of type 0)
                if (type == 0) {
                    continue;
                }
                //Here, we merge all the possible moves for each piece, by getting the moves of each piece for each of their respective types
                else if (type == color * 6 + 1) {
                    moves = merge(moves, getMovesForPawn(piece));
                }
                else if (type == color * 6 + 2) {
                }
                else if (type == color * 6 + 3) {
                }
                else if (type == color * 6 + 4) {
                }
                else if (type == color * 6 + 5) {
                }
                else if (type == color * 6 + 6) {
                }
            }
        }
        return moves;
    }
    //Merge two arraylists
    public ArrayList<Move> merge(ArrayList<Move> a, ArrayList<Move> b) {
        b.forEach(move -> a.add(move));
        return a;

    }

    public ArrayList<Move> getMovesForPawn(Piece piece) {
        int pieceRank = piece.getRank();
        int pieceFile = piece.getFile();
        int pieceColor = (piece.getType() <= 6) ? 0 : 1;
        ArrayList<Move> movesForPiece = new ArrayList<Move>();
        //White pawns and black pawns move in different directions, so we have to factor in the piece type when looking for pawn moves
        if (pieceColor == 0) {
            Move singlePawnPush = new Move(new Square(pieceRank, pieceFile), new Square(pieceRank + 1, pieceFile), this);
            if (singlePawnPush.isValid(this, pieceColor)) {
                movesForPiece.add(singlePawnPush);
            }
            Move doublePawnPush = new Move(new Square(pieceRank, pieceFile), new Square(pieceRank + 2, pieceFile), this);
            if (doublePawnPush.isValid(this, pieceColor)) {
                movesForPiece.add(doublePawnPush);
            }
        }
        else {
            Move singlePawnPush = new Move(new Square(pieceRank, pieceFile), new Square(pieceRank - 1, pieceFile), this);
            if (singlePawnPush.isValid(this, pieceColor)) {
                movesForPiece.add(singlePawnPush);
            }
            Move doublePawnPush = new Move(new Square(pieceRank, pieceFile), new Square(pieceRank - 2, pieceFile), this);
            if (doublePawnPush.isValid(this, pieceColor)) {
                movesForPiece.add(doublePawnPush);
            }
        }
        return movesForPiece;
    }
    
}
