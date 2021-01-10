import java.io.*;

import org.json.JSONObject;
import java.io.FileWriter;

/**
 * A Save Manager, to save and load game files
 * @author  Benjamin LAMBERT
 * @version 2021.09.01
 */
public class SaveManager{
    
    /**
     * Create the default save manager
     */
    public SaveManager(){
        
    } // SaveManager()

    public int SaveGame(final String pFilePath, final Player pPlayer, final Map pMap){
        final String vGameDataInJson = new GameData(pPlayer, pMap).toJson();
    }

    public GameData LoadGame(final String pFilePath){
        return this.fromJson( // Json file content );
    }

    private GameData fromJson(final String json){

    }
}

public class GameData{

    private Player aPlayer;
    private Map aMap;

    /**
     * Create the game data object
     */
    public GameData(final Player pPlayer, final Map pMap){
        this.aPlayer = pPlayer;
        this.aMap = pMap;
    } // GameData( params )

    public String toJson(){
        JSONObject vData = new JSONObject();
        vData.put("player", this.aPlayer.toJSONObject());
        vData.put("map", this.aMap.toJSONObject());
    }

    public Player getPlayer(){
        return this.aPlayer;
    }

    public Map getMap(){
        return this.aMap;
    }
}