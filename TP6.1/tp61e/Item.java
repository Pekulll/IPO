/** version etudiants
 * TP6
 * @author DB
 * @version 2020.02
 */
public class Item implements Comparable
{
    private String aNom;
    private int    aPrix;

    public Item( final String pNom, final int pPrix )
    {
        this.aNom  = pNom;
        this.aPrix = pPrix;
    } // Item(..)
    
    public String getNom()
    {
        return this.aNom;
    } // getNom()
    
    public int getPrix()
    {
        return this.aPrix;
    } // getPrix()

    @Override
    public String toString()
    {
        return this.aNom + " (" + this.aPrix + "€)";
    }
    
    public int compareTo( final Object pItem )
    {
        Integer vPrice = this.aPrix;
        Item vItem = (Item)pItem;
        
        if(vPrice.compareTo(vItem.getPrix()) == 0){
            return this.aNom.compareTo(vItem.getNom());
        }else{
            return vPrice.compareTo(vItem.getPrix());
        }
    }
    
    public boolean equals( final Item pItem )
    {
        return this.aPrix == pItem.getPrix() && this.aNom == pItem.getNom();
    }
} // Item
