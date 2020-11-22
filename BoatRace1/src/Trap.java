import java.util.ArrayList;
import java.util.Random;

public class Trap {
    /// image path

    //stage 1
    private final String Stage1Trap ="GUI/compo/bomb1.png";
    //stage 1 end

    //stage 2 (stage 1 but added jumping pad instead of current)
    private final String Stage2Trap ="GUI/compo/bomb1.png";
    //stage 2 end

    //stage 3 (shuffle light and black background)
    private final String[] Stage3Trap = {"GUI/compo/bomb1.png",
            "GUI/compo/bomb2.png"};
    //stage 3 end


    //stage 4 (spooky version )
    private final String[] Stage4Trap = {"GUI/compo/bombpumpkin.png",
            "GUI/compo/skullbomb.png"};
    //stage 4 end

    //special round

    //stage 5 (shuffle everything)
    private final String[] Stage5Trap = {"GUI/compo/bomb2.png",
            "GUI/compo/bomb1.png",
            "GUI/compo/bombpumpkin.png",
            "GUI/compo/skullbomb.png"};
    ///image path end


    //    calculation will randomized
    private String type;
    private int strength; //ranging from 0-5
    private ArrayList<String> list = new ArrayList<String>();
    private Random randomMovement = new Random();

    ////////////////////gui
    private String TrapGUI="";

    public String getTrapGUI() {
        return TrapGUI;
    }

    public void setTrapGUI(String trapGUI) {
        TrapGUI = trapGUI;
    }
    ////////////////////gui
    public Trap() {
        this.type = "";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void StrengthOfTrap(){
//        fix this dont need to repeat the conditions when it is the same for all.
//        remove the repeated

        switch (this.type){
            case "S":
                this.strength = randomMovement.nextInt(-1 - (-5+1)) + -5;
                break;
            case "B":
                this.strength = randomMovement.nextInt(-6 - (-10+1)) + -10;
                break;
        }
    }

    public void PowerGen(){

        list.add("B");
//        list.add("b");
        list.add("S");
//        list.add("s");
//        System.out.println(list.get(rnd));
        this.type = list.get(new Random().nextInt(list.size()));
    }

    public void settingtrap(){
        switch(Main.getStageCounter()){
            //this is stage 1
            case 2:
                setTrapGUI(Stage1Trap);
                list.clear();
                break;
            case 3:
                setTrapGUI(Stage2Trap);
                break;
            case 4:
                setTrapGUI(SizeGen(Stage3Trap));
                list.clear();
                break;
            case 5:
                setTrapGUI(SizeGen(Stage4Trap));
                list.clear();
                break;
            case 6:
                setTrapGUI(SizeGen(Stage5Trap));
                list.clear();
                break;

        }
        //call getTrapGUI for gui Sring value

    }
    public String SizeGen(String[] arr){
        for (int i=0;i<arr.length;i++){
            list.add(arr[i]);
        }
        return list.get(new Random().nextInt(list.size()));
    }




}
