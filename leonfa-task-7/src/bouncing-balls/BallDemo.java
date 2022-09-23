import java.awt.Color;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the
 * Canvas class.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    // Exercise 5.62 and 5.64
    /**
     * Simulate two bouncing balls
     */
    public void bounce(int balls)
    {
        Random rand = new Random();

        int ground = 400;   // position of the ground line
        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall[] ballsList = new BouncingBall[balls];
        for (int i = 0; i < balls; i++){
            int randY = rand.nextInt(250);  // randomly in top half of canvas with dimension 600 x 500
            int randX = rand.nextInt(600);  // randomly wherever on the canvas ( x- axis)
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            Color randColor = new Color(r, g, b);
            ballsList[i] = new BouncingBall(randX, randY, 20, randColor, ground, myCanvas);
        }

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
           boolean newFinish = true;

            for (int i = 0; i < balls; i++){
                ballsList[i].move();

                if(ballsList[i].getXPosition() <= 550) { // stops when every ball is outside of Line.
                    newFinish = false;
                }
            }
            finished = newFinish;
            // stop once ball has travelled a certain distance on x axis
        }
    }
    // Exercise 5.65 and 5.66
    public void boxBounce(int balls){

        myCanvas.setVisible(true);
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(100, 100, 400, 100); // roof
        myCanvas.drawLine(400, 100, 400, 400); // right wall
        myCanvas.drawLine(400, 400, 100, 400); // ground
        myCanvas.drawLine(100, 400, 100, 100); // left wall

        Random rand = new Random();

        BoxBall[] ballsList = new BoxBall[balls];
        for (int i = 0; i < balls; i++){
            int randY = rand.nextInt(100)+100;  // randomly in the box (with some height)
            int randX = rand.nextInt(400)+100;  // _______ || ________
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            int randySpeed = rand.nextInt(50)+40;
            int randxSpeed = rand.nextInt(20)+5;
            Color randColor = new Color(r, g, b);
            ballsList[i] = new BoxBall(randX, randY, 20, randColor, 100, 400, 400, 100, myCanvas, randxSpeed, randySpeed);
        }
        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            for (int i = 0; i < balls; i++){
                ballsList[i].move();
            }

        }
    }

    public static void main(String[] args) {
        BallDemo bd = new BallDemo();
        //bd.bounce(3);
        bd.boxBounce(20);
    }
}
