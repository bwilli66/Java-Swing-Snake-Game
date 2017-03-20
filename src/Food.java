import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by BradWilliams on 2/10/17.
 */
public class Food extends JPanel {

    public Food(int side) {

        setSize(side,side);
        setVisible(true);

    }


    @Override
    public void setLocation(int x, int y){
        super.setLocation(x,y);

        //this.repaint();
    }

    @Override
    public void paint(Graphics g){

        Random r = new Random();
        super.paintComponent(g);

        if(Psychedelic.isTripping()) {
            g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
        else{
            g.setColor(new Color(0,255,0));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

}
