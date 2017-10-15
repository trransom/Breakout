package breakingout;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This program creates the classic arcade game 'BreakOut'
 * @author Thomas Ransom
 */
public class Breakingout extends GraphicsProgram {
    
    private GLabel livesleft;
    private int lives = 5;
    private int numBricks = 130;
    private ball ball;
    private paddle paddle;
    private final double BRICK_WIDTH = 50;
    private final double BRICK_HEIGHT = 20;
    private final double BRICK_SPACE = 5;
    private final Color [] BCOLORS = {Color.red, Color.red, Color.orange, Color.orange, 
        Color.yellow, Color.yellow, Color.green, Color.green, Color.cyan, Color.cyan};
    private final double TOP_HEIGHT = 10;
    
    /**
     * Initializes the paddle, the ball, and draws the bricks.
     */
    
    @Override
    public void init() {
        addKeyListeners();
        addMouseListeners();
        paddle = new paddle(getWidth()/2.0, getHeight()- 80, 60, 10);
        ball = new ball(50, 50, 13);
        add(ball, 20, 400);
        add(paddle);
        //drawTestBrick();
        drawBricks();
        
       
        Font d = new Font("SansSerif", Font.BOLD, 20);
        livesleft = new GLabel("Lives left: " + lives);
        livesleft.setFont(d);
        add(livesleft, 630, 490);
        
    }
    /**
     * Draws the bricks.
     */
    
    private void drawTestBrick(){
       brick brick = new brick(500, 100);
       add(brick, 50, 50);
    }
    private void drawBricks(){
        int bricksInRow = (int)(getWidth()/(BRICK_WIDTH+BRICK_SPACE))-1;
        double leftPadding = (getWidth()-(bricksInRow*(BRICK_WIDTH+BRICK_SPACE)))/2.0;
         for(int row=0;row<10;++row){
            for(int col=0;col<bricksInRow;++col){
              brick brick = new brick(
                leftPadding+(BRICK_WIDTH+BRICK_SPACE)*col,
                TOP_HEIGHT+(BRICK_HEIGHT+BRICK_SPACE)*row,
                BRICK_WIDTH, BRICK_HEIGHT);
              brick.setFillColor(BCOLORS[row]);
              add(brick);
        }
      }
    }
    
    /**
     * Moves the paddle with the mouse.
     * @param e 
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        //move the paddle
        double xpos = e.getX()-paddle.getWidth()/2.0;
        if(xpos>=0 && xpos+paddle.getWidth()<=getWidth()){
        paddle.setLocation(xpos, paddle.getY());
        }
    }
    
    public int getLives() {
        return lives;
    }
    
    /**
     * Moves the ball and makes it bounce off of the right, left, and top
     * walls along with the paddle.
     */
    @Override
    public void run() {
        boolean play;
        play = true;
        while (play) {
            ball.move();
            if(ball.getX()<0){
                ball.setLocation(0,ball.getY());
                ball.setDx(ball.getDx()*-1);
            }else if(ball.getX()+ball.getWidth()>=getWidth()){
                ball.setLocation(getWidth()-ball.getWidth(),ball.getY());
                ball.setDx(ball.getDx()*-1);
            }else if(ball.getY()<0){
                ball.setLocation(ball.getX(),0);
                ball.setDy(ball.getDy()*-1);
            }else if(ball.getY()>getHeight()){
               remove(ball);
               add(ball, 20, 400);
               lives--;
               remove(livesleft);
               Font d = new Font("SansSerif", Font.BOLD, 20);
               livesleft = new GLabel("Lives left: " + lives);
               livesleft.setFont(d);
               add(livesleft, 630, 490);
               restart();
            }
            GObject obj = this.getElementAt(ball.getX(), ball.getY());
            if(obj!=null)hit(obj);
            obj = this.getElementAt(ball.getX()+ball.getWidth(),ball.getY());
            if(obj!=null)hit(obj);
            obj = this.getElementAt(ball.getX(),ball.getY()+ball.getHeight());
            if(obj!=null)hit(obj);
            obj = this.getElementAt(ball.getX()+ball.getWidth(),ball.getY()+ball.getHeight());
            if(obj!=null)hit(obj);
            if(obj instanceof paddle){
                ball.setDy(ball.getDy()*-1);
            }
            obj = this.getElementAt(ball.getX()+ball.getWidth(),ball.getY()+ball.getHeight());
            if(obj instanceof paddle){
                ball.setDy(ball.getDy()*-1);
            }
            pause(3);
        }
    }
    
    
  
    /**
     * Makes the bricks disappear upon contact from the ball.
     * @param obj 
     */   
    
    private void hit(GObject obj){
        if (obj instanceof paddle){
            ball.setLocation(ball.getX(), paddle.getY()-ball.getWidth()-1);
            ball.setDy(ball.getDy()*-1);
        } else if (obj instanceof brick) {
            remove(obj);
            ball.setDy(ball.getDy()*-1);
            numBricks--;
            if(numBricks==0){
                winLabel();
            }
        }
      
    }
    
    /**
     * Sets the ball in motion when the user presses the spacebar.
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE) {
        ball.setMoving(!ball.isMoving());
      }   
    }
    
    public void restart() {
        if(getLives()==0){
            loseLabel();
            remove(ball);
            int i = 0;
            while(i==0){
                remove(ball);
                remove(livesleft);
            }
        } else {
        int timer = 3;
        for(int i=0; timer>=1; timer--) {
            Font d = new Font("SansSerif", Font.BOLD, 100);
            GLabel TIMER = new GLabel(""+timer);
            TIMER.setFont(d);
            add(TIMER, getWidth()/2, 375);
            pause(1000);
            remove(TIMER);
        }
        }
       
       
    }
    
    public void loseLabel() {
            lives = 0;
            Font d = new Font("SansSerif", Font.BOLD, 100);
            GLabel youlose = new GLabel("You lose!");
            youlose.setFont(d);
            add(youlose, (getWidth()/2)-(youlose.getWidth()/2), getHeight()/1.3);
            int i = 0;
            while(i==0) {
                remove(livesleft);
                remove(ball);
            }
    }
            
    public void winLabel() {
            Font d = new Font("SansSerif", Font.BOLD, 100);
            GLabel youwin = new GLabel("You win!");
            youwin.setFont(d);
            add(youwin, (getWidth()/2)-(youwin.getWidth()/2), getHeight()/1.3);
            int i = 0;
            while(i==0) {
                remove(livesleft);
                remove(ball);
            }
    }
            

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Breakingout().start();
    }
    
}
