package breakingout;

import acm.graphics.GRect;

/**
 *
 * @author Thomas Ransoms
 */
public class brick extends GRect{

    public brick(double width, double height) {
        this(0.0, 0.0, width, height);
    }
    
    public brick(double xpos, double ypos, double width, double height) {
        super(xpos, ypos, width, height);
        setFilled(true);
    }
    
    
}

