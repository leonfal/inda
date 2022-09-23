import java.awt.*;
import java.awt.geom.*;
// Exercise 5.65
public class BoxBall
{
    private static final int GRAVITY = 3;  // effect of gravity

    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int roof;
    private final int rightWall;      // x position of right wall
    private final int ground;      // y postion of ground
    private final int leftWall;      // x postion of left wall
    private Canvas canvas;
    private int ySpeed;                // initial downward speed
    private int xSpeed;

    /**
     * Constructor for objects of class BouncingBall
     *
     * @param xPos  the horizontal coordinate of the ball
     * @param yPos  the vertical coordinate of the ball
     * @param ballDiameter  the diameter (in pixels) of the ball
     * @param ballColor  the color of the ball
     * @param groundPos  the position of the ground (where the wall will bounce)
     * @param drawingCanvas  the canvas to draw this ball on
     */
    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor, int roofPos, int rightWallPos, int groundPos, int leftWallPos, Canvas drawingCanvas, int xAxisSpeed, int yAxisSpeed)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        roof = roofPos;
        rightWall = rightWallPos;
        ground = groundPos;
        leftWall = leftWallPos;
        canvas = drawingCanvas;
        xSpeed = xAxisSpeed;
        ySpeed = yAxisSpeed;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        ySpeed += GRAVITY;
        yPosition += ySpeed;
        xPosition += xSpeed;

        // check if it has hit any wall or ground or roof
        if (yPosition <= (roof)) {
            yPosition = (int)(roof);
            ySpeed = -ySpeed - ballDegradation; // opposite of grounds physics
        }
        
        if (xPosition >= (rightWall - diameter)) {
            xPosition = (int)(rightWall - diameter);
            xSpeed = -xSpeed + ballDegradation; 
        }

        if (yPosition >= (ground - diameter) && ySpeed > 0) {
            yPosition = (int)(ground - diameter);
            ySpeed = -ySpeed + ballDegradation; 
        }

        if(xPosition <= (leftWall)) {
            xPosition = (int)(leftWall);
            xSpeed = -xSpeed - ballDegradation; // opposite of rightwalls phyisics
        }

        // draw again at new position
        draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
