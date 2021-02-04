/**
 * A game room, where the player can be
 * @author  Benjamin LAMBERT
 * @version 2021.01.18
 */
public class Room
{
    private String aName;
    private String aDescription;
    
    private Room aNorthExit;
    private Room aEastExit;
    private Room aSouthExit;
    private Room aWestExit;
    
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
    
    /**
     * Get exit from a direction
     * @param pDirection exit direction
     * @return the exit room or null
     */
    public Room getExit ( final String pDirection )
    {
        if(pDirection.equals("nord")){
            return this.aNorthExit;
        } else if(pDirection.equals("est")){
            return this.aEastExit;
        } else if(pDirection.equals("sud")){
            return this.aSouthExit;
        } else if(pDirection.equals("ouest")){
            return this.aWestExit;
        } else {
            System.out.println("Comment je suis censé faire pour aller dans cette direction ?!");
            return null;
        }
    } // getExit(.)
    
    /**
     * Show every direction available from this room
     */
    public void showDirections ()
    {
        if(this.aNorthExit != null){
            System.out.println("Nord : " + this.aNorthExit.getName() );
        }
            
        if(this.aEastExit != null){
            System.out.println("Est : " + this.aEastExit.getName() );
        }
            
        if(this.aSouthExit != null){
            System.out.println("Sud : " + this.aSouthExit.getName() );
        }
            
        if(this.aWestExit != null){
            System.out.println("Ouest : " + this.aWestExit.getName() );
        }
    } // showDirections()
} // Room
