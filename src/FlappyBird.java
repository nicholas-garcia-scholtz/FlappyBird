import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // going to store all the pipes
import java.util.Random; // to place pipes at random positions
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

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

    //Pipes
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64; // Scaled by 1/6
    int pipeHeight = 512;

    class Pipe {
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed = false; // to check if our flappy bird has passed the pipe

        Pipe (Image img) {
            this.img = img;
        }
    }

    //game logic
    Bird bird;
    int velocityX = -4; // speed to move the pipes to the left (relatively makes it seem like the bird is moving right)
    int velocityY = 0; 
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();


    Timer gameLoop;
    Timer placePipesTimer;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight)); //create canvas
        // setBackground(Color.blue);
        setFocusable(true); // to make sure that our FlappyBird class (ie our JPanel) takes in our key events
        addKeyListener(this); // to check the 3 functions  from KeyListener when a key is pressed

        //load images
        backgroundImg = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage(); //getClass refers to flappy bird class
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();

        // bird
        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        // place pipes timer
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e){
                placePipes();
            }
        }); // places new pipe every 1.5 seconds
        placePipesTimer.start(); // starts timer

        // game timer
        gameLoop = new Timer(1000/60, this); //60 frames per second (1000ms / 60 = 16.6 ms per frame)
        gameLoop.start(); // starts the timer
    }

    public void placePipes() {
        // (0 -> 1) * pipeHeight /2 = (0 -> 256)
        // pipeY/4 = 128
        // 0 - 128 - (0 -> 256) -> Range from 1/4 to 3/4 of the pipe height

        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random() * pipeHeight/2);
        int openingSpace = boardHeight/4;

        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingSpace;
        pipes.add(bottomPipe);
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

        // pipes 
        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);
        }
    }

    public void move() {
        // bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0); // adds a ceiling to the movement

        // pipes
        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move(); 
        repaint(); // calls paintComponent
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            velocityY = -9;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    
}
