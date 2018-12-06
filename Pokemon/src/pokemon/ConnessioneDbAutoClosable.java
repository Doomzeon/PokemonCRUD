/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author net
 */
public class ConnessioneDbAutoClosable  extends Observable implements  Runnable  {
    private String url ="jdbc:mysql://localhost:3306/pokemon";
    private Connection con;
    private Statement statement;
    private Queries q;
    private  BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
    
    public ConnessioneDbAutoClosable (Queries tipoQuery) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
             con = DriverManager.getConnection(url,"root","");
             statement = con.createStatement();
             this.q=tipoQuery;
        } finally {
             // Avoid leak if an exception was thrown in createStatement
             if (statement == null) {
                 con.close();
             }
        }
    }

    private StringBuilder visualizza(String ID) throws SQLException {
        PreparedStatement stmt=con.prepareStatement((String) MappeHash.getMappeHash().get(Queries.visualizzaPokemon));  
        stmt.setString(1,ID);
        // execute select SQL stetement
        ResultSet rs = stmt.executeQuery();
        StringBuilder out=new StringBuilder("");
        while (rs.next()) {
                //out="identifier= "+rs.getString("identifier")+" height= " +rs.getString("height")+" weight= "+rs.getString("weight");
                out.append("identifier =");
                out.append(rs.getString("identifier"));
                out.append(" height =");
                out.append(rs.getString("height"));
                out.append(" weight =");
                out.append(rs.getString("weight"));
                
            }
        return out;
    }
    
    public StringBuilder getvisualizzaQuery(String s){
        StringBuilder d = null;
        try {
           d= visualizza(s);
        } catch (SQLException ex) {
            Logger.getLogger(ConnessioneDbAutoClosable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public void openCOnnection(){
        
    }
    
    
    public void close() throws SQLException {
        con.close();
    }

    @Override
    public void run() {
        switch(q){
            case visualizzaPokemon:
                    {
                        StringBuilder s =getvisualizzaQuery("5");
                        // System.out.println(getvisualizzaQuery("5"));
                        
                    }
                break;
        }
        
        
    }
    
    
    
}
