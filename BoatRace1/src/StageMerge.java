import java.util.ArrayList;
import java.util.Random;

public class StageMerge {
    // This is the section where all the stages merge up together
    // so in this section there will be some logics that determines the stage.
    // The initial starting point will be "stage1".
    // according to the option that the user select at the end of each stage will det. the flow
    // stage2 - stage5 will have a random weather. But for stage1 the user can choose weather
    // the weather in stage1 will be picked according to the values that user give.
    // temperature & humidity.


    private Login login;

    private int totplayer1=0;
    private int totplayer2=0;

    private DataStored dataStored;

    StageMerge(){
        //stage 5
        switch (Main.getStageCounter()){
            case -1:
                dataStored = new DataStored();
                dataStored.topName();
            case 0:
                new StartPage().settingBackGround();
                break;
            case 1:

                // setting this just to double confirm that it wont break the condition
//                Main.setPlayer1Name(null);
//                Main.setPlayer2Name(null);

                Main.setP1Flagger(false);
                Main.setP2Flagger(false);
                login = new Login();
                login.settingImage();
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                new Stage();
                break;
            case 7:
                new userChoice().promptScreen();
                new DataStored().summingThemAll(Main.getPlayer1Name(),totplayer1);
                new DataStored().summingThemAll(Main.getPlayer2Name(),totplayer2);
                System.out.println("updated to database");
                break;
            default:
                System.out.println("thank you for playing the game ");
                System.exit(0);
                break;
        }
    }

}
