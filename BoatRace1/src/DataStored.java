import org.sqlite.core.DB;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class DataStored {

    private Path path = Paths.get("game.db");


    private String UserTableCreation = "CREATE TABLE IF NOT EXISTS user (name VARCHAR, password VARCHAR, conpassword VARCHAR)";
    private String Stage1 = "CREATE TABLE IF NOT EXISTS stage1 (name VARCHAR, score INTEGER, date CURRENT_DATE)";
    private String pew = "jdbc:sqlite:";

    private String DBLoc = "";

    public void setDBLoc(String DBLoc) {
        this.DBLoc = DBLoc;
    }

    private String userInsert = "INSERT INTO stage1 (name,score,date)VALUES('kim',?,current_date)";
    private String scores = "1";

    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> repeatedName = new ArrayList<String>();

    private ArrayList<String> queriedName = new ArrayList<String>();
    private int queriedScore;

    private Boolean check;

    private int dbscore;
    private int userscore;

    private PreparedStatement pr;

    private ResultSet results;

    private Connection conn;

    private int querytotscore;

    ////getting path for db
    private ArrayList<Character> pathDB = new ArrayList<Character>();

    private String fullpath="";

    ////

    DataStored(){
        gettingDBPath();
        setDBLoc(pew);


//        dataDB("josh","123123","123123",names);
//        checkDB("lizard","123123","123123",check,3);
//        updateScore("lizard",40,2,check);
//        summingThemAll("kimyeonwan",userscore);
//        topName();

    }

    //getting path

    private void gettingDBPath(){
        String pp = Paths.get("game.db").toAbsolutePath().toString();
        for (int i=0;i < pp.length();i++){
            char inn = pp.charAt(i);
            pathDB.add(inn);
        }
        for (int i=0;i< pathDB.size();i++){
            if (pathDB.get(i).equals('1')){
                pew += "1\\src";
            }else{
                pew += pathDB.get(i);
            }
        }
    }

    // enf path

    // storing user (create account)
    public void dataDB(String name, String password, String passwordCon, ArrayList<String> Listname ){
        try {
            conn  = DriverManager.getConnection(DBLoc);
            Statement statement = conn.createStatement();
            statement.execute(UserTableCreation);


            //getting all the user name.
            results = statement.executeQuery("SELECT name FROM user");
            while (results.next()){
                Listname.add(results.getString("name"));
            }
            for (int i=0;i<Listname.size();i++){
                if (name == Listname.get(i)){
                    repeatedName.add(Listname.get(i));
                    break;
                }

            }
            if (repeatedName.isEmpty()){
                pr = conn.prepareStatement("INSERT INTO user VALUES(?,?,?)");

                pr.setString(1,name);
                pr.setString(2,password);
                pr.setString(3,passwordCon);

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

    //get the user score for each stage by querying
    // SELECT score FROM stage_ where user = '____';
    //accepting stage counter as the stage number so the table name has to link up in a way
    // name it so that it can be called stage1 stage2 stage3.. so on.
    // or i have a logic that sort through a condition and it will affect it accordingly.

    // login section to check user input if it is valid.(user validity)
    // if Main.userChecker is false the break the code.
    public void checkDB(String names,String passwords,String passwordCon , int stageCounter, int position){


        try {
            conn  = DriverManager.getConnection(DBLoc);
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS " +"Stage"+(stageCounter-1)+ "(name VARCHAR, score INTEGER, date CURRENT_DATE)");

            //getting all the user name.
            results = statement.executeQuery("SELECT name FROM user");

            while (results.next()){
                queriedName.add(results.getString("name"));
            }
            for (int i=0;i<queriedName.size();i++){
                if (names.equals(queriedName.get(i))){
                    pr = conn.prepareStatement("SELECT password FROM user WHERE name = ?");
                    pr.setString(1,names);
                    results =pr.executeQuery();

                    if (position%2 != 0){
                        Main.setP1Flagger(false);
                        if (passwords.equals(results.getString("password"))){
                            System.out.println("valid user " +position + "\n Welcome " + queriedName.get(i).toUpperCase());
                            Main.setP1Flagger(true);
                        }
                        else{
                            System.out.println("hmm try again..");

                        }
                    }
                    else{
                        Main.setP2Flagger(false);
                        if (passwords.equals(results.getString("password"))){
                            System.out.println("valid user " +position + "\n Welcome " + queriedName.get(i).toUpperCase());
                            Main.setP2Flagger(true);

                        }
                        else{
                            System.out.println("hmm try again..");

                        }
                    }



                    break;
                }
            }

            statement.close();
            conn.close();

        }
        catch (SQLException e) {
            System.out.println("something went wrong: " + e.getMessage());
        }
    }

    // updating user value if the user score is higher than the original score in the table
    // this will update the score
    // UPDATE table_name SET score= '123123' WHERE name ='___';

    public void updateScore(String name,int score, int stageCounter, Boolean flagInvalid){
        flagInvalid = true;
        String tableN1 = "Stage"+(stageCounter-1);
        try {
            conn  = DriverManager.getConnection(DBLoc);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS " +tableN1 +"(name VARCHAR, score INTEGER, date CURRENT_DATE)");

            //insert data accordingly
            //name and score

            //getting all the user name.

            results = statement.executeQuery("SELECT name FROM " + tableN1);
            while (results.next()){
                queriedName.add(results.getString("name"));
            }

            for (int i=0;i<queriedName.size();i++){
                if (name.equals(queriedName.get(i))){
                    flagInvalid = false;
                    break;
                }
            }

            //getting all the score.
            String ScoreGet = "SELECT score FROM "+tableN1+" WHERE name=?";

            pr = conn.prepareStatement(ScoreGet);
            pr.setString(1,name);
            results= pr.executeQuery();

            while (results.next()){
                queriedScore = results.getInt("score");

            }
//            statement.close();
            if (queriedScore < score){
                String UpdateQue = "UPDATE "+ tableN1 +" SET score=? WHERE name=?";

                pr = conn.prepareStatement(UpdateQue);
                pr.setInt(1,score);
                pr.setString(2,name);
                pr.execute();

            }

            //getting user score END

            if (flagInvalid){
                String InsertQue = "INSERT INTO "+tableN1 + " VALUES(?,?,current_date )";
                pr = conn.prepareStatement(InsertQue);

                pr.setString(1,name);
                pr.setInt(2,score);
                pr.execute();
            }
            //check if the score for that user is above the current value
            // if it is then update the value
            // if not just ignore it
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("something went wrong: " + e.getMessage());
        }
    }

    // call all the table and get the score according to the user name sum it all up
    // compared with the score obtain in this round.

    //make a array in the Main. then use that

    public void summingThemAll(String userName,int totalScoreCounter){
        String tableN1;
        try {
            conn  = DriverManager.getConnection(DBLoc);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS ScoreTotal (name VARCHAR, score INTEGER, date CURRENT_DATE)");

            for (int i=0; i<5;i++){

                tableN1 =  "Stage"+(i+1);
                String ScoreGet = "SELECT score FROM "+tableN1+" WHERE name=?";

                pr = conn.prepareStatement(ScoreGet);
                pr.setString(1,userName);
                results= pr.executeQuery();

                totalScoreCounter+=results.getInt("score");

            }
//            System.out.println(totalScoreCounter);

            ///error down here somewhere
            //it is likely to be problem with the resultset being empty
            //set a condition to fix it
            //trying to figure it out

            conn.close();
            conn  = DriverManager.getConnection(DBLoc);

            pr = conn.prepareStatement("SELECT score FROM ScoreTotal WHERE name=?");
            pr.setString(1,userName);
            results = pr.executeQuery();
            if (results.next() ==false){
                conn.close();
                conn  = DriverManager.getConnection(DBLoc);
                String InsertTotal = "INSERT INTO ScoreTotal VALUES(?,?,current_date )";
                pr = conn.prepareStatement(InsertTotal);

                pr.setString(1,userName);
                pr.setInt(2,totalScoreCounter);
                pr.execute();
                statement.close();
            }
            else{
                querytotscore = results.getInt("score");
                conn.close();
                conn  = DriverManager.getConnection(DBLoc);
                pr =conn.prepareStatement("SELECT name FROM ScoreTotal");
                results = pr.executeQuery();
                if (results.getString("name").equals(userName)){
                    if (querytotscore < totalScoreCounter){
                        conn.close();
                        conn  = DriverManager.getConnection(DBLoc);
                        pr = conn.prepareStatement("UPDATE  ScoreTotal SET score=?");
                        pr.setInt(1,totalScoreCounter);
                        pr.execute();
                        statement.close();
                    }
                }
            }
        }

        catch (SQLException e) {
            System.out.println("something went wrong: " + e.getMessage());
        }

    }
    public void topName(){

        try {
            conn   = DriverManager.getConnection(DBLoc);
            Statement statement = conn.createStatement();
            pr = conn.prepareStatement("SELECT * FROM ScoreTotal ORDER BY score DESC LIMIT 5 ");
            results = pr.executeQuery();
            while (results.next()){
//                Main.players.add(new Player(results.getString("name"), results.getInt("score"), results.getString("date")))
                Main.topUserName.add(results.getString("name"));
                Main.topUserScore.add(results.getInt("score"));
                Main.topUserDate.add(results.getString("date"));
            }
            statement.close();

//            for (int i=0;i<Main.topUserName.size();i++){
//                System.out.println(Main.topUserName.get(i));
//                System.out.println(Main.topUserScore.get(i));
//                System.out.println(Main.topUserDate.get(i));
//            }

            Main.setStageCounter(Main.getStageCounter()+1);
//            new StageMerge();

        }
        catch (SQLException e) {
            System.out.println("something went wrong: " + e.getMessage());
        }
    }


}
















