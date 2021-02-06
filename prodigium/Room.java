import java.util.HashMap;
import java.util.Set;

/**
 * A game room, where the player can be
 * @author  Benjamin LAMBERT
 * @version 2021.02.06
 */
public class Room
{
    private String aName;
    private String aDescription;
    
    private HashMap<String, Room> aExits;
    
    /**
     * Create a Room object
     * @param pName the name of the room
     * @param pDescription the description of the room
     */
    public Room( final String pName, final String pDescription )
    {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();
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
     * @return the room's description
     */
    public String getDescription()
    {
        return this.aDescription;
    } // getDescription()
    
    /**
     * Get the long description of the current room
     * It's composed of the name, the description and the exits of the room
     * @return the room's long description
     */
    public String getLongDescription()
    {
        String vLongDescription = "***** " + this.getName() + " *****\n";
        vLongDescription += this.getDescription() + "\n";
        vLongDescription += this.getExitString() + "\n";
        
        return vLongDescription;
    } // getLongDescription()
    
    /**
     * Set every common exits of the current room
     * @param pNorth north exit
     * @param pEast east exit
     * @param pSouth south exit
     * @param pWest west exit
     */
    public void setExits ( final Room pNorth, final Room pEast, final Room pSouth, final Room pWest )
    {
        this.setExit ( "nord", pNorth );
        this.setExit ( "est", pEast );
        this.setExit ( "sud", pSouth );
        this.setExit ( "ouest", pWest );
    } // setExits(....)
    
    /**
     * Set an exit in a specific direction
     * @param pDirection direction of the exit
     * @param pRoom the exit
     */
    public void setExit( final String pDirection, final Room pRoom )
    {
        if(pRoom != null){
            this.aExits.put( pDirection, pRoom );
        }
    } // setExit(..)
    
    /**
     * Get exit from a direction
     * @param pDirection exit direction
     * @return the exit room or null
     */
    public Room getExit ( final String pDirection )
    {
        return this.aExits.get(pDirection);
    } // getExit(.)
    
    /**
     * Show every direction available from this room
     * @return the serialized String with every exits
     */
    public String getExitString ()
    {
        String vExit = "Vous voyez différents chemins :\n";
        Set<String> vKeys = this.aExits.keySet();
        
        for ( String key : vKeys ){
            vExit += key + " : " + this.aExits.get(key).getName() + "\n";
        }
        
        return vExit;
    } // showDirections()
} // Room
