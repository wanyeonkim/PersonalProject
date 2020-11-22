import java.util.ArrayList;
import java.util.Random;

public class Weather {

    //stage 1
    private final String Stage1BackGround = "GUI/backgrounds/backgroundLight.png";
    //stage 1 end

    //stage 2 (stage 1 but added jumping pad instead of current)
    private final String Stage2BackGround = "GUI/backgrounds/backgroundLight.png";

    //stage 2 end

    //stage 3 (shuffle light and black background)
    private final String[] Stage3BackGround = {"GUI/backgrounds/backgroundLight.png",
            "GUI/backgrounds/backgroundBlack.png"};

    //stage 3 end

    //stage 4 (spooky version )
    private final String Stage4BackGround = "GUI/backgrounds/riverspooky.png";

    //stage 4 end

    //special round
    //stage 5 (shuffle everything)
    private final String[] Stage5BackGround = {"GUI/backgrounds/backgroundBlack.png",
            "GUI/backgrounds/riverspooky.png",
            "GUI/backgrounds/backgroundLight.png"};

    private int temperature;
    private int humidity;
    private String weather;
    private ArrayList<String> list = new ArrayList<String>();


    /////////////////////////
    private String backgroundGUI="";

    public String getBackgroundGUI() {
        return backgroundGUI;
    }

    public void setBackgroundGUI(String backgroundGUI) {
        this.backgroundGUI = backgroundGUI;
    }
    ///////////////////////////////
    public Weather() {
        //later on in the game we can make this randomized.
        //by giving some values in the list and pick a random position.
        //by doing the index method we can find the random value that we want
        //the random number generator can be made using the Random fucntion in java lib
        this.temperature = 32;
        this.humidity = 90;
        this.weather = "";
    }

    public Weather(int temperature, int humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.weather = "";
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void DetWeather(){

        if ((this.temperature>=23 &&this.temperature<=32)||this.temperature <23 ) {
            if (this.humidity >= 0 && this.humidity <= 50){
                this.weather = "sunny";
            }else if (this.humidity >= 51 && this.humidity <= 100){
                this.weather = "rainy";
            }else{
                System.out.println("wrong humidity value enter value between 0-100");
            }
        }
        else {
            System.out.println("The maximum vtemperature accepted will be 32");
        }
    }

    public void settingBackground(){
        switch(Main.getStageCounter()){
            //this is stage 1
            case 2:
                setBackgroundGUI(Stage1BackGround);
                list.clear();
                break;
            case 3:
                setBackgroundGUI(Stage2BackGround);
                break;
            case 4:
                setBackgroundGUI(SizeGen(Stage3BackGround));
                list.clear();
                break;
            case 5:
                setBackgroundGUI(Stage4BackGround);
                list.clear();
                break;
            case 6:
                setBackgroundGUI(SizeGen(Stage5BackGround));
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
// gve out screen background pic according to weather.
// make a setter getter.



//this will generate a waether according to the temperature and humidity.
//the nthe value will be linked to current class acting as the input there
//there will be 2 values that needed to be differentiate. whic hare sunny and rainy.

