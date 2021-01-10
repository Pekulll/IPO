import java.io.*;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * A Player
 * @author  Benjamin LAMBERT
 * @version 2021.09.01
 */
public class Player extends Magister{

    private Room aCurrentRoom;

    /**
     * Create a default player
     */
    public Player(){
        this.aName = "Player";
        this.aTeam = new Prodigium[0];
        this.aCurrentRoom = null;
    } // Player()

    public Room getRoom(){
        return this.currentRoom;
    }

    public JSONObject toJSONObject(){
        JSONObject vPlayer = new JSONObject();

        vPlayer.put("name", this.aPlayer.getName());
        vPlayer.put("level", this.aPlayer.getLevel());

        JSONArray vTeam = new JSONArray();

        for(Prodigium vProdigium : this.aPlayer.getTeam()){
            vTeam.add(vProdigium.toJSONObject());
        }

        return vPlayer;
    }
}