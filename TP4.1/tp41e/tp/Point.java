
package tp;


/**
 * Decrivez votre classe Point ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Point
{
    private int aX;
    private int aY;
    
    public Point ( final int pX, final int pY )
    {
        this.aX = pX;
        this.aY = pY;
    }
    
    public Point ()
    {
        this(10,10);
    }
    
    public void deplace ( final int pDeltaX, final int pDeltaY )
    {
        this.aX += pDeltaX;
        this.aY += pDeltaY;
    }
    
    @Override public String toString()
    {
        return "(" + this.aX + "," + this.aY + ")";
    }
    
    public void affiche()
    {
        System.out.println(this.toString());
    }
    
    @Override public boolean equals( final Object pObj )
    {
        if(this == pObj){
            return true;
        }
        
        if(pObj == null){
            return false;
        }
        
        if(pObj.getClass() != this.getClass()){
            return false;
        }
        
        Point vPoint = (Point)pObj;
        return this.aX == vPoint.aX && this.aY == vPoint.aY;
    }
} // Point
