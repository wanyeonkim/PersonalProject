import java.util.ArrayList;
import java.util.Random;

public class Boat {

    // getting dice value

    public Dice dice= new Dice();
    public Trap trap = new Trap();
    public Current current = new Current();


    ///getting dice vlaue
    private int DiceRolled;

    public int getDiceRolled() {
        dice.rollDice();

        setDiceRolled(dice.getCurrentValue());

        return DiceRolled;
    }

    public void setDiceRolled(int diceRolled) {
        DiceRolled = diceRolled;
    }
    //// getting dice value  END

    //////ARRAYLIST

    private ArrayList<Integer> trapPosition = new ArrayList<Integer>();
    private ArrayList<Integer> currentPosition = new ArrayList<Integer>();

    public ArrayList<Integer> getTrapPosition() {
        return trapPosition;
    }

    public void setTrapPosition(ArrayList<Integer> trapPosition) {
        this.trapPosition = trapPosition;
    }

    public ArrayList<Integer> getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(ArrayList<Integer> currentPosition) {
        this.currentPosition = currentPosition;
    }

    //////


    /// trap and current y  position

    private int Ypos;

    public int getYpos() {
        return Ypos;
    }

    public void setYpos(int ypos) {
        Ypos = ypos;
    }

    ///trap and current y  position END

    //score

    private int Score = 0;

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    // SCORE ENd

    ///boatType  GUI
    private String boatType="";

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(int boatType) {
        switch (boatType) {
            case 1:
                this.boatType = "GUI/playerboat/boat1.png";
                break;
            case 2:
                this.boatType = "GUI/playerboat/boat2.png";
                break;
        }
    }

    //boattype GUI end

    /// x pos of a boat
    private int boatXposition;

    public int getBoatXposition() {
        return boatXposition;
    }

    public void setBoatXposition(int boatXpostion) {
        switch (boatXpostion){
            case 1:
                this.boatXposition = 120;
                break;
            case 2:
                this.boatXposition = 520;
                break;
        }
    }
    ///x pos end

    //boat current position

    private int BoatYPos = Main.YAxis;

    public int getBoatYPos() {
        return BoatYPos;
    }

    public void setBoatYPos(int boatYPos) {
        BoatYPos = boatYPos;
    }

    // boat end

    Boat(){
        // take this as a player
        // will have the current trap value

        // since each player will have different set of trap and current value.
        // score will be updated here using setter getter
        // dice rolled value using setter and getter
        // call dice here

        /*
        components.currentTrapSetter(3);
        setTrapPosition(components.getTArray());
        setCurrentPosition(components.getCArray());

//        System.out.println(getTrapPosition());
//        System.out.println(components.getTArray().size());

        for (int i=0;i<components.getTArray().size();i++){
            System.out.println(components.getTArray().get(i));
        }

        for (int i=0;i < getTrapPosition().size();i++){
            getTrapPosition().get(i);
        }
        */
//        for (int i=0;i<trapPosition.size();i++){
//            System.out.println(trapPosition.get(i));
//        }
//        System.out.println();
//
//        for (int i=0;i<currentPosition.size();i++){
//            System.out.println(currentPosition.get(i));
//        }

        // top 5 user
        //
    }
    public int randomGen1(int startpos, int endpos){
        Random number = new Random();
        return number.nextInt(endpos - startpos+1) + startpos;
    }
//3
    public void currentTrapSetter(int numpo){
        int maxYVal = Main.YAxis;
        for (int i=0;i<numpo;i++){
            int cco = randomGen1(0,10);
            //numpo number of loop AKA number of component added in.
            int movementCon = randomGen1(100,250);
            int temp = 0;
            switch (cco%2){
                case 1:
                    if (maxYVal >= 150){
                        temp = maxYVal -= movementCon;
                        setYpos(temp);
                        trapPosition.add(getYpos());
                        break;
                    }
                    break;
                default:
                    if (maxYVal >= 150){
                        maxYVal -= movementCon;
                        temp = maxYVal;
                        setYpos(temp);
                        currentPosition.add(getYpos());
                        break;
                    }
                    break;
            }
        }
    }
}
