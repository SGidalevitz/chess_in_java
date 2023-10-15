public class Move {
    private Square originSquare;
    private Square destinationSquare;
    private Board parentBoard;
    public Move(Square origin, Square destination, Board parentBoard) {
        this.originSquare = origin;
        this.destinationSquare = destination;
        this.parentBoard = parentBoard;
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
    public int getType() {
        return parentBoard.getPiece(originSquare).getType();
    }
    public int getDestinationRank() {
        return destinationSquare.getRank();
    }
    public int getDestinationFile() {
        return destinationSquare.getFile();
    }
    //This method for move validity, but is very basic for now
    public boolean isValid(Board board, int color) {
        Piece destinationPiece = board.getPiece(destinationSquare);
        int destinationPieceType = destinationPiece.getType();
        //If the destination square is empty
        if (destinationPieceType == 0){
            return true;
        }
        //If the piece on the destination square is of opposite color, meaning a capture is made
        else if ((destinationPieceType <= 6 && color == 2) ||(destinationPieceType > 6 && color == 1)) {
            return true;
        }
        //Otherwise, there is a piece of the same color on the destination square, making the move invalid
        else {
            return false;
        }

    }

    /*
    public Board simulateMove(Board board) {
        Board newBoard = board.clone();
        Piece placeholder = new Piece(destinationSquare, type, newBoard); // placehold destination piece
        newBoard.makeMove(this);
    }
    */

    
}
