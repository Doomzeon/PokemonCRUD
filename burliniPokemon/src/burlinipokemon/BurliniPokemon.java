/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burlinipokemon;

/**
 *
 * @author net
 */
public class burliniPokemon {
    public burliniPokemon(){
    }
    
    public static void elencoOpzioni(){
        System.out.println("Benvenuto su Pokemon. Come possiamo aiutarti?");
        System.out.println("1. inserisci un pokemon");
        System.out.println("2. cancella un pokemon");
        System.out.println("3. modifica un pokemon");
        System.out.println("4. seleziona un pokemon");
        System.out.println("5. torna al menu principale");
    }
    

    private static void inserisci(){
        System.out.println("scrivi il nome del pokemon");
        System.out.println("scrivi il peso del pokemon in kg");
        System.out.println("scrivi l’altezza del pokemon in m");
        System.out.println("scrivi il tipo del pokemon");
        System.out.println("sei sicuro di voler agiungere il pokemon?");
    }
     private static void cancella(){
        System.out.println("scrivi il nome del pokemon");
        System.out.println("sei sicuro di voler eliminare il pokemon");
    }
      private static void modifica(){
        System.out.println("scrivi il nome, il peso, l’altezza e il tipo del pokemon  da modificare");
        
    }
       private static void seleziona(){
        System.out.println("scrivi il nome del pokemon da selezionare");
        
    }
        private static void torna(){
        System.out.println("vuoi tornare ");
        
    }
    private static void menuPokemon(){
        int choose = 5;
        
        switch (choose) {
            case 1: 
                inserisci();
                break;
            case 2: 
                cancella();
                break;
            case 3:  
                modifica();
                break;
            case 4: 
                seleziona();
                break;
            case 5:  
                torna();
                break;
        }
        System.out.println(monthString);
    }
}
    
    
}
