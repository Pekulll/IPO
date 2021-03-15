import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collections;

/**
 * Decrivez votre classe Inventaire ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Inventaire
{
    private List<Item> aListe;
    private int aPrixTotal;
    
    public Inventaire()
    {
        this.aListe = new ArrayList<Item>();
        this.aPrixTotal = 0;
    }
    
    public List<Item> getItems()
    {
        return this.aListe;
    }
    
    public Item getItem( final String pName )
    {
        for(Item item : this.aListe){
            if(item.getNom().equals(pName)){
                return item;
            }
        }
        
        return null;
    }
    
    public boolean contientItem( final String pName )
    {
        return this.getItem(pName) != null;
    }
    
    public void ajouteItem( final String pName, final int pPrice )
    {
        this.aListe.add(new Item(pName, pPrice));
        this.aPrixTotal += pPrice;
    }
    
    @Override
    public String toString()
    {
        return this.aListe.toString() + " : " + this.aPrixTotal;
    }
    
    public void enleveItem( final String pName )
    {
        Iterator<Item> vIterator = this.aListe.iterator();
        
        while(vIterator.hasNext()){
            Item vItem = vIterator.next();
            
            if(vItem.getNom().equals(pName)){
                vIterator.remove();
                this.aPrixTotal -= vItem.getPrix();
            }
        }
    }
    
    public void trieC()
    {
        this.aListe.sort(null);
    }
    
    public void trieD()
    {
        this.aListe.sort(Collections.reverseOrder());
    }
    
    public boolean equals( final Inventaire pInv )
    {
        return this.aListe.equals(pInv.getItems());
    }
} // Inventaire
