
package v1;
import java.util.Scanner;

/**
 * The game object, dict every act of the player
 * @author  Benjamin LAMBERT
 * @version 2021.01.18
 */
public class Game
{
    private Room aCurrentRoom;
    private Parser aParser;
    
    /**
     * Create the game
     */
    public Game()
    {
        this.aParser = new Parser();
        this.createRooms();
        this.play();
    } // Game()
    
    /**
     * Creates every room of the game and assign every exists
     */
    private void createRooms()
    {
        Room vOutside = new Room ( "Outside", "outside the main entrance of the university" );
        Room vTheatre = new Room ( "Theatre", "in a lecture theatre" );
        Room vPub = new Room ( "Pub", "in the campus pub" );
        Room vLab = new Room ( "Lab", "in a computing lab" );
        Room vOffice = new Room( "Office", "in the computing admin office" );
        
        vPub.setExits ( null, vOutside, null, null );
        vOutside.setExits ( null, vTheatre, vLab, vPub );
        vTheatre.setExits ( null, null, null, vOutside );
        vLab.setExits (vOutside, vOffice, null, null );
        vOffice.setExits ( null, null, null, vLab );
        
        this.aCurrentRoom = vOutside;
        
        // True map
        
        // The name and the description of every room can be found in the GDD
        Room vCave = new Room ( "Grotte Oubliée", "Une grotte banale d’apparence mais qui renferme toute une société de Prodigiums.\nJ’y ai passé mes 5 dernières années en leur compagnie.\nIl est maintenant temps que je parte à l’aventure et que je me venge !");
        Room vVenandi = new Room ( "Venandi", "On dirait un petit village, principalement constitué de chasseurs de Prodigiums.\nL’odeur de la chair des Prodigiums en putréfaction est insoutenable !");
        Room vForest = new Room ( "Forêt des Asservis", "*vous lisez un panneau à l’entrée* \"Forêt des asservies : défense d’entrer !\" Une forêt magnifique...ment glauque…\nOn dirait bien que c’est le terrain de chasse des gens du village que l’on vient de traverser...");
        Room vRoad1 = new Room ( "Route 1", "C’est la route qui relie Venandi à Quaesti.");
        Room vQuaesti = new Room ( "Quaesti", "*tousse* L’air est irrespirable ici ! ça pourrait expliquer pourquoi tout le monde a l’air malade et pauvre dans le coin.\nC’est certainement une ville d’esclaves. Je ne devrais pas trop m’attarder par ici…");
        Room vRoad2 = new Room ( "Route 2", "On dirait bien que c’est la route entre Quaesti et Dominus.");
        Room vDominus = new Room ( "Dominus", "Ah… L'air est beaucoup plus respirable dans le coin ! Cette ville là, a l’air beaucoup plus riche que celle d’avant…\n Peut-être est-ce la ville des maîtres ? Si c’est le cas, je devrais trouver pas mal de Prodigiums à sauver !");
        Room vDeadForest = new Room ( "Forêt Mortes des Asservis", "On dirait comme une partie de la Forêt des asservies… mais elle est complètement déserte et morte…\n C’était peut-être leur ancien terrain de chasse ?");
        Room vRoad3 = new Room ( "Route 3", "Voilà la route qui relie Dominus à Tenaci !");
        Room vTenaci = new Room ( "Tenaci", "On dirait bien que ce n’est pas réellement une ville ! C’est plus une sorte de zone industrielle.\nIl faut faire cesser l’exploitation des Prodigiums dans le coin ! Je ne repartirai pas tant qu’ils ne seront pas tous libérés.");
        Room vRoad4 = new Room ( "Route 4", "La route qui précède la route 5, il se pourrait bien que je puisse retourner à Quaestui en l’empruntant.");
        Room vRoad5 = new Room ( "Route 5", "Enfin ! Je sens que je touche au but ! C’est cette route qui mène jusqu’au siège de Tenebris !\nIls feraient mieux de se préparer à ma visite...");
        Room vSiege = new Room ( "Siège de Tenebris", "On dirait bien que ma Révolution est en marche ! Et ce n'est que le début...");
        
        // Exits assignement
        vCave.setExits ( null, vVenandi, null, null );
        vVenandi.setExits ( null, vForest, vRoad1, vCave );
        vForest.setExits ( null, null, null, vVenandi );
        vRoad1.setExits ( vVenandi, null, vQuaesti, null );
        vQuaesti.setExits ( vRoad1, vRoad2, null, null );
        vRoad2.setExits ( null, vDominus, null, vQuaesti );
        vDominus.setExits ( vDeadForest, null, vRoad3, vRoad2 );
        vDeadForest.setExits ( vForest, null, vDominus, null );
        vRoad3.setExits ( vDominus, null, vTenaci, null );
        vTenaci.setExits ( vRoad3, null, null, vRoad4 );
        vRoad4.setExits ( vQuaesti, vTenaci, null, vRoad5 );
        vRoad5.setExits ( null, vRoad4, vSiege, null );
        vSiege.setExits ( vRoad5, null, null, null );
        
        // Starting point
        this.aCurrentRoom = vCave;
        this.showDirections();
    } // createRooms()
    
