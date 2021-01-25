/**
 * Decrivez votre classe Calc ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Calc
{
    public static void test ()
    {
        System.out.println("" + (2 + 3 / 4));
        System.out.println("" + (2.0 + 3 / 4));
        System.out.println("" + (2 + 30e-1 / 4));
        System.out.println("" + (11 % 4));
        
        System.out.println("" + Math.cos(Math.PI / 4) + "==" + Math.sin(Math.PI / 4) + " : " + (Math.cos(Math.PI / 4) == Math.sin(Math.PI / 4)));
    }
    
    public static double racNeg ( final double x)
    {
        if(x >= 0){
            return Math.sqrt(x);
        }else{
            return -Math.sqrt(-x);
        }
    }
    
    public static void afficheMoities ( final int x )
    {
        for(int i = x; i >= 1; i /= 2){
            System.out.println("" + i);
        }
    }
    
    public static boolean sontProches ( final double x, final double y )
    {
        return Math.abs(x - y) <= 10e-9;
    }
} // Calc
