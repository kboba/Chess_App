package Pieces;

import Board.Board;
import Board.Position;
import Board.Square;

public class Rock extends Piece {

    public Rock(PlayerColor playerColor, Position position) {
        super(playerColor, PieceType.ROCK, position);
    }

    @Override
    public boolean isMoveValid(Position newPosition, Board board) {
        var currentPositionX = getPosition().getX();
        var currentPositionY = getPosition().getY();
        var newPositionX = newPosition.getX();
        var newPositionY = newPosition.getY();
        var boardSquares = board.getBoardSquares();

        if(currentPositionX == newPositionX)
            return canMoveVertically(currentPositionX, currentPositionY, newPositionY, boardSquares);

        if(currentPositionY == newPositionY)
            return canMoveHorizontally(currentPositionX, currentPositionY, newPositionX, boardSquares);

        return false;
    }

    private boolean canMoveHorizontally(int currentPositionX, int currentPositionY, int newPositionX, Square[][] boardSquares) {
        if(currentPositionX < newPositionX)
            for(int xPosition = currentPositionX +1; xPosition < newPositionX; xPosition++){
                if(boardSquares[xPosition][currentPositionY].getPiece()!=null)
                    return false;
            }
        else if(currentPositionX > newPositionX)
            for(int xPosition = currentPositionX -1; xPosition > newPositionX; xPosition--){
                if(boardSquares[xPosition][currentPositionY].getPiece()!=null)
                    return false;
            }

        return true;
    }

    private boolean canMoveVertically(int currentPositionX, int currentPositionY, int newPositionY, Square[][] boardSquares) {
        if(currentPositionY < newPositionY)
            for(int yPosition = currentPositionY +1; yPosition < newPositionY; yPosition++){
                if(boardSquares[currentPositionX][yPosition].getPiece()!=null){
                    return false;
                }
            }
        else if(currentPositionY > newPositionY)
            for(int yPosition = currentPositionY -1; yPosition > newPositionY; yPosition--){
                if(boardSquares[currentPositionX][yPosition].getPiece()!=null){
                    return false;
                }
            }

        return true;
    }
}
