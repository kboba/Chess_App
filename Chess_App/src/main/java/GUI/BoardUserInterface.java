package GUI;

import Board.Board;
import Board.Square;
import Pieces.Piece;
import Pieces.PieceType;
import Pieces.PlayerColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BoardUserInterface extends JPanel implements MouseListener, MouseMotionListener {
    private final byte X_MOVE = 13;
    private final byte Y_MOVE = 3;
    private final byte BORDER_WIDTH = 1;
    final Color BLACK_SQUARES_COLOR = new Color(102, 51, 0);
    final Color WHITE_SQUARES_COLOR = new Color(255,204,153);
    final Color BOARD_COLOR = new Color(68, 28, 0);
    private final byte ROWS_AMOUNT = 8;
    private final byte COLUMNS_AMOUNT = 8;
    private final byte SQUARE_WIDTH = 64;
    private final byte SQUARE_HEIGHT = 64;
    private HashMap<String, Image> stringToImage;
    Board board;

    public BoardUserInterface(Board board) {
        this.board = board;
        stringToImage  = new HashMap<>();
        readImages();
    }

    @Override
    public void paint(Graphics g) {
        drawBorder(g);
        drawBoard(g);
        drawPieces(g, this);
    }

    /*
     * MouseListener implementation
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /*
     * MouseMotionListener implementation
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void drawBorder(Graphics g) {
        g.setColor(BOARD_COLOR);
        g.drawRect(X_MOVE, Y_MOVE, COLUMNS_AMOUNT*SQUARE_WIDTH + BORDER_WIDTH, ROWS_AMOUNT*SQUARE_HEIGHT + BORDER_WIDTH);
    }

    private void drawBoard(Graphics g) {
        boolean isWhite;
        for(int x = 0; x<COLUMNS_AMOUNT; x++){
            for(int y=0; y<ROWS_AMOUNT; y++){
                isWhite = (x+y)%2 == 0;

                if(isWhite){
                    g.setColor(WHITE_SQUARES_COLOR); // color white
                }
                else{
                    g.setColor(BLACK_SQUARES_COLOR); // black
                }

                g.fillRect(x*SQUARE_WIDTH+ X_MOVE + BORDER_WIDTH, y*SQUARE_HEIGHT+ Y_MOVE + BORDER_WIDTH, SQUARE_WIDTH, SQUARE_HEIGHT);
            }
        }
    }

    private void drawPieces(Graphics g, ImageObserver observer) {
        var boardSquares = board.getBoardSquares();
        String pieceInitials;
        for (int i=0; i<ROWS_AMOUNT; i++){
            for (int j = 0; j < COLUMNS_AMOUNT; j++) {
                if(boardSquares[i][j].getPiece() == null)
                    continue;
                var pieceOnSquare = boardSquares[i][j].getPiece();
                var pieceColor = pieceOnSquare.getPlayerColor();
                var type = pieceOnSquare.getType();
                pieceInitials = getPieceInitials(pieceColor, type);
                g.drawImage(stringToImage.get(pieceInitials), i*SQUARE_WIDTH+ X_MOVE-BORDER_WIDTH, j*SQUARE_HEIGHT+ Y_MOVE-BORDER_WIDTH, observer);
            }
        }
    }

    private String getPieceInitials(PlayerColor pieceColor, PieceType type) {
        String pieceInitials = pieceColor ==PlayerColor.WHITE ? "w" : "b";

        if(type ==PieceType.PAWN) {
            pieceInitials += "p";
        }
        else if(type ==PieceType.BISHOP) {
            pieceInitials += "B";
        }
        else if(type ==PieceType.KNIGHT) {
            pieceInitials += "N";
        }
        else if(type ==PieceType.ROCK) {
            pieceInitials += "R";
        }
        else if(type ==PieceType.QUEEN) {
            pieceInitials += "Q";
        }
        else if(type ==PieceType.KING) {
            pieceInitials += "K";
        }
        return pieceInitials;
    }

    private void readImages() {
        Image images[] = new Image[12];
        BufferedImage img= null;
        String pathName = "";
        for(int i=0; i<6; i++){
            // white:1  ; black:2
            for(int j=0; j<2; j++){
                pathName = "C:\\Users\\bobak\\CLOUD\\repos\\Chess_App\\Pieces\\"+(j+1)+(i+1)+".png"; // "(path)\(1;2)(1;6)"
                try {
                    img = ImageIO.read(new File(pathName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                images[i*2+j]=img.getSubimage(0, 0, 60, 60).getScaledInstance(SQUARE_WIDTH, SQUARE_HEIGHT, BufferedImage.SCALE_SMOOTH);
            }
        }

        mapImages(images);
    }

    private void mapImages(Image[] images){
        stringToImage.put("wp", images[0]);
        stringToImage.put("bp", images[1]);
        stringToImage.put("wB", images[2]);
        stringToImage.put("bB", images[3]);
        stringToImage.put("wN", images[4]);
        stringToImage.put("bN", images[5]);
        stringToImage.put("wR", images[6]);
        stringToImage.put("bR", images[7]);
        stringToImage.put("wQ", images[8]);
        stringToImage.put("bQ", images[9]);
        stringToImage.put("wK", images[10]);
        stringToImage.put("bK", images[11]);
    }

    public int getBoardWidth(){
        return COLUMNS_AMOUNT*SQUARE_WIDTH + 42;
    }

    public int getBoardHeight(){
        return ROWS_AMOUNT*SQUARE_HEIGHT + 54;
    }
}