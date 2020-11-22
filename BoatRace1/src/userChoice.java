import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class userChoice extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel panel;

    ///
    private JLabel topLogo;
    private JLabel ChoiceText;

    private JButton restartButt;
    private JButton exitButt;

    private JButton confirm;

    private JButton cancelButt;
    ///

    private String fontColors = "#D9e7ff";
    private String backgroundColor = "#4a454d";

    userChoice(){
        frame = new JFrame();
        panel = new JPanel();
    }
    public void promptScreen(){

        frame.setTitle("Boat Race");

        frame.setSize(700,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        //setting logo on top of the window
        topLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon("GUI/windowIcon/bb1.png");
        topLogo.setIcon(imageIcon);
        topLogo.setBounds(280,5,120,63);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.decode(backgroundColor));
        panel.setLayout(null);

        //// title
        ChoiceText = new JLabel("Do you wanna restart the game?");
        ChoiceText.setBounds(190,60,400,50);
        ChoiceText.setFont(new Font("roboto",Font.BOLD, 20));
        ChoiceText.setForeground(Color.decode(fontColors));
        ////

        /// restart button
        restartButt = new JButton("Restart?");
        restartButt.setBounds(100,150,200,300);
        restartButt.setFont(new Font("roboto",Font.BOLD, 30));
        restartButt.setBackground(Color.decode("#293f4a"));
        restartButt.setForeground(Color.decode(fontColors));
        restartButt.addActionListener(this);
//        restartButt.setVisible(false);
        ///

        /// exit button
        exitButt = new JButton("EXIT");
        exitButt.setBounds(400,150,200,300);
        exitButt.setFont(new Font("roboto",Font.BOLD, 30));
        exitButt.setBackground(Color.decode("#293f4a"));
        exitButt.setForeground(Color.decode(fontColors));
        exitButt.addActionListener(this);
        ///

        // hidden confirm butt
        confirm = new JButton("Quit the game");
        confirm.setBounds(250,200,200,50);
        confirm.setFont(new Font("roboto",Font.BOLD, 20));
        confirm.setBackground(Color.decode("#293f4a"));
        confirm.setForeground(Color.decode(fontColors));
        confirm.addActionListener(this);
        confirm.setVisible(false);
        //


        ///  hidden exit butt
        cancelButt = new JButton("cancel");
        cancelButt.setBounds(250,300,200,50);
        cancelButt.setFont(new Font("roboto",Font.BOLD, 20));
        cancelButt.setBackground(Color.decode("#293f4a"));
        cancelButt.setForeground(Color.decode(fontColors));
        cancelButt.addActionListener(this);
        cancelButt.setVisible(false);
        // end exit

        panel.add(topLogo);
        panel.add(ChoiceText);
        panel.add(restartButt);
        panel.add(exitButt);
        panel.add(confirm);
        panel.add(cancelButt);

        ImageIcon image = new ImageIcon("GUI/windowIcon/BoatIcon.png");
        frame.setIconImage(image.getImage()); // change icon of frame

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartButt){
            Main.setStageCounter(0);
            frame.dispose();
//            System.gc();
            new StageMerge();
        }
        if (e.getSource() == exitButt){
            confirm.setVisible(true);
            cancelButt.setVisible(true);
            ChoiceText.setText("Are you sure?");
            ChoiceText.setBounds(280,60,400,50);
            restartButt.setVisible(false);
            exitButt.setVisible(false);
        }
        if (e.getSource() == confirm){
            Main.setStageCounter(Main.getStageCounter()+1);
            frame.dispose();
            new StageMerge();
        }
        if (e.getSource().equals(cancelButt)){
            new userChoice();
            confirm.setVisible(false);
            cancelButt.setVisible(false);
            ChoiceText.setText("Do you wanna restart the game?");
            ChoiceText.setBounds(190,60,400,50);
            restartButt.setVisible(true);
            exitButt.setVisible(true);
        }

    }
}
