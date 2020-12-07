import org.sqlite.core.DB;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
public class DataBase {
    private Path path = Paths.get("banking.db");


    private String UserTableCreation = "CREATE TABLE IF NOT EXISTS user (name VARCHAR, password VARCHAR)";
    private String Stage1 = "CREATE TABLE IF NOT EXISTS stage1 (name VARCHAR, score INTEGER, date CURRENT_DATE)";
    private String pew = "jdbc:sqlite:";

    private String DBLoc = "";

    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> repeatedName = new ArrayList<String>();

    private ArrayList<String> queriedName = new ArrayList<String>();
    private int queriedScore;

    public void setDBLoc(String DBLoc) {
        this.DBLoc = DBLoc;
    }
    ////getting path for db
    private ArrayList<Character> pathDB = new ArrayList<Character>();

    private String fullpath="";

    ////

    private PreparedStatement pr;

    private ResultSet results;

    private Connection conn;

    DataBase(){
        gettingDBPath();
        setDBLoc(pew);
//        System.out.println(DBLoc);
    }


    private void gettingDBPath(){
        String pp = Paths.get("banking.db").toAbsolutePath().toString();
        for (int i=0;i < pp.length();i++){
            char inn = pp.charAt(i);
            pathDB.add(inn);
        }
        for (Character character : pathDB) {
            if (character.equals('1')) {
                pew += "m\\src";
            } else {
                pew += character;
            }
        }
    }

    public void dataDB(String name, String password, ArrayList<String> Listname ){
        try {
            conn  = DriverManager.getConnection(DBLoc);
            Statement statement = conn.createStatement();
            statement.execute(UserTableCreation);


            //getting all the user name.
            results = statement.executeQuery("SELECT name FROM user");
            while (results.next()){
                Listname.add(results.getString("name"));
            }
            for (String s : Listname) {
                if (name.equals(s)) {
                    repeatedName.add(s);
                    break;
                }

            }
            if (repeatedName.isEmpty()){
                String query1 = "INSERT INTO user VALUES(?,?)";
                pr = conn.prepareStatement(query1);

                pr.setString(1,name);
                pr.setString(2,password);

                pr.execute();

                statement.close();
                conn.close();
            }else{
                System.out.println("user name taken");
            }

//            statement.execute();

        } catch (SQLException e) {
            System.out.println("something went wrong: " + e.getMessage());
        }

    }


    // login section to check user input if it is valid.(user validity)
    // if Main.userChecker is false the break the code.
    public void checkDB(String names,String passwords){
        int counter=0;


        try {
            conn  = DriverManager.getConnection(DBLoc);
            Statement statement = conn.createStatement();

//            statement.execute("CREATE TABLE IF NOT EXISTS " +"Stage"+(stageCounter-1)+ "(name VARCHAR, score INTEGER, date CURRENT_DATE)");

            //getting all the user name.
            results = statement.executeQuery("SELECT name FROM user");

            while (results.next()){
                queriedName.add(results.getString("name"));
            }

            //inside queriedName everything will be stored.
            //everything in name column
            for (String s : queriedName) {
                if (names.equals(s)) {
                    pr = conn.prepareStatement("SELECT password FROM user WHERE name = ?");
                    pr.setString(1, names);
                    results = pr.executeQuery();
                    if (results.getString("password").equals(passwords)) {
                        System.out.println("Hello welcome "+s);
                        counter += 1;
                        break;
                    }
                    else{
                        counter += 1;
                        System.out.println("Wrong Password");
                    }
                }
            }
            if (counter == 0){
                System.out.println("user doesnt exist");
            }
            statement.close();
            conn.close();

        }
        catch (SQLException e) {
            System.out.println("something went wrong: " + e.getMessage());
        }
    }



}
