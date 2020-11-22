import java.util.ArrayList;
import java.util.Random;

public class Current {
    ////GUI

    //stage 1
    private final String Stage1Current ="GUI/compo/current1.png";
    //stage 1 end

    //stage 2 (stage 1 but added jumping pad instead of current)

    private final String Stage2Current ="GUI/compo/jumpingpad.png";
    //stage 2 end

    //stage 3 (shuffle light and black background)
    private final String[] Stage3Current = {"GUI/compo/jumpingpad.png",
            "GUI/compo/current1.png"};
    //stage 3 end

    //stage 4 (spooky version )
    private final String[] Stage4Current = {"GUI/compo/currentspooky.png",
            "GUI/compo/jumpingpadspooky.png"};
    //stage 4 end

    //special round
    //stage 5 (shuffle everything)

    private final String[] Stage5Current = {"GUI/compo/jumpingpad.png",
            "GUI/compo/jumpingpadspooky.png",
            "GUI/compo/current1.png",
            "GUI/compo/current1.png"};

    ///END GUI

    private String type;
    private int strength;
    private int max;
    private int min;
    private String path;
    private ArrayList<String> list = new ArrayList<String>();


    //////////////GUI
    private String CurrentGUI="";

    public String getCurrentGUI() {
        return CurrentGUI;
    }

    public void setCurrentGUI(String currentGUI) {
        CurrentGUI = currentGUI;
    }

    ///////////////////GUI END

    public Current() {
        this.type = "";
        this.strength = 0;
    }

    public String getType() {
        Weather weather = new Weather(randomGen1(0,32),randomGen1(0,100));
        weather.DetWeather();
        setType(weather.getWeather());
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

    //determine the Strength
    public void DetStrength(String weather){
        weather = weather.toLowerCase();
//        System.out.println(weather);
        switch (weather){
            case "sunny":
                // generating random value from 0 -5
                max = 5;
                min = 1;
                this.strength = randomGen1(min,max);
//                this.strength = rand.nextInt(max - min+1) + min;
                break;

            case "rainy":
                // generating random value from 6-10
                max = 10;
                min = 6;
                this.strength = randomGen1(min,max);
                break;
            default:
                System.out.println("wrong value!");
        }
    }



    private void setPath(String path) {
        this.path = path;
    }

    public int randomGen1(int startpos, int endpos){
        Random number = new Random();
        return number.nextInt(endpos - startpos+1) + startpos;
    }
    public void settingCurent(){
        switch(Main.getStageCounter()){
            //this is stage 1
            case 2:
                //logic can be added here to determine background.
                setCurrentGUI(Stage1Current);
                list.clear();
                break;
            case 3:
                setCurrentGUI(Stage2Current);
                break;
            case 4:
                setCurrentGUI(SizeGen(Stage3Current));
                list.clear();
                break;
            case 5:
                setCurrentGUI(SizeGen(Stage4Current));
                list.clear();
                break;
            case 6:
                setCurrentGUI(SizeGen(Stage5Current));
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
