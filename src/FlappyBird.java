import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // Going to store all the pipes
import java.util.Random; // to place pipes at random positions
import javax.swing.*;

public class FlappyBird extends JPanel{
// Adding JPanel to use for our canvas (to draw our game)

    int boardWidth = 360;
    int boardHeight = 640;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight)); //create canvas
        setBackground(Color.blue);
    }

    
}
