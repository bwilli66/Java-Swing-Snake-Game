/**
 * Created by BradWilliams on 2/11/17.
 */
public final class Psychedelic {

    private static boolean tripping = false;


    private Psychedelic(){

    }


    public static void trip(){
        tripping = !tripping;
    }

    public static boolean isTripping(){
        return tripping;
    }

}
