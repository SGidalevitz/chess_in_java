import java.util.HashMap;

public class Square {
    private int rank; // 0 to 7 from bottom to top
    private int file; // 0 to 7 from left to right
    public Square(int rank, int file) {
        this.rank = rank;
        this.file = file;
        
    }
    public Square(String pos) {
        HashMap<Character, Integer> posToPieceIDMap = getPosToPieceIDMap();
        this.rank = posToPieceIDMap.get(pos.charAt(0)) + 1;
        this.file = Integer.parseInt(pos.substring(1)) + 1;
    }
    public int getRank() {
        return rank;
    }
    public int getFile() {
        return file;
    }
    public int[] getCoords() {
        return new int[]{rank, file};
    }
    public HashMap<Character, Integer> getPosToPieceIDMap() {
        HashMap<Character, Integer> posToPieceIDMap = new HashMap<Character, Integer>();
        posToPieceIDMap.put('a', 1);
        posToPieceIDMap.put('b', 2);
        posToPieceIDMap.put('c', 3);
        posToPieceIDMap.put('d', 4);
        posToPieceIDMap.put('e', 5);
        posToPieceIDMap.put('f', 6);
        posToPieceIDMap.put('g', 7);
        posToPieceIDMap.put('h', 8);
        return posToPieceIDMap;
    }
}