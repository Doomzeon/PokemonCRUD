/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokemon;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author net
 */
public class MappeHash {
   // private static MappeHash istanza=null;
    
    private static Map<Queries, String> query =null;
    private MappeHash(){}
    
    public static synchronized Map getMappeHash(){
        if(query==null){            
            query=new HashMap<>();
            initQuery();
        }
        return query;
    }
    
    private static void initQuery() {
        query.put(Queries.visualizzaPokemon, "SELECT * FROM pokemon where ID=?");
  }
   
    
}
