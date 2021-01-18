import org.json.JSONObject;
import java.io.FileWriter;

/**
 * A Save Manager, to save and load game files
 * @author  Benjamin LAMBERT
 * @version 2021.09.01
 */
public class SaveManager
{
    
    /**
     * Create the default save manager
     */
    public SaveManager()
    {
        
    } // SaveManager()

    /**
     * Save the current game state
     */
    public void SaveGame(final String pFilePath, final Player pPlayer, final Map pMap)
    {
        final String vGameDataInJson = new GameData(pPlayer, pMap).toJson();
    }

    /**
     * Get the state of the last game
     * @return the game data
     */
    public GameData LoadGame(final String pFilePath)
    {
        return this.fromJson( "" );
    }

    /**
     * Get the game data from the JSON file
     * @return the game data
     */
    private GameData fromJson(final String json)
    {

    }
}

private class GameData
{
    private Player aPlayer;
    private Map aMap;

    /**
     * Create the game data object
     */
    public GameData(final Player pPlayer, final Map pMap)
    {
        this.aPlayer = pPlayer;
        this.aMap = pMap;
    } // GameData( params )

    /**
     * Get the Game data to json format
     * @return the game data to JSON object
     */
    public String toJson()
    {
        JSONObject vData = new JSONObject();
        vData.put("player", this.aPlayer.toJSONObject());
        vData.put("map", this.aMap.toJSONObject());
    }

    /**
     * Get the player
     * @return player
     */
    public Player getPlayer()
    {
        return this.aPlayer;
    }

    /**
     * Get the map
     * @return map
     */
    public Map getMap()
    {
        return this.aMap;
    }
}