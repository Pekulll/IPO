import org.json.JSONObject;
import org.json.JSONArray;

/**
 * A Map
 * @author  Benjamin LAMBERT
 * @version 2021.09.01
 */
public class Map
{

    private Room[] aRooms;

    /**
     * Create a map
     * @param pRoomNumber the number of the room that the map contain
     */
    public Map(final int pRoomNumber)
    {
        this.aRooms = new Room[pRoomNumber];
    } // Map( params )

    /**
     * Get the map as a JSON object
     * @return the map to JSON object
     */
    public JSONObject toJSONObject()
    {
        JSONObject vMap = new JSONObject();
        JSONArray vRooms = new JSONArray();

        for(Room vRoom : this.aRooms){
            vRooms.add(vRoom.toJSONObject());
        }
    }
}