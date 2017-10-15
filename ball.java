package breakingout;

import acm.graphics.GOval;
import static java.awt.Color.orange;
import static java.awt.Color.white;

/**
 *
 * @author Thomas Ransom
 */
public class ball extends GOval{
    
    private double dx;
    private double dy;
    private boolean moving = false;
    
    public ball(double width) {
        this(0, 0, width);
    }
    
    public ball(double x, double y, double width) {
        super(x, y, width, width);
        dx = 1.0;
        dy = 1.0;
        setFilled(true);
        setFillColor(orange);
        
    }
    
    public void move() {
        if (moving) {
            move(dx, dy);
        }
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    
    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
    
    
    
}
