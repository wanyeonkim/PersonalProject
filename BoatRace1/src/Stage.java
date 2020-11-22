import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

public class Stage extends JFrame implements ActionListener {

    //components for game play
    private int yMinLimit = (int)(Main.getScreenHeight()*0.1); // around 100
    private int yMaxLimit = (int)(Main.getScreenHeight()*0.1); //

    private  int dicedThrown;
    private int turns=1;

    public int getTurns() {
        return turns;
    }


    //dice String from newWindow java
    //position of the boat

    //JLabels
    private JLabel background;
    private JLabel BoatP1;
    private JLabel BoatP2;
    private JLabel finishLine;

    //JButton
    private JButton DiceButton;
    private JButton Continue;

    //boat
    private Boat player1 = new Boat();
    private Boat player2 = new Boat();

    //Trap
    private int totalJLs = 10;

    private JLabel[] TarrayJLsP1 = new JLabel[totalJLs];
    private ArrayList<JLabel>TrapArrayP1 = new ArrayList<JLabel>();

    private JLabel[] TarrayJLsP2 = new JLabel[totalJLs];
    private ArrayList<JLabel>TrapArrayP2 = new ArrayList<JLabel>();


    //Current
    private JLabel[] CarrayJLsP1 = new JLabel[totalJLs];
    private ArrayList<JLabel>CurrentArrayP1 = new ArrayList<JLabel>();

    private JLabel[] CarrayJLsP2 = new JLabel[totalJLs];
    private ArrayList<JLabel>CurrentArrayP2 = new ArrayList<JLabel>();


    private int numcompo=5;

    private Boolean forPlayers;

    private JLabel P1ScoreBoard;

    private JLabel P2ScoreBoard;


    private String fontColors = "#4a454d";

    private Weather weather = new Weather();

    private DiceGUI myDiceP1;
    private DiceGUI myDiceP2;


