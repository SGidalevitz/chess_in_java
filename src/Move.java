public class Move {
    private Square originSquare;
    private Square destinationSquare;
    private int type;
    public Move(Square origin, Square destination, int type) {
        this.originSquare = origin;
        this.destinationSquare = destination;
    }
    public Square getOriginSquare() {
        return originSquare;
    }
    public Square getDestinationSquare() {
        return destinationSquare;
    }
    public int getOriginRank() {
        return originSquare.getRank();
    }
    public int getOriginFile() {
        return originSquare.getFile();
    }
    public int getDestinationRank() {
        return destinationSquare.getRank();
    }
    public int getDestinationFile() {
        return destinationSquare.getFile();
    }
    public boolean isValid(Board board, int color) {
        Piece destinationPiece = board.getPiece(destinationSquare);
        int destinationPieceType = destinationPiece.getType();
        if (destinationPieceType == 0){
            return true;
        }
        else if ((destinationPieceType <= 6 && color == 2) ||(destinationPieceType > 6 && color == 1)) {
            return true;
        }
        else {
            return false;
        }

    }

    public int getType() {
        return this.type;
    }
    /*
    public Board simulateMove(Board board) {
        Board newBoard = board.clone();
        Piece placeholder = new Piece(destinationSquare, type, newBoard); // placehold destination piece
        newBoard.makeMove(this);
    }
    */

    
}
