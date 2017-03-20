
import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

/**
 * Created by BradWilliams on 2/11/17.
 */
public class HighScore {

    private int highScore;
    private int score;
    public boolean highScoreCorrupted;

    public HighScore (){
        this.highScore = getScoreFromFile();
    }

    public void incrementScore(){
        this.score++;
    }

    public void updateHighScore(){
        if(this.score > this.highScore){
            this.highScore = this.score;
        }
    }

    public int getHighScore(){
        return this.highScore;
    }

    public int getScore(){
        return this.score;
    }

    public void resetScore(){
        this.score = 0;
    }

    private int getScoreFromFile() {

        StringBuilder result = new StringBuilder("");

        String home = System.getProperty("user.home");

        Path path = Paths.get(home, "highScore.txt");
        boolean fileExists = Files.exists(path);

        if(fileExists) {
            File file = new File(path.toString());

            try (Scanner scanner = new Scanner(file)) {

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    result.append(line);
                }

                scanner.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            int intReturn;

            try {
                intReturn = Integer.parseInt(result.toString().replace("\n", ""));
            }
            catch (NumberFormatException e){
                this.highScoreCorrupted = true;
                intReturn = 0;
            }


            return intReturn;
        }
        else {
            return 0;
        }

    }

    public void writeHighScore() {

        String home = System.getProperty("user.home");

        Path path = Paths.get(home, "highScore.txt");

        File file = new File(path.toString());

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            String content = String.valueOf(this.highScore);

            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(content);

            System.out.println("highScore.txt written");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
    }

    public static void main(String[] args){

        HighScore h = new HighScore();

        System.out.println(h.getHighScore());

        h.highScore = 30;

        h.writeHighScore();


    }
}
