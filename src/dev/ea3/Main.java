package dev.ea3;
import java.sql.*;

public class Main {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/Users/EmilioAraos/Documents/Computer Science/Java/Java MasterClass Remastered Udemy August 2019/TestDB/testjava.db";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        // You can use try with resources so the db resources closes automatically.


        try{
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " +  TABLE_CONTACTS +
                                   " (" + COLUMN_NAME + " text, " +
                                    COLUMN_PHONE + " integer, " +
                                    COLUMN_EMAIL + " text" +
                                    ")");
            insertContact(statement, "Emilio", 3467863,"emilio@mail.com");
            insertContact(statement, "Mariela", 887654,"mariela@mail.com");
            insertContact(statement, "Olga", 445687544,"olga@mail.com");
            insertContact(statement, "Lucas", 22345678,"lucas@mail.com");

            statement.execute("UPDATE " + TABLE_CONTACTS +
                    " SET " + COLUMN_PHONE + "=11111111"+
                    " WHERE " + COLUMN_NAME + "='Mariela'");

            statement.execute("DELETE FROM " + TABLE_CONTACTS +
                    " WHERE " + COLUMN_NAME + "='Emilio'");

            ResultSet results = statement.executeQuery("SELECT * FROM " +  TABLE_CONTACTS);

            while(results.next()){
                System.out.println(results.getString(COLUMN_NAME) + " " +
                        results.getInt(COLUMN_PHONE) + " " + results.getString(COLUMN_EMAIL));
            }

            results.close();
            statement.close();
            conn.close();

        }catch(SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertContact(Statement statement, String name,int phone, String email) throws SQLException{

        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL + " ) " +
                "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }



















}
