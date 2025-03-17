import javax.swing.*;

public class App {
    public static void main(String[] args){
        
        //Dimensions of background image
        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappy bird");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null); //Place window to center of screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //so user can terminate program

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird); // adding the canvas to the frame
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);
        

    }
}
