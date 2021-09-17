package GUI;


import Board.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BoardDisplay implements Displayable {
    private final byte ROWS_AMOUNT = 8;
    private final byte COLUMNS_AMOUNT = 8;
    private final byte SQUARE_WIDTH = 64;
    private final byte SQUARE_HEIGHT = 64;


    private JFrame jFrame;
    private JPanel jPanel;
    private HashMap<String, Image> stringToImage = new HashMap<>();

    private Board board;
    
    public BoardDisplay(Board board) {
        this.board = board;
    }

    public void initialize(){
        jFrame = new JFrame("Chess Game");
        readImages();
        setFrame();
    }

    private void setFrame() {
        jFrame.setBounds(0, 0, COLUMNS_AMOUNT*SQUARE_WIDTH +42, ROWS_AMOUNT*SQUARE_HEIGHT +54);
        jPanel = new BoardGraphics();
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

    @Override
    public void display() {

    }


}
