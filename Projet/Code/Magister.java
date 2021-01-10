import java.io.*;

/**
 * A Magister (=Prodigium Owner)
 * @author  Benjamin LAMBERT
 * @version 2021.09.02
 */
public class Magister{

    private String aName;
    private int aLevel;
    private Prodigium[] aTeam;

    /**
     * Create a default Magister
     */
    public Magister(){
        this.aName = "Magister";
        this.aLevel = 0;
        this.aTeam = new Prodigium[0];
    } // Magister()

    /**
     * Create a custom Magister
     * @param pName  the name of the Magister
     * @param pLevel the level of the Magister
     * @param pTeam  the team of the Magister
     */
    public Magister(final String pName, final int pLevel, final Prodigium[] pTeam){
        this.aName = pName;
        this.aLevel = pLevel;
        this.aTeam = pTeam;
    } // Magister( params )

    public String getName(){
        return this.aName;
    }

    public int getLevel(){
        return this.aLevel;
    }

    public Prodigium[] getTeam(){
        return this.aTeam;
    }
}