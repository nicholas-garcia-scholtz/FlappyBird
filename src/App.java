import javax.swing.*;

public class App {
    public static void main(String[] args){
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy bird");
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); //Place window to center of screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //so user can terminate program

    }
}
