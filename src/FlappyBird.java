import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // going to store all the pipes
import java.util.Random; // to place pipes at random positions
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener{

    int boardWidth = 360;
    int boardHeight = 640;


    // Images
    Image backgroundImg;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    // Bird
    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird (Image img) {
            this.img = img;
        }
    }

    //game logic
    Bird bird;

    Timer gameLoop;




    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight)); //create canvas
        // setBackground(Color.blue);

        //load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage(); //getClass refers to flappy bird class
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        // bird
        bird = new Bird(birdImg);

        // game timer
        gameLoop = new Timer(1000/60, this); //60 frames per second (1000ms / 60 = 16.6 ms per frame)
        gameLoop.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        // draw background
        g.drawImage(backgroundImg, 0, 0, boardWidth, boardHeight, null);

        // bird
        g.drawImage(bird.img, bird.x, bird.y, bird.width, birdHeight, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // calls paintComponent
    }

    
}
