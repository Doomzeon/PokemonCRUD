
import java.sql.*;
public class FirstJDBC {

    static String url = "jdbc:mysql://localhost:3306/BurliniJdbs.java";
    static String user = "root";
    static String password = "";

    static Connection conn; //L'oggetto che contiene la connessione al DB

    public static void connectToDB() {
        try {
            // Registro la libreria (non è necessario per le librerie più recenti)
            Class.forName("com.mysql.jdbc.Driver"); 
            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
           
            System.out.println("Successfully connected!!!!");
        } catch (Exception ex) {
            System.out.println("ERROR!" + ex.getMessage());
        } 
    }
}

