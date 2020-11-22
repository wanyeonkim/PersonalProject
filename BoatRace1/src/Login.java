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
import java.util.ArrayList;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener {
    private JButton loginButton;
    private JLabel success;

    private JButton sign;

    private int checker=0;

    public int getChecker() {
        return checker;
    }

    public void setChecker(int checker) {
        this.checker = checker;
    }

    private JFrame frame;
    private JPanel panel;

    private JLabel userLabel;
    private JTextField userText;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JLabel userLabelp2;
    private JTextField userTextp2;

    private JLabel passwordLabelp2;
    private JPasswordField passwordFieldp2;

    private JLabel topBanner;

    private JLabel[] topUser = new JLabel[5];

    private JLabel columnPlace;
    private JLabel columnName;
    private JLabel columnScore;
    private JLabel columnDate;

    private JLabel p1;
    private JLabel p2;

    private int currentPosition =1;


    private JLabel topLogo;

    private JLabel[] name = new JLabel[5];
    private JLabel[] score = new JLabel[5];
    private JLabel[] date = new JLabel[5];
    private JLabel gameTitle;

    //////

    private String fontColors = "#D9e7ff";
    private String backgroundColor = "#4a454d";

    //////




//    //player components
//
//    private String player1Name;
//    private String player2Name;
//
//    public String getPlayer1Name() {
//        return player1Name;
//    }
//
//    public void setPlayer1Name(String player1Name) {
//        this.player1Name = player1Name;
//    }
//
//    public String getPlayer2Name() {
//        return player2Name;
//    }
//
//    public void setPlayer2Name(String player2Name) {
//        this.player2Name = player2Name;
//    }
//
//    //player components

    //    private ArrayList<JLabel> TrapArrayP2 = new ArrayList<JLabel>();

    Login(){
        panel = new JPanel();
        frame = new JFrame();

//        Arrays.fill(topUser,null);
//        Arrays.fill(name,null);
//        Arrays.fill(score,null);
//        Arrays.fill(date,null);


//        settingImage();
    }


    public void settingImage(){
        settingTopValue();
        frame.setTitle("Boat Race");


        frame.setSize(700,950);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.decode(backgroundColor));
        panel.setLayout(null);

        //setting logo on top of the window
        topLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon("GUI/windowIcon/bb1.png");
        topLogo.setIcon(imageIcon);
        topLogo.setBounds(280,52,120,63);

        //welcome text
        gameTitle = new JLabel("Welcome to Boat Race");
        gameTitle.setForeground(Color.decode("#D9e7ff"));
        gameTitle.setFont(new Font("roboto",Font.BOLD, 20));
        gameTitle.setBounds(230,0,250,50);



        //setting title for the top user
        topBanner = new JLabel("TOP 5 USER");
        topBanner.setForeground(Color.decode(fontColors));
        topBanner.setFont(new Font("roboto",Font.BOLD, 20));
        topBanner.setBounds(280,120,150,25);

        //top user column
        columnPlace = new JLabel("Ranking");
        columnName = new JLabel("Name");
        columnScore = new JLabel("Score");
        columnDate = new JLabel("Date");

        columnPlace.setBounds(20,150,80,25);
        columnPlace.setForeground(Color.decode(fontColors));
        columnPlace.setFont(new Font("roboto",Font.BOLD, 18));
        panel.add(columnPlace);

        columnName.setBounds(200,150,80,25);
        columnName.setForeground(Color.decode(fontColors));
        columnName.setFont(new Font("roboto",Font.BOLD, 18));
        panel.add(columnName);
        //starting position for database data will be (150,120) increase by  by 100 each loop


        columnScore.setBounds(380,150,80,25);
        columnScore.setForeground(Color.decode(fontColors));
        columnScore.setFont(new Font("roboto",Font.BOLD, 18));
        panel.add(columnScore);
        //starting position for database data will be (320,120) increase by  by 100 each loop


        columnDate.setBounds(570,150,80,25);
        columnDate.setForeground(Color.decode(fontColors));
        columnDate.setFont(new Font("roboto",Font.BOLD, 18));
        panel.add(columnDate);
        //starting position for database data will be (500,120) increase by  by 100 each loop



        //top user listing.

        int positionX = 50;
        int positionY = 170;
        for (int i=0;i<topUser.length;i++){
            topUser[i] = new JLabel(""+(i+1));
            topUser[i].setForeground(Color.decode(fontColors));
            topUser[i].setFont(new Font("roboto",Font.BOLD, 15));
            topUser[i].setBounds(positionX,positionY,8,50);
            panel.add(topUser[i]);
            positionY = positionY + 100;
        }

        //player1 label

        p1 = new JLabel("player 1");
        p1.setForeground(Color.decode(fontColors));
        p1.setFont(new Font("roboto",Font.BOLD, 18));
        p1.setBounds(100,680,80,25);

        //player label end

        //setting user label
        userLabel = new JLabel("User:");
        userLabel.setForeground(Color.decode(fontColors));
        userLabel.setFont(new Font("roboto",Font.BOLD, 15));
        userLabel.setBounds(10,750,80,25);


        userText = new JTextField(20);//setting the length of that text field
        userText.setBounds(100,750,165,25);

        //user label end

        //setting password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.decode(fontColors));
        passwordLabel.setFont(new Font("roboto",Font.BOLD, 15));
        passwordLabel.setBounds(10,800,80,25);//in by 30

        passwordField = new JPasswordField();
        passwordField.setBounds(100,800,165,25);

        //password end

        // p2

        //player2 label

        p2 = new JLabel("player 2");
        p2.setForeground(Color.decode(fontColors));
        p2.setFont(new Font("roboto",Font.BOLD, 18));
        p2.setBounds(400,680,80,25);

        //player label end

        //setting user label
        userLabelp2 = new JLabel("User:");
        userLabelp2.setForeground(Color.decode(fontColors));
        userLabelp2.setFont(new Font("roboto",Font.BOLD, 15));
        userLabelp2.setBounds(310,750,80,25);


        userTextp2 = new JTextField(20);//setting the length of that text field
        userTextp2.setBounds(400,750,165,25);

        //user label end

        //setting password label
        passwordLabelp2 = new JLabel("Password:");
        passwordLabelp2.setForeground(Color.decode(fontColors));
        passwordLabelp2.setFont(new Font("roboto",Font.BOLD, 15));
        passwordLabelp2.setBounds(310,800,80,25);//in by 30

        passwordFieldp2 = new JPasswordField();
        passwordFieldp2.setBounds(400,800,165,25);

        //password end

        //p2 end

        //setting login button
        ImageIcon loginImage = new ImageIcon("GUI/login/lo1.png");
        loginButton = new JButton(loginImage);
        loginButton.setBounds(255,860,150,38);

        sign = new JButton("Sign up");
        sign.setBackground(Color.decode("#c2e000"));
        sign.setBounds(590,765,80,50);
        sign.addActionListener(this);


        //showing message when login successful
        success = new JLabel("");
        success.setBounds(10,110,400,25);

        loginButton.addActionListener(this);

        //adding to the panel

        panel.add(topBanner);
        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(success);
        panel.add(sign);
        panel.add(p1);
        panel.add(p2);
        panel.add(userLabelp2);
        panel.add(userTextp2);
        panel.add(passwordFieldp2);
        panel.add(passwordLabelp2);
        panel.add(topLogo);
        panel.add(gameTitle);

        //Setting Window Icon
        ImageIcon image = new ImageIcon("GUI/windowIcon/BoatIcon.png");
        frame.setIconImage(image.getImage()); // change icon of frame
        frame.setVisible(true);
    }

    public void settingTopValue(){
        int yvalue = 170;
        String ss;
        if (!Main.topUserName.isEmpty()){
            for (int i=0;i<Main.topUserName.size();i++){
                ss = Main.topUserName.get(i).toUpperCase();
                GettingTogether(name,ss,yvalue,50,210,i);
                yvalue = yvalue + 100;
            }
        }
        if (!Main.topUserScore.isEmpty()){
            yvalue= 170;
            for (int i=0;i<Main.topUserScore.size();i++){
                ss = Integer.toString(Main.topUserScore.get(i));
                GettingTogether(score, ss, yvalue,50,400,i);
                yvalue = yvalue + 100;
            }
        }
        if (!Main.topUserDate.isEmpty()){
            yvalue = 170;
            for (int i=0;i<Main.topUserDate.size();i++){
                ss = Main.topUserDate.get(i);
                GettingTogether(date,ss,yvalue,90,560,i);
                yvalue = yvalue + 100;
            }
        }
    }

    public void GettingTogether(JLabel[] compo, String ss, int yvalue, int width, int xValue,int i){
        compo[i] = new JLabel(ss);
        compo[i].setBounds(xValue,yvalue,width,50);
        compo[i].setForeground(Color.decode("#c2e000"));
        compo[i].setFont(new Font("roboto",Font.BOLD, 13));
        panel.add(compo[i]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //get the value from the database directly
//        compare it with the data in the database.
        Main.setPlayer1Name(null);
        Main.setPlayer2Name(null);
        Main.setP1Flagger(false);
        Main.setP2Flagger(false);
        String user1= "";
        String password1 ="";
        currentPosition = 1;
        Main.setStageCounter(1);
        if (e.getSource() ==loginButton){

            user1 = userText.getText();
            password1 = passwordField.getText();


            String user2 = userTextp2.getText();
            String password2 = passwordFieldp2.getText();

            DataStored data = new DataStored();

            data.checkDB(user1,password1,password1,Main.getStageCounter(),currentPosition);
            currentPosition++;
            data.checkDB(user2,password2,password2,Main.getStageCounter(), currentPosition);
            Main.setStageCounter(Main.getStageCounter()+1);
            if (Main.getP1Flagger() && Main.getP2Flagger()){
                Main.setPlayer1Name(user1);
                Main.setPlayer2Name(user2);
                frame.dispose();
                new StageMerge();
            }
            else if (!Main.getP1Flagger()){

                System.out.println("player 1 account error.. Please try again");


            }
            else if (!Main.getP2Flagger()){

                System.out.println("Player 2 account error.. Please try again");

            }

        }
        if (e.getSource() == sign){
            frame.dispose();
            new SignUp().signupGui();
        }

    }
}
