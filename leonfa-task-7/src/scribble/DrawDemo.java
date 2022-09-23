import java.awt.Color;
import java.util.Random;

/**
 * Class DrawDemo - provides some short demonstrations showing how to use the 
 * Pen class to create various drawings.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class DrawDemo
{
    private Canvas myCanvas;
    private Random random;

    /**
     * Prepare the drawing demo. Create a fresh canvas and make it visible.
     */
    public DrawDemo()
    {
        myCanvas = new Canvas("Drawing Demo", 500, 400);
        random = new Random();
    }


    /**
     * Draw a square on the screen.
     */
    public void drawSquare()
    {
        Pen pen = new Pen(320, 260, myCanvas);
        pen.setColor(Color.BLUE);

        square(pen);
    }

    // Exercise 5.57
    /**
     * Draw a Green triangle
     */
    public void drawTriangle(){
        Pen pen = new Pen(200, 200, myCanvas);
        pen.setColor(Color.GREEN);
        triangle(pen);
    }

    // Exericse 5.58

    /**
     * Draw a pentagon
     */
    public void drawPentagon(){
        Pen pen = new Pen(200, 100, myCanvas);
        pentagon(pen);
    }

    // Exercise 5.59
    /**
     * Draws a regular polygon
     * 
     */
    public void drawPolygon(int n){
        Pen pen = new Pen(200, 200, myCanvas);
        polygon(pen, n);
    }

    // Exercise 5.60
    /**
     * Draw a spiral similar to the picture.
     */
    public void drawSpiral(){
        Pen pen = new Pen(200, 200, myCanvas);
        spiral(pen);
    }
    /**
     * Draw a wheel made of many squares.
     */
    public void drawWheel()
    {
        Pen pen = new Pen(250, 200, myCanvas);
        pen.setColor(Color.RED);

        for (int i=0; i<36; i++) {
            square(pen);
            pen.turn(10);
        }
    }

    // part of Exercise 5.57
    /**
     * Draw a triangle in the pen's choosed color at the pens location
     * @param pen
     */
    private void triangle(Pen pen){
        for (int i = 0; i < 3; i++){
            pen.move(100);
            pen.turn(120);
        }
    }

    // part of Exercise 5.58
    /**
     * Draw a pentagon in the pen's color and location
     */
    private void pentagon(Pen pen){
        for (int i = 0; i < 5; i++){
            pen.move(100);
            pen.turn(72);
        }
    }

    // Exercise 5.60
    /**
     * Draws a sprial in a square format.
     * @param pen
     */
    private void spiral(Pen pen){
        for (int i = 0; i < 100; i++){
            pen.move(100-i);
            pen.turn(90);
        }
    }

    // part of Exercise 5.59
    /**
     * Draws a regular polygon with given amount of corners.
     * @param pen 
     * @param corners how many corners the polygon will have.
     */
    private void polygon(Pen pen, int corners){
        int turn = 180-(180*(corners-2))/corners; // calculates the degree the pen should turn.
        
        for (int i = 0; i < corners; i++){
            pen.move(500/(corners)); // makes the shapes equally large.
            pen.turn(turn);
        }
    }
    /**
     * Draw a square in the pen's color at the pen's location.
     */
    private void square(Pen pen)
    {
        for (int i=0; i<4; i++) {
            pen.move(100);
            pen.turn(90);
        }
    }

    /**
     * Draw some random squiggles on the screen, in random colors.
     */
    public void colorScribble()
    {
        Pen pen = new Pen(250, 200, myCanvas);

        for (int i=0; i<10; i++) {
            // pick a random color
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);
            pen.setColor(new Color(red, green, blue));
            
            pen.randomSquiggle();
        }
    }
    
    /**
     * Clear the screen.
     */
    public void clear()
    {
        myCanvas.erase();
    }

    public static void main(String[] args) {
        DrawDemo dd = new DrawDemo();
        dd.drawPolygon(4);
    }
}
