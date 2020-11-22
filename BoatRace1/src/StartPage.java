import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StartPage extends JFrame implements ActionListener {
    private JLabel BoatP1;
    private JLabel BoatP2;
    private JLabel finishLine;
    private JLabel background;

    private JButton toGame;

    private JButton StopAnimation;

    private String fontColors = "#D9e7ff";
    private String backgroundColor = "#4a454d";

    private int ypos = 900;

    //
    private java.util.Timer timer = new Timer();
    private int secondspassed = 0;
    private TimerTask task;
    //

    //compo
    private Boolean animationLoopChecker=true;

    public Boolean getAnimationLoopChecker() {
        return animationLoopChecker;
    }

    public void setAnimationLoopChecker(Boolean animationLoopChecker) {
        this.animationLoopChecker = animationLoopChecker;
    }
    //

    StartPage(){

    }
    public void settingBackGround(){

        //Setting Frame
        setSize(Main.SCREEN_WIDTH,Main.getScreenHeight());
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("BOAT RACE - Initial");

        //Setting Boat1
        ImageIcon Boat1 = new ImageIcon("GUI/playerboat/boat1.png");
        BoatP1 = new JLabel();
        BoatP1.setIcon(Boat1);
        BoatP1.setBounds(Main.P1XAxis,ypos,Main.BOAT_WIDTH,Main.BOAT_HEIGHT);
        add(BoatP1);

        //Setting Boat2

        ImageIcon Boat2 = new ImageIcon("GUI/playerboat/boat2.png");
        BoatP2 = new JLabel();
        BoatP2.setIcon(Boat2);
        BoatP2.setBounds(Main.P2XAxis,ypos,Main.BOAT_WIDTH,Main.BOAT_HEIGHT);
        add(BoatP2);

        //////////FINISH LINE//////////////
        ImageIcon finishline = new ImageIcon("GUI/backgrounds/finish.png");
        finishLine = new JLabel();
        finishLine.setIcon(finishline);
        finishLine.setBounds(30,150,700,60);
        finishLine.setVisible(true);

        //button continue
        toGame = new JButton("continue");
        toGame.setBounds(310,300,150,50);
        toGame.setForeground(Color.decode(fontColors));
        toGame.setBackground(Color.decode("#293f4a"));
        toGame.addActionListener(this);
        //

        //stop animation
        StopAnimation = new JButton("Stop animation");
        StopAnimation.setBounds(310,980,150,50);
        StopAnimation.setForeground(Color.decode(fontColors));
        StopAnimation.setBackground(Color.decode("#293f4a"));
        StopAnimation.addActionListener(this);

        //Setting background
        ImageIcon img = new ImageIcon("GUI/backgrounds/riverspooky.png");
        background = new JLabel();
        background.setIcon(img);
        background.setBounds(0,0,Main.SCREEN_WIDTH,Main.getScreenHeight());
        add(background);

        background.add(finishLine);
        background.add(toGame);
        background.add(StopAnimation);

        //repainting and revalidating after adding it to the background
        background.revalidate();
        background.repaint();
        background.setVisible(true);



        //Setting Window Icon
        ImageIcon image = new ImageIcon("GUI/windowIcon/BoatIcon.png");
        setIconImage(image.getImage()); // change icon of frame
        setVisible(true);

        task= new TimerTask(){

            public void run(){
                //change this section to switch.

                if (ypos >= 100 && animationLoopChecker){
                    BoatP1.setBounds(Main.P1XAxis,ypos,Main.BOAT_WIDTH,Main.BOAT_HEIGHT);
                    BoatP2.setBounds(Main.P2XAxis,ypos,Main.BOAT_WIDTH,Main.BOAT_HEIGHT);
                    repaint();
                    revalidate();
                    background.repaint();
                    background.revalidate();
                    ypos = ypos - 50;
                }
                else{
                    ypos = 900;
                }
            }
        };
        timer.scheduleAtFixedRate(task,500,650);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == StopAnimation){
            if (getAnimationLoopChecker()){
                setAnimationLoopChecker(false);
            }
            else{
                setAnimationLoopChecker(true);
            }
        }
        if (e.getSource() == toGame){
            dispose();
            background.disable();
            Main.setStageCounter(Main.getStageCounter()+1);
            new StageMerge();
        }

    }
}
