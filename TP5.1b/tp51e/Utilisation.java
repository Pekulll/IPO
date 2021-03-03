/**
 * @author Benjamin LAMBERT
 * @version 2021.03.03
 */
public class Utilisation
{
    public static void essai()
    {
        CompteCourant vC = new CompteCourant(1000.0);
        CompteAnnuel vA = new CompteAnnuel(1000.0, 6.0);
        CompteMensuel vM = new CompteMensuel(1000.0, 0.5);
        CompteCourant vC2 = new CompteCourant(1060.0);
        Compte[] vComptes = {vC, vA, vM};
        
        System.out.println(vC.compareTo(vA));
        System.out.println(vC.compareTo(vM));
        System.out.println(vA.compareTo(vM));
        
        for(Compte c : vComptes){
            c.bilanAnnuel();
        }
        
        System.out.println(vC2.compareTo(vC));
        System.out.println(vC2.compareTo(vA));
        System.out.println(vC2.compareTo(vM));
    }
} // Utilisation
