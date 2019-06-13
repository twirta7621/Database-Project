import java.io.IOException;
import java.util.Map;
import java.sql.*;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

//javac -cp sqlite-jdbc-3.23.1.jar; JuniorProject.java
//path C:\Program Files\Java\jdk1.8.0_131\bin
//path C:\Program Files\Java\jdk-12.0.1\bin
public class JuniorProject{
    public static void main(String[] args)throws IOException{
        
        int port = 8500;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        Database db = new Database("jdbc:sqlite:Movies.db");

        String query = "SELECT * FROM Movies";//  SQL statement
        String result = db.selectData(query);// runs statement

        String titles = Input.readFile("title.html");
        String front = Input.readFile("frontEnd.html");
        String search = Input.readFile("search.html");
        String cast = Input.readFile("cast.html");
        String dir = Input.readFile("director.html");
        String g = Input.readFile("genre.html");
        server.createContext("/json", new RouteHandler(result));
        server.createContext("/home", new RouteHandler(front));
        server.createContext("/search", new RouteHandler(search));
        server.createContext("/title", new RouteHandler(titles));
        server.createContext("/cast", new RouteHandler(cast));
        server.createContext("/dir", new RouteHandler(dir));
        server.createContext("/g", new RouteHandler(g));
        System.out.println("Server is listening on port " + port);

        server.start(); 
    }
}
