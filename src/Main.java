import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by BradWilliams on 12/7/16.
 */
public class Main {
    static int x = 0;
    static int y = 0;


    static int xSpeed = 1;
    static int ySpeed = 0;

    static int PANEL_SIZE = 20;

    static int scl = PANEL_SIZE;

    public static void main(String[] args){

        HighScore highScore = new HighScore();

        GUI frame = new GUI(800,800,highScore);

        JLabel highScoreLabel = new JLabel("High Score: ");
        JLabel scoreLabel = new JLabel("Score: ");
        JLabel highScoreContent = new JLabel(String.valueOf(highScore.getHighScore()));
        JLabel scoreContent = new JLabel(String.valueOf(highScore.getScore()));


        highScoreLabel.setForeground(new Color(255,255,255));
        scoreLabel.setForeground(new Color(255,255,255));
        highScoreContent.setForeground(new Color(255,255,255));
        scoreContent.setForeground(new Color(255,255,255));

        highScoreLabel.setBounds(20,5,200,30);
        highScoreContent.setBounds(100,5,200,30);

        scoreLabel.setBounds(150,5,200,30);
        scoreContent.setBounds(200,5,200,30);


        frame.getContentPane().add(highScoreLabel);
        frame.getContentPane().add(highScoreContent);
        frame.getContentPane().add(scoreLabel);
        frame.getContentPane().add(scoreContent);


        highScoreLabel.setVisible(true);
        scoreLabel.setVisible(true);
        highScoreContent.setVisible(true);
        scoreContent.setVisible(true);

        if(highScore.highScoreCorrupted){
            JLabel highScoreCorrupted = new JLabel("High score data file has been corrupted, high score data will be reset upon closing application.");
            highScoreCorrupted.setForeground(new Color(255,255,255));
            highScoreCorrupted.setBounds(20,25,1000,30);
            frame.getContentPane().add(highScoreCorrupted);
            highScoreCorrupted.setVisible(true);
        }

        frame.validate();

        ArrayList<SnakeSegment> snake = new ArrayList<>();
        ArrayList<Point> segmentLocations = new ArrayList<>();

        Food food = new Food(PANEL_SIZE);
        Random random = new Random();
        snake.add(new SnakeSegment(PANEL_SIZE));
        segmentLocations.add(new Point());

        boolean started = false;
        int snakeNumber = 1;
        long timeStamp = System.currentTimeMillis();


        while(true){
            if(System.currentTimeMillis() - timeStamp >= 70){

                if(!started){

                    frame.getContentPane().add(snake.get(0));
                    frame.getContentPane().add(food);

                    food.setLocation(random.nextInt(frame.getWidth()/PANEL_SIZE)*PANEL_SIZE,random.nextInt(frame.getWidth()/PANEL_SIZE - 20)*PANEL_SIZE);

                    started = !started;

                }

                timeStamp = System.currentTimeMillis();

                //update directions (x and y coordinates of snake)
                x += xSpeed * scl;
                y += ySpeed * scl;

                //if snake hits edge
                if((x == frame.getWidth() || x == -PANEL_SIZE || y == frame.getHeight() - 20 || y == -PANEL_SIZE) && snake.size() > 1 ){
                    for(SnakeSegment s: snake)
                        frame.getContentPane().remove(s);

                    snake.subList(0, snake.size()-1).clear();
                    segmentLocations.subList(0, segmentLocations.size()-1).clear();

                    x = 0;
                    y = 0;
                    xSpeed = 1;
                    ySpeed = 0;

                    snakeNumber = 1;

                    started = !started;

                    highScore.updateHighScore();
                    highScore.resetScore();

                    highScoreContent.setText(String.valueOf(highScore.getHighScore()));
                    scoreContent.setText("0");
                }

                //if snake hits edge at length 1
                else if((x == frame.getWidth() || x == -PANEL_SIZE || y == frame.getHeight() || y == -PANEL_SIZE) && snake.size() <= 1 ){
                    x = 0;
                    y = 0;
                    xSpeed = 1;
                    ySpeed = 0;
                }

                //if head snake hits food
                if(snake.get(0).getX() == food.getX() && snake.get(0).getY() == food.getY()){

                    //HighScore.score++
                    //System.out.println("FOOOOOOD");

                    //move food to random spot
                    food.setLocation(random.nextInt(frame.getWidth()/PANEL_SIZE)*PANEL_SIZE,random.nextInt(frame.getWidth()/PANEL_SIZE - 20)*PANEL_SIZE);

                    //add snake segment
                    snake.add(new SnakeSegment(PANEL_SIZE));
                    segmentLocations.add(new Point());

                    frame.getContentPane().add(snake.get(snakeNumber++));

                    //update score
                    highScore.incrementScore();
                    scoreContent.setText(String.valueOf(highScore.getScore()));
                }



                //update snake segment locations
                for(int i = segmentLocations.size() - 1; i > 0; --i){
                    //System.out.println("i = " + i + " " + segmentLocations.get(i));
                    segmentLocations.get(i).setLocation(segmentLocations.get(i - 1).getLocation());

                }

                //place snake segments at their locations
                for(int i = 0; i < snake.size(); ++i){
                    snake.get(i).setLocation(segmentLocations.get(i));

                }

                //set location of head of snake
                snake.get(0).setLocation(x , y);
                segmentLocations.get(0).setLocation(x,y);
                //System.out.println("0 = " + segmentLocations.get(0));

                //check to see if snake has hit itself, only check from 4th segment on
                for(int i = 4; i < segmentLocations.size(); ++i){

                    //System.out.print(segmentLocations.get(0).getX() + ", " + segmentLocations.get(0).getY());
                    //System.out.println(" = " + segmentLocations.get(i).getX() + ", " + segmentLocations.get(i).getY());

                    if(segmentLocations.get(0).getX() == segmentLocations.get(i).getX() && segmentLocations.get(0).getY() == segmentLocations.get(i).getY()){

                        //System.out.println("xCount = " + xCount + " segmentLocationsSize = " + segmentLocations.size());
                        //System.out.println("yCount = " + yCount + " segmentLocationsSize = " + segmentLocations.size());

                        for (SnakeSegment s : snake)
                            frame.getContentPane().remove(s);

                        snake.subList(0, snake.size() - 1).clear();
                        segmentLocations.subList(0, segmentLocations.size() - 1).clear();

                        x = 0;
                        y = 0;
                        xSpeed = 1;
                        ySpeed = 0;

                        snakeNumber = 1;

                        started = !started;

                        highScore.updateHighScore();
                        highScore.resetScore();
                    }

                }

                frame.repaint();

            }
        }

    }
}
