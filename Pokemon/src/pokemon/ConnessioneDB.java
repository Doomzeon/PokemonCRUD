/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import com.mysql.jdbc.Connection;
import static java.lang.Integer.parseInt;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 *
 * @author net
 */
public class ConnessioneDB extends Observable implements Runnable{
    static String url ;
    static String user ;
    static String password ;

    static Connection conn; //L'oggetto che contiene la connessione al DB
    private Queries q;
    public ConnessioneDB(String url,String user, String password, Queries q){
        this.url=url;
        this.user=user;
        this.password=password;
        this.q =q;
        connectToDB();
    }
    
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
    
    public  String selectPokemon(String ID){
        String sql = "SELECT * FROM pokemon WHERE id = "+ ID;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);//esegui la query
            String output=null;
            while (rs.next()) {
                output="identifier= "+rs.getString("identifier")+" height= " +rs.getString("height")+" weight= "+rs.getString("weight");
            }
            return output;
        } catch (SQLException ex) {
            //System.out.println("ERROR!" + ex.getMessage());
            return "Si e' verificato un errore : "+ex.getMessage();
        }
    }
    
    public String insertPokemon(String nomePok,String altezzaPok,String largezzaPok,String esperienzaBasPok){
        String sql = "INSERT INTO pokemon (identifier, height, weight, base_experience) VALUES ('"+nomePok+"',"+altezzaPok+","+largezzaPok+","+esperienzaBasPok+")";
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);//esegui la query
            return "Pokemon e' stato inserito con successo";
        } catch (SQLException ex) {
            //System.out.println("ERROR!" + ex.getMessage());
            return "Si e' verificato un errore : "+ex.getMessage();
        }
    }
    
    public String deletePokemon( int idPokemon){
        String sql = "DELETE FROM pokemon where id= "+idPokemon;
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);//esegui la query
            return "Pokemon e' stato eliminato con successo";
        } catch (SQLException ex) {
            //System.out.println("ERROR!" + ex.getMessage());
            return "Si e' verificato un errore : "+ex.getMessage();
        }
    }
    
    public String modificaPokemon (int idPok, String cosaCambiare,String valore){
        String sql ;
        if(cosaCambiare=="identifier"){
            sql= "UPDATE pokemon SET "+cosaCambiare+"='"+valore+"' where id= "+idPok;
        }else{
            sql= "UPDATE pokemon SET "+cosaCambiare+"="+parseInt(valore)+" where id= "+idPok;
        }
        try {
            Statement stmt = conn.createStatement();
            int rs = stmt.executeUpdate(sql);//esegui la query
            return "Update avvenuto con il successo";
        } catch (SQLException ex) {
            //System.out.println("ERROR!" + ex.getMessage());
            return "Si e' verificato un errore : "+ex.getMessage();
        }
    }

    @Override
    public void run() {
        switch(q){
            case visualizzaPokemon:
                this.setChanged();
                this.notifyObservers();
            break;
        }
          //  case visualizzaPokemon:
            
         
    }
}
