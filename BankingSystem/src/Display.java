import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class Display extends JFrame implements ActionListener {
    private JButton loginButton;

    private JButton sign;

    private int checker=0;

    private JFrame frame;
    private JPanel panel;

    private JLabel userLabel;
    private JTextField userText;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private JButton SignIn;

    private String fontColors = "#D9e7ff";
    private String backgroundColor = "#4a454d";

    Display(){

        panel = new JPanel();
        frame = new JFrame();

    }
    public void settingImage(){
        frame.setTitle("Account Book");
        frame.setSize(400,650);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setVisible(true);

        panel.setBackground(Color.decode(backgroundColor));
        panel.setLayout(null);

        //user name label
        userLabel = new JLabel();
        userLabel.setText("Username:");
        userLabel.setForeground(Color.decode(fontColors));
        userLabel.setFont(new Font("roboto",Font.BOLD,12));
        userLabel.setBounds(30,500,62,50);
        //user text
        userText = new JTextField(20);
        userText.setBounds(100,510,165,25);

        //password label
        passwordLabel = new JLabel();
        passwordLabel.setText("Password:");
        passwordLabel.setForeground(Color.decode(fontColors));
        passwordLabel.setFont(new Font("roboto",Font.BOLD,12));
        passwordLabel.setBounds(30,550,62,50);

        //password text
        passwordField = new JPasswordField(20);
        passwordField.setBounds(100,560,165,25);

        //button
        SignIn = new JButton("SignIn");
        SignIn.setText("signin");
        SignIn.setForeground(Color.decode("#4f5c57"));
        SignIn.setBounds(280,530,90,25);
        SignIn.addActionListener(this);



        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(SignIn);

        frame.repaint();
        frame.revalidate();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