    Stage(){
        //Setting Frame
        setSize(Main.SCREEN_WIDTH,Main.getScreenHeight());
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("BOAT RACE - Stage" + (Main.getStageCounter()-1));

        //score board

        P1ScoreBoard = new JLabel();
        P1ScoreBoard.setText(Main.getPlayer1Name()+" Score: " + player1.getScore());
        P1ScoreBoard.setBounds(100,20,200,50);
        P1ScoreBoard.setForeground(Color.decode("#1c1d1f"));
        P1ScoreBoard.setFont(new Font("roboto",Font.BOLD, 15));
        P1ScoreBoard.setVisible(true);

        P2ScoreBoard = new JLabel();
        P2ScoreBoard.setText(Main.getPlayer2Name()+" Score: " + player2.getScore());
        P2ScoreBoard.setBounds(500,20,200,50);
        P2ScoreBoard.setForeground(Color.decode("#1c1d1f"));
        P2ScoreBoard.setFont(new Font("roboto",Font.BOLD, 15));
        P2ScoreBoard.setVisible(true);

        //

        //Setting continue button
        String name = "GUI/Dice/dice.png";
        ImageIcon MoveOn = new ImageIcon("GUI/windowIcon/continue.png");
        Continue = new JButton(MoveOn);
        Continue.setBounds(335,500,120,50);
        Continue.addActionListener(this);
        Continue.setVisible(true);

        //Setting Dice
        ImageIcon RollDice = new ImageIcon(name);
        DiceButton = new JButton(RollDice);
        DiceButton.setBounds(335,Main.YAxis,97,97);
        DiceButton.addActionListener(this);
        DiceButton.setVisible(true);
        DiceButton.setEnabled(true);

        //Setting Boat1
        player1.currentTrapSetter(3);
        player1.setBoatType(1);
        player1.setBoatXposition(1);
        ImageIcon Boat1 = new ImageIcon(player1.getBoatType());

        BoatP1 = new JLabel();
        BoatP1.setIcon(Boat1);
        BoatP1.setBounds(Main.P1XAxis,Main.YAxis,Main.BOAT_WIDTH,Main.BOAT_HEIGHT);
        add(BoatP1);

        //Setting Boat2

        player2.currentTrapSetter(3);
        player2.setBoatType(2);
        player2.setBoatXposition(2);
        ImageIcon Boat2 = new ImageIcon(player2.getBoatType());

        BoatP2 = new JLabel();
        BoatP2.setIcon(Boat2);
        BoatP2.setBounds(Main.P2XAxis,Main.YAxis,Main.BOAT_WIDTH,Main.BOAT_HEIGHT);
        add(BoatP2);


        //////////FINISH LINE//////////////
        ImageIcon finishline = new ImageIcon("GUI/backgrounds/finish.png");
        finishLine = new JLabel();
        finishLine.setIcon(finishline);
        finishLine.setBounds(30,yMinLimit,700,60);
        finishLine.setVisible(true);

        //Setting background
        weather.settingBackground();
        ImageIcon img = new ImageIcon(weather.getBackgroundGUI());

//        ImageIcon img = new ImageIcon("GUI/backgrounds/backgroundLight.png");
        background = new JLabel();
        background.setIcon(img);
        background.setBounds(0,0,Main.SCREEN_WIDTH,Main.getScreenHeight());
        add(background);
        background.add(finishLine);
        background.add(DiceButton);

        background.add(P1ScoreBoard);
        background.add(P2ScoreBoard);

        //repainting and revalidating after adding it to the background
        background.revalidate();
        background.repaint();

        background.setVisible(true);

        //Setting Window Icon
        ImageIcon image = new ImageIcon("GUI/windowIcon/BoatIcon.png");
        setIconImage(image.getImage()); // change icon of frame
        setVisible(true);


        TarrayJLsP1 = new JLabel[player1.getTrapPosition().size()];
        CarrayJLsP1 = new JLabel[player1.getCurrentPosition().size()];

        player1.trap.settingtrap();
        player1.current.settingCurent();

        setter(player1, player1.trap.getTrapGUI(),player1.current.getCurrentGUI(),
                player1.getBoatXposition(),TarrayJLsP1,CarrayJLsP1);

        player2.trap.settingtrap();
        player2.current.settingCurent();

        setter(player2,player2.trap.getTrapGUI(),player2.current.getCurrentGUI(),
                player2.getBoatXposition(),TarrayJLsP2,CarrayJLsP2);

        //removing null value by looping through the array and adding back to a new array
        //by checking condition.
        //converting from Array to Arraylist
        Remover(TarrayJLsP1,TrapArrayP1);
        Remover(CarrayJLsP1,CurrentArrayP1);
        Remover(TarrayJLsP2,TrapArrayP2);
        Remover(CarrayJLsP2,CurrentArrayP2);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        while (player1.getBoatYPos() > 100 && player2.getBoatYPos() > 100) {
            if (e.getSource().equals(DiceButton)) {
                switch (getTurns()){
                    case 1:
                        break;
                    case 2:
                        myDiceP1.frame.dispose();
                        break;
                    case 3:
                        myDiceP2.frame.dispose();
                        break;
                    default:
                        myDiceP1.frame.dispose();
                        myDiceP2.frame.dispose();
                        break;
                }





                if (getTurns() % 2 != 0) {
                    //animation
                    myDiceP1 = new DiceGUI();
                    myDiceP1.showGUI();
                    //actual value
                    player1.dice.rollDice();
                    dicedThrown = player1.dice.getCurrentValue();
                    player1.setBoatYPos(player1.getBoatYPos() - (dicedThrown * 10));

                    System.out.println(Main.getPlayer1Name()+" rolled:" + dicedThrown + "\n");

                    player1.setScore(player1.getScore() + Main.BaseScore);
                    P1ScoreBoard.setText(Main.getPlayer1Name()+" Score: " + player1.getScore());

                    BoatP1.setBounds(Main.P1XAxis, player1.getBoatYPos(), Main.BOAT_WIDTH, Main.BOAT_HEIGHT);
                    this.turns++;
                    background.revalidate();
                    background.repaint();


                    //the below second is the collision detection.
                    //the length of the boat is set at 140(y-axis).
                    //In this case x-axis can be ignored since it is fixed value.
                    //More explanation about this section will be added to the documentation.
                    if (!player1.getTrapPosition().isEmpty()) {
                        for (int i = 0; i < player1.getTrapPosition().size(); i++) {
                            if ((player1.getBoatYPos() >= (player1.getTrapPosition().get(i) - Main.BOAT_HEIGHT)) && (player1.getBoatYPos() <= (player1.getTrapPosition().get(i)))) {
                                System.out.println(Main.getPlayer1Name() + " stepped on trap\n");
                                player1.trap.PowerGen();
                                player1.trap.StrengthOfTrap();

                                player1.setBoatYPos(player1.getBoatYPos() - (player1.trap.getStrength() * 20));
                                player1.getTrapPosition().remove(i);

                                player1.setScore(player1.getScore() + Main.TrapValue);
                                P1ScoreBoard.setText(Main.getPlayer1Name()+" Score: " + player1.getScore());

                                BoatP1.setBounds(Main.P1XAxis, player1.getBoatYPos(), Main.BOAT_WIDTH, Main.BOAT_HEIGHT);
                                background.remove(TrapArrayP1.get(i));
                                TrapArrayP1.remove(i);

                                revalidate();
                                repaint();

                            }
                        }
                    }
                    if (!player1.getCurrentPosition().isEmpty()) {
                        //current section
                        for (int i = 0; i < player1.getCurrentPosition().size(); i++) {

                            if ((player1.getBoatYPos() >= (player1.getCurrentPosition().get(i) - Main.BOAT_HEIGHT)) && (player1.getBoatYPos() <= (player1.getCurrentPosition().get(i)))) {
                                System.out.println(Main.getPlayer1Name() + " stepped on current\n");
                                weather.DetWeather();
                                player1.current.DetStrength(weather.getWeather());

                                player1.setBoatYPos(player1.getBoatYPos() - (player1.current.getStrength() * 20));
                                player1.getCurrentPosition().remove(i);

                                player1.setScore(player1.getScore() + Main.CurrentValue);
                                P1ScoreBoard.setText(Main.getPlayer1Name()+" Score: " + player1.getScore());

                                BoatP1.setBounds(Main.P1XAxis, player1.getBoatYPos(), Main.BOAT_WIDTH, Main.BOAT_HEIGHT);
                                background.remove(CurrentArrayP1.get(i));
                                CurrentArrayP1.remove(i);

                                revalidate();
                                repaint();
                            }
                        }
                    }
                } else {
                    //animation
                    myDiceP2 = new DiceGUI();
                    myDiceP2.getDiceNumber();
                    myDiceP2.showGUI();
                    //actual value
                    player2.dice.rollDice();
                    dicedThrown = player2.dice.getCurrentValue();
                    player2.setBoatYPos(player2.getBoatYPos() - (dicedThrown * 10));

                    System.out.println(Main.getPlayer2Name()+" rolled:" + dicedThrown + "\n");

                    player2.setScore(player2.getScore() + Main.BaseScore);
                    P2ScoreBoard.setText(Main.getPlayer2Name()+" Score: " + player2.getScore());

                    BoatP2.setBounds(Main.P2XAxis, player2.getBoatYPos(), Main.BOAT_WIDTH, Main.BOAT_HEIGHT);
                    this.turns++;
                    background.revalidate();
                    background.repaint();


                    if (!player2.getTrapPosition().isEmpty()) {

                        for (int i = 0; i < player2.getTrapPosition().size(); i++) {

                            if ((player2.getBoatYPos() >= (player2.getTrapPosition().get(i) - Main.BOAT_HEIGHT)) && (player2.getBoatYPos() <= (player2.getTrapPosition().get(i)))) {
                                System.out.println(Main.getPlayer2Name() + " stepped on trap\n");

                                player2.trap.PowerGen();
                                player2.trap.StrengthOfTrap();

                                player2.setBoatYPos(player2.getBoatYPos() - (player2.trap.getStrength() * 20));
                                player2.getTrapPosition().remove(i);

                                player2.setScore(player2.getScore() + Main.TrapValue);
                                P2ScoreBoard.setText(Main.getPlayer2Name()+" Score: " + player2.getScore());

                                BoatP2.setBounds(Main.P2XAxis, player2.getBoatYPos(), Main.BOAT_WIDTH, Main.BOAT_HEIGHT);
                                background.remove(TrapArrayP2.get(i));
                                TrapArrayP2.remove(i);

                                background.revalidate();
                                background.repaint();

                            }
                        }
                    }
                    if (!player2.getCurrentPosition().isEmpty()) {
                        //current section
                        for (int i = 0; i < player2.getCurrentPosition().size(); i++) {

                            if ((player2.getBoatYPos() >= (player2.getCurrentPosition().get(i) - Main.BOAT_HEIGHT)) && (player2.getBoatYPos() <= (player2.getCurrentPosition().get(i)))) {
                                System.out.println(Main.getPlayer2Name() + " stepped on current\n");

                                weather.DetWeather();
                                player2.current.DetStrength(weather.getWeather());

                                player2.setBoatYPos(player2.getBoatYPos() - (player1.current.getStrength() * 20));
                                player2.getCurrentPosition().remove(i);

                                player2.setScore(player2.getScore() + Main.CurrentValue);
                                P2ScoreBoard.setText(Main.getPlayer2Name()+" Score: " + player2.getScore());

                                BoatP2.setBounds(Main.P2XAxis, player2.getBoatYPos(), Main.BOAT_WIDTH, Main.BOAT_HEIGHT);
                                background.remove(CurrentArrayP2.get(i));
                                CurrentArrayP2.remove(i);

                                background.revalidate();
                                background.repaint();
                            }
                        }
                    }
                }
                break;
            }
        }

            //continuing section linking to other window
        if (player1.getBoatYPos() <= 100 || player2.getBoatYPos() <= 100){
            myDiceP1.frame.dispose();
            myDiceP2.frame.dispose();
            if (player1.getScore() > player2.getScore()){
                System.out.println("player 1 won");
                new DataStored().updateScore(Main.getPlayer1Name(),player1.getScore(),Main.getStageCounter(),forPlayers);
                new DataStored().updateScore(Main.getPlayer2Name(),player2.getScore(),Main.getStageCounter(),forPlayers);

                background.remove(DiceButton);
                background.revalidate();
                background.repaint();
                //Setting continue button.
                background.add(Continue);
                background.revalidate();
                background.repaint();
                revalidate();
                repaint();
                if (e.getSource() == Continue){
                    //moving to next stage section.
                    Main.setStageCounter(Main.getStageCounter()+1);
                    background.removeAll();
                    this.dispose();
                    revalidate();
                    repaint();
                    new StageMerge();

//                this.dispose();
//                new Stage2();
                }
            }
            else if (player2.getScore() > player1.getScore()){
                System.out.println("player 2 won");
                new DataStored().updateScore(Main.getPlayer1Name(),player1.getScore(),Main.getStageCounter(),forPlayers);
                new DataStored().updateScore(Main.getPlayer2Name(),player2.getScore(),Main.getStageCounter(),forPlayers);
                background.remove(DiceButton);
                background.revalidate();
                background.repaint();
                //Setting continue button.
                background.add(Continue);
                background.revalidate();
                background.repaint();
                if (e.getSource() == Continue){
                    Main.setStageCounter(Main.getStageCounter()+1);
                    background.removeAll();
                    this.dispose();
                    revalidate();
                    repaint();
                    new StageMerge();

                }
            }
            else{
                System.out.println("player Draw");
                new DataStored().updateScore(Main.getPlayer1Name(),player1.getScore(),Main.getStageCounter(),forPlayers);
                new DataStored().updateScore(Main.getPlayer2Name(),player2.getScore(),Main.getStageCounter(),forPlayers);
                background.remove(DiceButton);
                background.revalidate();
                background.repaint();
                //Setting continue button.
                background.add(Continue);
                background.revalidate();
                background.repaint();
                if (e.getSource() == Continue){
                    Main.setStageCounter(Main.getStageCounter()+1);
                    background.removeAll();
                    this.dispose();
                    revalidate();
                    repaint();
                    new StageMerge();

                }
            }

        }

        }

    public void setter(Boat player, String trap,String current, int playerXpos, JLabel[] JT,JLabel[] JC) {
        ImageIcon image1 = new ImageIcon(trap);
            for (int i = 0; i < player.getTrapPosition().size(); i++) {
                JT[i] = new JLabel();
                JT[i].setIcon(image1);
                JT[i].setBounds(playerXpos,player.getTrapPosition().get(i),80,50);
                background.add(JT[i]);
            }
            for (int i = 0; i < player.getCurrentPosition().size(); i++) {
                ImageIcon image2 = new ImageIcon(current);
                JC[i] = new JLabel();
                JC[i].setIcon(image2);
                JC[i].setBounds(playerXpos,player.getCurrentPosition().get(i),80,50);
                background.add(JC[i]);
            }
    }
    private void Remover(JLabel[] firstArray, ArrayList<JLabel> secondArray){
        if (firstArray!= null){
            for (int i=0;i< firstArray.length;i++){
                if (firstArray[i]!=null){
                    secondArray.add(firstArray[i]);
                }
            }
        }
    }


}