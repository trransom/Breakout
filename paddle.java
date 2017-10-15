package breakingout;

import acm.graphics.GRect;
import static java.awt.Color.red;

/**
 *
 * @author Thomas Ransom
 */
public class paddle extends GRect{
    
    public paddle(double width, double height) {
        this(0, 0, width, height);
    }
    
    public paddle(double x, double y, double width, double height){
        super(x, y, width, height);
        setFilled(true);
        setFillColor(red);
    }
}

