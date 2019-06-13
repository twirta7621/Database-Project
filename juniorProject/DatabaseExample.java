import java.io.IOException;

import java.util.Map;
import java.sql.*;
//javac -cp sqlite-jdbc-3.23.1.jar; DatabaseExample.java

public class DatabaseExample {

    public static void main(String[] args) throws IOException {
        Database db = new Database("jdbc:sqlite:chinook.db");// creates database objects
        String query = "SELECT * FROM customers";//  SQL statement
        String result = db.selectData(query);// runs statement
        System.out.println(result);// returns result
    }    

}
