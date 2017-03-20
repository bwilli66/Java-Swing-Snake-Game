import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

/**
 * Created by BradWilliams on 12/7/16.
 */
public class GUI extends JFrame{

    public HighScore highScore;

    public GUI(int _width, int _height, HighScore highScore){
        setResizable(false);
        setSize(_width,_height);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0,0,0));
        setLayout(null);
        setFocusable(true);
        setVisible(true);
        setLayout(null);

        this.highScore = highScore;

        this.addWindowListener(new WindowScoreListener(this));

        this.addKeyListener(new ArrowKeylistener());
    }

    public class WindowScoreListener implements java.awt.event.WindowListener{

        public GUI parent;

        public WindowScoreListener(GUI parent){
            this.parent = parent;
        }

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {
            //System.out.println("window closed");
            parent.highScore.writeHighScore();
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
    }

    public class ArrowKeylistener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {

            if(e.getKeyCode() == 84){
                Psychedelic.trip();
            }

            //left
            if(e.getKeyCode() == 37){
                //System.out.println("left");

                if(Main.xSpeed != 1) {
                    Main.xSpeed = -1;
                }

                Main.ySpeed = 0;
            }
            //up
            else if(e.getKeyCode() == 38){
                //System.out.println("up");
                Main.xSpeed = 0;

                if(Main.ySpeed != 1) {
                    Main.ySpeed = -1;
                }
            }
            //right
            else if(e.getKeyCode() == 39){
                //System.out.println("right");

                if(Main.xSpeed != -1) {
                    Main.xSpeed = 1;
                }

                Main.ySpeed = 0;
            }
            //down
            else if(e.getKeyCode() == 40){
                //System.out.println("down");
                Main.xSpeed = 0;

                if(Main.ySpeed != -1) {
                    Main.ySpeed = 1;
                }
            }
        }
    }

    @Override
    public void paint(Graphics g){

        Random r = new Random();
        super.paint(g);
//        g.setColor(new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256)));
//        g.fillRect(0, 0, getWidth(), getHeight());

        int blackWhite = r.nextInt(2) * 255;

        if(Psychedelic.isTripping()) {
            this.getContentPane().setBackground(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            //this.getContentPane().setBackground(new Color(blackWhite, blackWhite, blackWhite));
        }
        else{
            this.getContentPane().setBackground(new Color(0,0,0));
        }
    }
}