    /**
     * Start the game loop and enable the player to play at the game
     */
    private void play()
    {
        boolean vFinished = false;
        
        while(!vFinished){
            Command vCommand = this.aParser.getCommand();
            vFinished = this.processCommand ( vCommand );
        }
        
        System.out.println("Merci d'avoir joué ! A bientôt.");
    } // play()
    
    /**
     * Show a welcome message and the description of the current room
     */
    private void printWelcome()
    {
        System.out.println("Bienvenue à Terrafernum ! Un planète charmante...\nTapez 'aide' si vous avez besoin d'aide.\n");
        this.showDirections();
    } // printWelcome()
    
    /**
     * Process a command by calling the correct method/function
     * @parma pCommand the command to process
     * @return true if the game is finished
     */
    private boolean processCommand( final Command pCommand )
    {
        if(pCommand.isUnknown()){
            System.out.println("Je ne comprends pas ce que vous dites...");
            return false;
        }
        
        if(pCommand.getCommandWord().equals("go")){
            this.goRoom(pCommand);
            return false;
        }
        else if(pCommand.getCommandWord().equals("help")){
            this.printHelp();
            return false;
        }
        else if(pCommand.getCommandWord().equals("quit")){
            return this.quit(pCommand);
        }
        
        return false;
    } // processCommand(.)
    
    /**
     * Show a help message
     */
    private void printHelp()
    {
        System.out.println("Vous êtes perdu ?");
        System.out.println("Vous n'avez qu'un mot à dire pourtant !\n");
        System.out.print("Essayer de dire : ");
        System.out.println("aller, quitter, aide");
    } // printHelp()
    
    /**
     * Process the quit command
     * @parma pCommand the current command
     * @return true if the command has a second word
     */
    private boolean quit( final Command pCommand )
    {
        if(!pCommand.hasSecondWord()){
            System.out.println("Que voulez-vous quitter ?");
            return false;
        }
        
        return true;
    } // quit(.)
    
    /**
     * Go to the room that the player has specified in the command
     * @param pCommand the player's command, where the direction is
     */
    private void goRoom( final Command pCommand )
    {
        // Verify that there is a direction in the command
        if(!pCommand.hasSecondWord()){
            System.out.println("Où voulez-vous allez ?");
            return;
        }
        
        // Choose the next room by direction
        Room vNextRoom = null;
        String vDirection = pCommand.getSecondWord();
        
        if(vDirection.equals("nord")){
            vNextRoom = this.aCurrentRoom.aNorthExit;
        } else if(vDirection.equals("est")){
            vNextRoom = this.aCurrentRoom.aEastExit;
        } else if(vDirection.equals("sud")){
            vNextRoom = this.aCurrentRoom.aSouthExit;
        } else if(vDirection.equals("ouest")){
            vNextRoom = this.aCurrentRoom.aWestExit;
        } else {
            System.out.println("Comment je suis censé faire pour aller dans cette direction ?!");
        }
        
        // Show a message if there is no room
        if(vNextRoom == null) {
            System.out.println("Je ne vois pas de chemin dans cette direction");
        }
        else { 
            // Go to the next room and show every exit direction
            this.aCurrentRoom = vNextRoom;
            this.showDirections();
        }
    } // goRoom(.)
    
    /**
     * Show every direction available from the current room
     */
    private void showDirections()
    {
        System.out.println( "***** " + this.aCurrentRoom.getName() + " *****\n" + this.aCurrentRoom.getDescription() + "\n" );
        System.out.println( "Vous voyez différents chemins : " );
            
        if(this.aCurrentRoom.aNorthExit != null){
            System.out.println("Nord : " + this.aCurrentRoom.aNorthExit.getName() );
        }
            
        if(this.aCurrentRoom.aEastExit != null){
            System.out.println("Est : " + this.aCurrentRoom.aEastExit.getName() );
        }
            
        if(this.aCurrentRoom.aSouthExit != null){
            System.out.println("Sud : " + this.aCurrentRoom.aSouthExit.getName() );
        }
            
        if(this.aCurrentRoom.aWestExit != null){
            System.out.println("Ouest : " + this.aCurrentRoom.aWestExit.getName() );
        }
        
        System.out.println("");
    } // showDirections()
} // Game
