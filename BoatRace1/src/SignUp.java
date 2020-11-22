import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignUp extends JFrame implements ActionListener {
    private JButton loginButton;
    private JLabel success;

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

    private JLabel passwordConLabel;
    private JPasswordField passwordConField;

    private JLabel topBanner;

    private JLabel[] topUser = new JLabel[5];

    private  JButton signup;

    private JLabel columnPlace;
    private JLabel columnName;
    private JLabel columnScore;
    private JLabel columnDate;

    private String name;
    private String password;
    private String passwordcon;

    private ArrayList<String> names = new ArrayList<String>();
    private Boolean flagging;

    private JLabel errorMessage;

    private JButton backButton;

    private String fontColors = "#D9e7ff";
    private String backgroundColor = "#4a454d";

    private JLabel topLogo;

    private JLabel topTitle;





//    private ArrayList<JLabel> TrapArrayP2 = new ArrayList<JLabel>();

    SignUp(){

        panel = new JPanel();
        frame = new JFrame();
//        signupGui();

    }

    public void signupGui(){
        frame.setTitle("Boat Race");
        frame.setResizable(false);

        frame.setSize(700,500);
//        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setBackground(Color.decode(backgroundColor));
        panel.setLayout(null);

        //setting logo on top of the window
        topLogo = new JLabel();
        ImageIcon imageIcon = new ImageIcon("GUI/windowIcon/bb1.png");
        topLogo.setIcon(imageIcon);
        topLogo.setBounds(280,5,120,63);

        // title window
        topTitle = new JLabel("Create An Account To Play");
        topTitle.setBounds(215,70,350,40);
        topTitle.setForeground(Color.decode("#D9e7ff"));
        topTitle.setFont(new Font("roboto",Font.BOLD, 20));


        //setting title for the top user
        topBanner = new JLabel("SIGN UP");
        topBanner.setFont(new Font("roboto",Font.BOLD, 20));
        topBanner.setForeground(Color.decode(fontColors));
        topBanner.setBounds(300,150,150,25);


        //setting user label
        userLabel = new JLabel("User:");
        userLabel.setFont(new Font("roboto",Font.BOLD, 15));
        userLabel.setForeground(Color.decode(fontColors));
        userLabel.setBounds(200,210 ,80,25);

        userText = new JTextField(20);//setting the length of that text field
        userText.setBounds(340,210,165,25);

        //user label end

        //setting password label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("roboto",Font.BOLD, 15));
        passwordLabel.setForeground(Color.decode(fontColors));
        passwordLabel.setBounds(200,260,80,25);//in by 30

        passwordField = new JPasswordField();
        passwordField.setBounds(340,260,165,25);

        //password end

        //setting passwordCon label
        passwordConLabel = new JLabel("Repeat Password:");
        passwordConLabel.setFont(new Font("roboto",Font.BOLD, 15));
        passwordConLabel.setForeground(Color.decode(fontColors));
        passwordConLabel.setBounds(200,310,140,25);//in by 30

        passwordConField = new JPasswordField();
        passwordConField.setBounds(340,310,165,25);

        //passwordCon end

        ///ERROR

        errorMessage = new JLabel();
//        errorMessage.setText("heelo");
        errorMessage.setBounds(260,410,350,50);
        errorMessage.setFont(new Font("roboto",Font.BOLD, 20));
        errorMessage.setForeground(Color.decode("#ff0000"));
        errorMessage.setVisible(false);


        ///ERROR


        // signup button
        signup = new JButton("Sign Up");
        signup.setBounds(290,360,100,50);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        signup.setBackground(Color.BLACK);
        signup.setOpaque(true);


        // back button
        backButton = new JButton("BACK");
        backButton.setBounds(585,0,100,50);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setOpaque(true);
        //end back button



//        //showing message when singup successful
//        success = new JLabel("");
//        success.setBounds(10,110,400,25);
//
//        loginButton.addActionListener(this);

        //adding to the panel
        panel.add(topBanner);

        panel.add(userLabel);
        panel.add(userText);

        panel.add(passwordLabel);
        panel.add(passwordField);

        panel.add(passwordConLabel);
        panel.add(passwordConField);

        panel.add(signup);
        panel.add(errorMessage);

        panel.add(backButton);

        panel.add(topLogo);

        panel.add(topTitle);

        //Setting Window Icon
        ImageIcon image = new ImageIcon("GUI/windowIcon/BoatIcon.png");
        frame.setIconImage(image.getImage()); // change icon of frame

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //get the value from the database directly
//        compare it with the data in the database.
        if (e.getSource() == signup){

            name = userText.getText();
            password = passwordField.getText();
            passwordcon = passwordConField.getText();
            if (name.isEmpty()){
                System.out.println("login text is empty");
                errorMessage.setBounds(260,410,350,50);
                errorMessage.setText("User Field Empty");
                errorMessage.setVisible(true);
            }
            else if (password.isEmpty()){
                System.out.println("password filed is empty");
                errorMessage.setBounds(250,410,350,50);
                errorMessage.setText("Password is Empty ");
                errorMessage.setVisible(true);
            }
            else if (passwordcon.isEmpty()){
                System.out.println("password con filled in empty");
                errorMessage.setBounds(220,410,350,50);
                errorMessage.setText("Repeat Password is Empty");
                errorMessage.setVisible(true);
            }
            else if (!password.equals(passwordcon)){
                System.out.println("Password Unmatch");
                errorMessage.setBounds(260,410,350,50);
                errorMessage.setText("PassWord Unmatch");
                errorMessage.setVisible(true);
            }
            else if (!name.isEmpty() && !password.isEmpty() && !passwordcon.isEmpty()){
                new DataStored().dataDB(name,password,passwordcon,names);
                Main.setStageCounter(Main.getStageCounter()+1);
                frame.dispose();
                new Login().settingImage();



            }
        }
        if (e.getSource() == backButton){
            frame.dispose();
            new Login().settingImage();
        }



    }
}
