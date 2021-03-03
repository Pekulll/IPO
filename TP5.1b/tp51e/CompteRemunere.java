/**
 * @author Benjamin LAMBERT 
 * @version 2021.03.03
 */
public class CompteRemunere extends Compte
{
    private double aTaux;
    
    public CompteRemunere( final double pSolde, final double pTaux )
    {
        super(pSolde);
        this.aTaux = pTaux;
    }
    
    @Override public void capitaliseUnAn()
    {
        this.credite( this.getSolde() * this.aTaux / 100 );
    }
    
    public double getTaux()
    {
        return this.aTaux;
    }
} // CompteRemunere
