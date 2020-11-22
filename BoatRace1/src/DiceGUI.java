/*
        ==========================================
         Title:  Boat Race
         Author: Kim Yeon Wan
         Date:   17 Oct 2020
        ==========================================

 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.SwingUtilities;
import java.util.List;
import java.util.Timer;
import java.util.*;


public class DiceGUI {
    public Timer timer = new Timer();
    private int secondspassed = 0;

    public void setSecondspassed(int secondspassed) {
        this.secondspassed = secondspassed;
    }

    private JLabel Dice1;
    private JLabel Dice2;
    private JLabel Dice3;
    private JLabel Dice4;
    private JLabel Dice5;
    private JLabel Dice6;

    private TimerTask task;

    ////////////DICE NUMBER SECTION//////////
    private int diceNumber;

    public int getDiceNumber() {
        return diceNumber;
    }

    public void setDiceNumber(int diceNumber) {
        this.diceNumber = diceNumber;
    }

    public int getSecondspassed() {
        return secondspassed;
    }
    ///////DICE NUMBER SECTION END /////////////

    ImageIcon dice1 = new ImageIcon("GUI/Dice/dice1.png");
    ImageIcon dice2 = new ImageIcon("GUI/Dice/dice2.png");
    ImageIcon dice3 = new ImageIcon("GUI/Dice/dice3.png");
    ImageIcon dice4 = new ImageIcon("GUI/Dice/dice4.png");
    ImageIcon dice5 = new ImageIcon("GUI/Dice/dice5.png");
    ImageIcon dice6 = new ImageIcon("GUI/Dice/dice6.png");

    List<String> diceList =Arrays.asList("dice1","dice3","dice2","dice6","dice4","dice6");


    JFrame frame = new JFrame();



    private int pickedValue;


    DiceGUI(){

    }

    public void showGUI(){
//        setSecondspassed(0);
        //// dice picture///
        //dice 1
        Dice1 = new JLabel();
        Dice1.setIcon(dice1);
        Dice1.setBounds(0,0,200,200);
        //dice2
        Dice2 = new JLabel();
        Dice2.setIcon(dice2);
        Dice2.setBounds(0,0,200,200);
        //dice 3
        Dice3 = new JLabel();
        Dice3.setIcon(dice3);
        Dice3.setBounds(0,0,200,200);
        //dice4
        Dice4 = new JLabel();
        Dice4.setIcon(dice4);
        Dice4.setBounds(0,0,200,200);
        //dice 5
        Dice5 = new JLabel();
        Dice5.setIcon(dice5);
        Dice5.setBounds(0,0,200,200);
        //dice 6
        Dice6 = new JLabel();
        Dice6.setIcon(dice6);
        Dice6.setBounds(0,0,200,200);

        JLabel[] array = new JLabel[6];
        array[0] = Dice1;
        array[1] = Dice2;
        array[2] = Dice3;
        array[3] = Dice4;
        array[4] = Dice5;
        array[5] = Dice6;

        shuffleArray(array);


        ////dice picture end ///



        ///icon section
        ImageIcon image = new ImageIcon("GUI/windowIcon/BoatIcon.png");
        frame.setIconImage(image.getImage()); // change icon of frame
        frame.setTitle("BOAT RACE");


        ///icon section end
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(220,240);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        task= new TimerTask(){

            public void run(){
                //change this section to switch.
                if (getSecondspassed() == 0){
                    frame.revalidate();
                    frame.repaint();
                    frame.add(array[secondspassed]);
                    setSecondspassed(getSecondspassed()+1);

                }

                else if (getSecondspassed()<5){
                    frame.remove(array[secondspassed-1]);
                    frame.revalidate();
                    frame.repaint();
                    frame.add(array[secondspassed]);
                    setSecondspassed(getSecondspassed()+1);
                }
                else if (getSecondspassed() == 5){
                    frame.remove(array[secondspassed-1]);
                    frame.revalidate();
                    frame.repaint();
                    frame.add(array[secondspassed]);

                    //extracting number from String.
                    String num = array[secondspassed].getIcon() + "";

//                    diceNumber = Integer.parseInt(num.replaceAll("[^0-9]", ""));
                    //this wont work.
                    setDiceNumber(Integer.parseInt(num.replaceAll("[^0-9]", "")));
//                    setDiceNumber(2);
                    setSecondspassed(getSecondspassed()+1);
                }else if (getSecondspassed() == 6){
//                    task.cancel();
//                    timer.cancel();
                    setSecondspassed(0);
                    frame.dispose();

//                    secondspassed =0;

                }
            }
        };
//        timer.cancel();
        timer.scheduleAtFixedRate(task,500,650);


        //hard coded.....
        String num = array[array.length-1].getIcon()+"";
        setDiceNumber(Integer.parseInt(num.replaceAll("[^0-9]", "")));
    }
    //shuffle
    public void shuffleArray(JLabel[] array){
        for (int i=array.length-1;i>0;i--){
            int j = (int) Math.floor(Math.random() * (i + 1));
            JLabel temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    ////////////////////////////////////////////////////////////
}
