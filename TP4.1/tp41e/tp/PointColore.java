package tp;


/**
 * Decrivez votre classe PointColore ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class PointColore extends Point
{
    private String aCouleur;
    
    public PointColore ( final int pX, final int pY, final String pCouleur )
    {
        super(pX, pY);
        this.aCouleur = pCouleur;
    }
    
    public PointColore ( final int pX, final int pY )
    {
        this(pX, pY, "N");
    }
    
    public PointColore ()
    {
        this(10,10, "N");
    }
    
    @Override public String toString()
    {
        return this.aCouleur + ":" + super.toString(); 
    }
    
    @Override public boolean equals( final Object pObj )
    {
        return super.equals(pObj);
    }
} // PointColore
