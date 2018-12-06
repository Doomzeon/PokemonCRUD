/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjdbc;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author net
 */
public class FirstJDBC {

    /**
     * @param args the command line arguments
     */
    static String url = "jdbc:mysql://localhost:3306/officine";
    static String user = "root";
    static String password = "";

    static Connection conn; //L'oggetto che contiene la connessione al DB

    public static void connectToDB() {
        try {
            // Registro la libreria (non è necessario per le librerie più recenti)
            Class.forName("com.mysql.jdbc.Driver"); 
            // create a connection to the database
            conn = (Connection) DriverManager.getConnection(url, user, password);
           
            System.out.println("Successfully connected!!!!");
        } catch (Exception ex) {
            System.out.println("ERROR!" + ex.getMessage());
        }
    } 
    private static void TestQuery() {
        String sql = "SELECT * FROM officina ";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);//esegui la query

            while (rs.next()) {
                System.out.println(rs.getString("nome"));
            }
                    
        } catch (SQLException ex) {
            System.out.println("ERROR!" + ex.getMessage());
        }
    }
    private static void TestQuery2() {
        String sql = "SELECT * FROM officina ";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);//esegui la query

            while (rs.next()) {
                System.out.println(rs.getString("nome"));
            }
                    
        } catch (SQLException ex) {
            System.out.println("ERROR!" + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        connectToDB();
        TestQuery();
        TestQuery2();
    }
    
}
