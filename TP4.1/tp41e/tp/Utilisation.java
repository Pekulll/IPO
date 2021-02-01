package tp;


/**
 * Decrivez votre classe Utilisation ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Utilisation
{
    public static void essai()
    {
        Point vPoint = new Point(10,15);
        PointColore vPointColore = new PointColore(23, 12, "R");
        
        vPoint.affiche();
        
        vPointColore.affiche();
        vPointColore.deplace(10, 20);
        vPointColore.affiche();
        
        System.out.println("" + vPoint.equals(null));
        System.out.println("" + vPoint.equals(new Object()));
        System.out.println("" + vPoint.equals(new Point(34, 65)));
        System.out.println("" + vPoint.equals(vPoint));
        
        System.out.println("" + vPointColore.equals(null));
        System.out.println("" + vPointColore.equals(new Object()));
        System.out.println("" + vPointColore.equals(new PointColore(0, 0, "Y")));
        System.out.println("" + vPointColore.equals(vPointColore));
    }
} // Utilisation
