import java.awt.*;
import java.util.ArrayList;

public class Main {
    private static int StageCounter =-1;

    public static final int SCREEN_WIDTH = 770;
    private static int SCREEN_HEIGHT;

    public static final int BOAT_WIDTH = 120; // x Axis
    public static final int BOAT_HEIGHT = 140; // y axis

    public static  int YAxis = (int)(Main.getScreenHeight()*0.83); //
    public static final int P1XAxis = 120; //
    public static final int P2XAxis = 520; //

    public static final int BaseScore = 30;
    public static final int CurrentValue = 30;
    public static final int  TrapValue= -60;


    //////////////////////
    private static Boolean p1Flagger=false;
    private static Boolean p2Flagger=false;

    public static Boolean getP1Flagger() {
        return p1Flagger;
    }

    public static void setP1Flagger(Boolean p1Flagger) {
        Main.p1Flagger = p1Flagger;
    }

    public static Boolean getP2Flagger() {
        return p2Flagger;
    }

    public static void setP2Flagger(Boolean p2Flagger) {
        Main.p2Flagger = p2Flagger;
    }
    //////////////////////


    public static ArrayList<String> topUserName = new ArrayList<String>();
    public static ArrayList<Integer> topUserScore = new ArrayList<Integer>();
    public static ArrayList<String> topUserDate = new ArrayList<String >();


    ///////////////////////////////////////////
    public static String Player1Name;
    public static String Player2Name;

    public static String getPlayer1Name() {
        return Player1Name;
    }

    public static void setPlayer1Name(String player1Name) {
        Player1Name = player1Name;
    }

    public static String getPlayer2Name() {
        return Player2Name;
    }

    public static void setPlayer2Name(String player2Name) {
        Player2Name = player2Name;
    }
    //////////////////////////////////////////////////

    public static void main(String[] args) {


	// write your code here
        new StageMerge();
//        new userChoice().promptScreen();
//        new DiceGUI().showGUI();
    }


    //counter
    public static int getStageCounter() {
        return StageCounter;
    }

    public static void setStageCounter(int stageCounter) {
        StageCounter = stageCounter;
    }
    //counter

    public static int getScreenHeight() {
        setScreenHeight(getHeight());
        return SCREEN_HEIGHT;
    }

    private static void setScreenHeight(int screenHeight) {
        SCREEN_HEIGHT = screenHeight;
    }

    public static int getHeight(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize.height;
    }
}
