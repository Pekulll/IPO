/**
 * Decrivez votre classe Compte ici.
 * 
 * @author Benjamin LAMBERT
 * @version 2021.03.03
 */
public abstract class Compte implements Comparable
{
    private double aSolde;
    
    public Compte( final double pSolde )
    {
        this.affecte(pSolde);
    }
    
    public double getSolde()
    {
        return this.aSolde;
    }
    
    private void affecte( final double pValue)
    {
        this.aSolde = arrondi2(pValue);
    }
    
    public void debite( final double pValue )
    {
        this.affecte( this.aSolde - pValue );
    }
    
    public void credite( final double pValue )
    {
        this.affecte( this.aSolde + pValue );
    }
    
    public abstract void capitaliseUnAn();
    
    public void bilanAnnuel()
    {
        System.out.print("solde=" + this.aSolde);
        this.capitaliseUnAn();
        System.out.println(" / après capitalisation, solde=" + this.aSolde);
    }
        
    
    private static double arrondi2( final double pR )
    {
        double vR = Math.abs( pR );
        int    vI = (int)(vR * 1000);
        if ( vI%10 >= 5 )  vR = vR + 0.01;
        return Math.copySign( ((int)(vR*100))/100.0, pR );
    } // arrondi2(.)
    
    public int compareTo(Object pObject)
    {
        Double pDouble = new Double(this.aSolde);
        return pDouble.compareTo(((Compte)pObject).getSolde());
    }
} // Compte
