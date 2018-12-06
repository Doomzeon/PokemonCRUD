/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author net
 */
public class MenuPrincipale implements Observer {
    ConnessioneDbAutoClosable con;
    public  BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
    private ConnessioneDB database;
    public MenuPrincipale(){
       // database=new ConnessioneDB("jdbc:mysql://localhost:3306/pokemon", "root", "");
        
    }
    
    public  void elencoOpzioni(){
        System.out.println("Benvenuto su PokemonG. Come possiamo aiutarti?");
        System.out.println("1. Visualizza un pokemon");
        System.out.println("2. Aggiungi un pokemon");
        System.out.println("3. Elimina un pokemon");
        System.out.println("4. Modifica un pokemon");
        System.out.println("0. Esci");
        richiestaUtente(parseInt(getElemTastiera()));
    }
    public  void richiestaUtente(int n){
        if(n==1){
            try {
                visualizzaPokemon();
            } catch (SQLException ex) {
                Logger.getLogger(MenuPrincipale.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(MenuPrincipale.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(n==2){
            aggiungiPokemon();
        }else if(n==3){
            eliminaPokemon();
        }else if(n==4){
            modificaPokemon();
        }else if(n==7){
        }else if(n==0){
            return;
        }else{
            erroreInserimento();
        }
    }
    
    private void visualizzaQuery() throws SQLException{
        
    }
    
    private  void aggiungiPokemon(){
        
        System.out.println("Inserisci il nome del pokemon");
        String nomePok=getElemTastiera();
        
        System.out.println("Inserisci l'altezza del pokemon");
        String altezzaPok=getElemTastiera();
        
        System.out.println("Inserisci largezza del pokemon");
        String largezzaPok=getElemTastiera();
        
        System.out.println("Inserisci esperienza di base del pokemon");
        String esperienzaBasPok=getElemTastiera();
        
        System.out.println(database.insertPokemon(nomePok,altezzaPok,largezzaPok,esperienzaBasPok));
        
        System.out.println("9. Torna al menu principale");
        if(getElemTastiera().equals("9"))
            elencoOpzioni();
        else
            erroreInserimento();
    }
    
    private  void aggiungiPokemon2(){
       ConnessioneDB con = new ConnessioneDB("jdbc:mysql://localhost:3306/pokemon","","",Queries.visualizzaPokemon);
       con.addObserver(this);
       Thread t  = new Thread(con);
       t.start();
    }
    private void eliminaPokemon(){
        System.out.println("Inserisci l'ID del pokemon");
        
        System.out.println(database.deletePokemon(parseInt(getElemTastiera())));
                
        System.out.println("9. Torna al menu principale");
        if(getElemTastiera().equals("9"))
            elencoOpzioni();
        else
            erroreInserimento();
    }
    private void modificaPokemon(){
        System.out.println("Inserisci l'ID del pokemon");
        String idPok=getElemTastiera();
        System.out.println("Il pokemon sul quale vuoi fare la modifica e' = "+database.selectPokemon(idPok));
        opzioniDiModificaPokemon(idPok);
        System.out.println("9. Torna al menu principale");
        if(getElemTastiera().equals("9"))
            elencoOpzioni();
        else
            erroreInserimento();
    }
    private void opzioniDiModificaPokemon(String idPok){
        System.out.println("Scegli cosa vuoi cambiare in questo pokemon");
        System.out.println("1. Nome del pokemon");
        System.out.println("2. Altezza del pokemon");
        System.out.println("3. Peso del pokemon");
        System.out.println("4. Esperienza di base del pokemon");
        
        String opzioneScelta=getElemTastiera();
        
        if(opzioneScelta.equals("1")){
            System.out.println("Inserisci nuovo nome del pokemon");
            String val=getElemTastiera();
            database.modificaPokemon(parseInt(idPok),"identifier",val);
        }else if(opzioneScelta.equals("2")){
            System.out.println("Inserisci nuovo valore di altezza");
            String val=getElemTastiera();
            database.modificaPokemon(parseInt(idPok),"height",val);
        }else if(opzioneScelta.equals("3")){
            System.out.println("Inserisci nuovo valore largezza");
            String val=getElemTastiera();
            database.modificaPokemon(parseInt(idPok),"weight",val);
        }else if(opzioneScelta.equals("4")){
            System.out.println("Inserisci nuovo valore di esperienza di base");
            String val=getElemTastiera();
            database.modificaPokemon(parseInt(idPok),"base_experience",val);
        }else{
            erroreInserimento();
        }
    }
    private void erroreInserimento(){
        System.out.println("Attenzione hai inserito un valore non valido, sarai ritornato al menu principale ");
        elencoOpzioni();
    }
    private  void visualizzaPokemon() throws SQLException, ClassNotFoundException{
        System.out.println("Inserisci l'ID del pokemon");
       //String str = getElemTastiera();
       
               ConnessioneDbAutoClosable con = new ConnessioneDbAutoClosable(Queries.visualizzaPokemon);
                con.addObserver(this);
                Thread t  = new Thread( con);
                t.start();
                
                //System.out.println(con.visualizza(getElemTastiera()));
        
       
        
        System.out.println("9. Torna al menu principale");
        if(getElemTastiera().equals("9"))
            elencoOpzioni();
        else
            erroreInserimento();
    }
    private String getElemTastiera(){
        String num=null;
        try {
            num = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipale.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return num;
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
    
    
    
}
