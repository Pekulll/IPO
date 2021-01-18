package v1;

/**
 * The game object, dict every act of the player
 * @author  Benjamin LAMBERT
 * @version 2021.01.18
 */
public class Game
{
    private Room aCurrentRoom;
    
    /**
     * Create the game
     */
    public Game()
    {
        this.createRooms();
    } // Game()
    
    /**
     * Creates every room of the game and assign every exists
     */
    private void createRooms()
    {
        Room vOutside = new Room ( "outside the main entrance of the university" );
        Room vTheatre = new Room ( "in a lecture theatre" );
        Room vPub = new Room ( "in the campus pub" );
        Room vLab = new Room ( "in a computing lab" );
        Room vOffice = new Room( "in the computing admin office" );
        
        vPub.setExits ( null, vOutside, null, null );
        vOutside.setExits ( null, vTheatre, vLab, vPub );
        vTheatre.setExits ( null, null, null, vOutside );
        vLab.setExits (vOutside, vOffice, null, null );
        vOffice.setExits ( null, null, null, vLab );
        
        this.aCurrentRoom = vOutside;
    } // createRooms()
    
    /**
     * Go to the room that the player has specified in the command
     * @param pCommand the player's command, where the direction is
     */
    private void goRoom( final Command pCommand )
    {
        // Verify that there is a direction in the command
        if(!pCommand.hasSecondWord()){
            System.out.println("Go where?");
            return;
        }
        
        // Choose the next room by direction
        Room vNextRoom = null;
        String vDirection = pCommand.getSecondWord();
        
        if(vDirection.equals("north")){
            vNextRoom = this.aCurrentRoom.aNorthExit;
        } else if(vDirection.equals("east")){
            vNextRoom = this.aCurrentRoom.aEastExit;
        } else if(vDirection.equals("south")){
            vNextRoom = this.aCurrentRoom.aSouthExit;
        } else if(vDirection.equals("west")){
            vNextRoom = this.aCurrentRoom.aWestExit;
        } else {
            System.out.println("Unknown direction!");
        }
        
        // Show a message if there is no room
        if(vNextRoom == null) {
            System.out.println("There is no door");
        }
        else { // Go to the next room and show every exit direction
            this.aCurrentRoom = vNextRoom;
            System.out.println( "You are " + this.aCurrentRoom.getDescription() );
            System.out.print( "Exits: " );
            
            if(this.aCurrentRoom.aNorthExit != null){
                System.out.print("north ");
            }
            
            if(this.aCurrentRoom.aEastExit != null){
                System.out.print("east ");
            }
            
            if(this.aCurrentRoom.aSouthExit != null){
                System.out.print("south ");
            }
            
            if(this.aCurrentRoom.aWestExit != null){
                System.out.print("west");
            }
            
            System.out.println("");
        }
    } // goRoom(.)
} // Game
