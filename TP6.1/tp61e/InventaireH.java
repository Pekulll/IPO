import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Comparator;

/**
 * Decrivez votre classe Inventaire ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class InventaireH
{
    private Map<String, Item> aListe;
    private int aPrixTotal;
    
    public InventaireH()
    {
        this.aListe = new HashMap<String, Item>();
        this.aPrixTotal = 0;
    }
    
    public Item getItem( final String pName )
    {
        return this.aListe.get(pName);
    }
    
    public boolean contientItem( final String pName )
    {
        return this.aListe.containsKey(pName);
    }
    
    public void ajouteItem( final String pName, final int pPrice )
    {
        this.aListe.put(pName, new Item(pName, pPrice));
        this.aPrixTotal += pPrice;
    }
    
    @Override
    public String toString()
    {
        return this.aListe.values().toString() + " : " + this.aPrixTotal;
    }
    
    public void enleveItem( final String pName )
    {
        this.aListe.remove(pName);
    }
} // InventaireH