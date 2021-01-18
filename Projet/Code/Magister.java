import java.io.*;

/**
 * A Magister (=Prodigium Owner)
 * @author  Benjamin LAMBERT
 * @version 2021.09.02
 */
public class Magister
{

    private String aName;
    private int aLevel;
    private Prodigium[] aTeam;

    /**
     * Create a default Magister
     */
    public Magister()
    {
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
    public Magister(final String pName, final int pLevel, final Prodigium[] pTeam)
    {
        this.aName = pName;
        this.aLevel = pLevel;
        this.aTeam = pTeam;
    } // Magister( params )

    /**
     * Get the magister's name
     * @return the name of the Magister
     */
    public String getName()
    {
        return this.aName;
    }

    /**
     * Get the level of the Magister
     * @return the level of the Magister
     */
    public int getLevel()
    {
        return this.aLevel;
    }

    /**
     * Get the team of the Magister
     * @return the team of the Magister
     */
    public Prodigium[] getTeam()
    {
        return this.aTeam;
    }
}