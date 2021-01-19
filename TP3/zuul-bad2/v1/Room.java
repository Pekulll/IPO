package v1;

/**
 * A game room, where the player can be
 * @author  Benjamin LAMBERT
 * @version 2021.01.18
 */
public class Room
{
    private String aName;
    private String aDescription;
    
    public Room aNorthExit;
    public Room aEastExit;
    public Room aSouthExit;
    public Room aWestExit;
    
    /**
     * Create a Room object
     * @param pName the name of the room
     * @param pDescription the description of the room
     */
    public Room( final String pName, final String pDescription )
    {
        this.aName = pName;
        this.aDescription = pDescription;
    } // Room(..)
    
    /**
     * Get the name of the current room
     * @return String room's name
     */
    public String getName()
    {
        return this.aName;
    } // getName()
    
    /**
     * Get the description of the current room
     * @return String room's description
     */
    public String getDescription()
    {
        return this.aDescription;
    } // getDescription()
    
    /**
     * Set every exits of the current room
     * @param pNorth north exit
     * @param pEast east exit
     * @param pSouth south exit
     * @param pWest west exit
     */
    public void setExits ( final Room pNorth, final Room pEast, final Room pSouth, final Room pWest )
    {
        this.aNorthExit = pNorth;
        this.aEastExit = pEast;
        this.aSouthExit = pSouth;
        this.aWestExit = pWest;
    } // setExits(....)  
} // Room
