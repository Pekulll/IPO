/**
 * Decrivez votre classe Tab ici.
 * 
 * @author (votre nom) 
 * @version (un numero de version ou une date)
 */
public class Tab
{
    public static void affTab ( final int[] pTab )
    {
        for(int i : pTab){
            System.out.println("" + i);
        }
    }
    
    public static void affTabInv( final int[] pTab )
    {
        for(int i = pTab.length - 1; i >= 0; i--){
            System.out.println("" + pTab[i]);
        }
    }
    
    public static void initTab ( final int[] pTab )
    {
        for(int i = 0; i < pTab.length; i++){
            pTab[i] = i * 2;
        }
    }
    
    public static int somme( final int[] pTab )
    {
        int s = 0;
        
        for(int i : pTab){
            s += i;
        }
        
        return s;
    }
    
    public static void essai()
    {
        int[] vTab = new int[5];
        Tab.initTab(vTab);
        Tab.affTab(vTab);
        Tab.affTabInv(vTab);
        Tab.somme(vTab);
    }
} // Tab
