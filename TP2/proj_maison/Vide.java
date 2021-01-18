/**
 * Decrivez votre classe Vide ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Vide
{
    /**
     * Constructeur par defaut d'objets de la classe Vide
     */
    public Vide()
    {
        
    } // Vide()
    
    private long factorielle(final int pN)
    {
        if(pN == 0){
            return 1;
        }
        else{
            return pN * this.factorielle(pN - 1);
        }
    }
    
    public void afficheFactorielle(int pN)
    {
        System.out.println("=" + this.factorielle(pN));
    }
} // Vide
