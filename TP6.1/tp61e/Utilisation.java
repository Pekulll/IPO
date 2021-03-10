/**
 * Decrivez votre classe Utilisation ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Utilisation
{
    public static void essai()
    {
        Inventaire vInv = new Inventaire();
        vInv.ajouteItem("Test", 16);
        System.out.println(vInv.toString());
        vInv.ajouteItem("Test", 7);
        System.out.println(vInv.toString());
        vInv.ajouteItem("WIP", 5);
        vInv.trieC();
        
        System.out.println(vInv.contientItem("Test"));
        System.out.println(vInv.contientItem("???"));
        
        System.out.println(vInv.toString());
        
        vInv.enleveItem("???");
        System.out.println(vInv.toString());
        vInv.enleveItem("Test");
        System.out.println(vInv.toString());
        vInv.enleveItem("WIP");
        System.out.println(vInv.toString());
        vInv.enleveItem("WIP");
        System.out.println(vInv.toString());
        
        vInv.ajouteItem("ananas", 7);
        vInv.ajouteItem("abricots", 7);
        vInv.ajouteItem("cerises", 9);
        vInv.ajouteItem("banane", 8);
        System.out.println(vInv.toString());
        vInv.trieC();
        System.out.println(vInv.toString());
        vInv.trieD();
        System.out.println(vInv.toString());
    }
} // Utilisation
