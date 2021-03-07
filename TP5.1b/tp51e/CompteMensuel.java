/**
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class CompteMensuel extends CompteRemunere
{
    public CompteMensuel( final double pSolde, final double pTaux )
    {
        super(pSolde, pTaux);
    }
    
    @Override public void capitaliseUnAn()
    {
        this.capitaliseMois(12);
    }
    
    private void capitaliseMois( final int pNbMois )
    {
        if(pNbMois <= 0) return;
        
        this.credite( this.getSolde() * this.getTaux() / 100 );
        this.capitaliseMois(pNbMois - 1);
    }
} // CompteMensuel
