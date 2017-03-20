import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by BradWilliams on 2/10/17.
 */
public class SnakeSegment extends JPanel {

    public SnakeSegment(int side) {
        setSize(side,side);
        setVisible(true);

    }

    @Override
    public void paint(Graphics g){

        Random r = new Random();
        super.paintComponent(g);

        if(Psychedelic.isTripping()) {
            g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

}
