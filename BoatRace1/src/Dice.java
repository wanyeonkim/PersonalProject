import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Dice {
    private int currentValue;

    private String Diceloc ="GUI/Dice/dice.png";

    public String getDiceloc() {
        return Diceloc;
    }

    Dice(){
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
    public void rollDice(){
        Random random = new Random();
        this.currentValue = random.nextInt(6 - 1+1) + 1;
    }

}
