import org.json.JSONObject;

/**
 * A Prodigium (=mystical creature)
 * @author  Benjamin LAMBERT
 * @version 2021.09.01
 */
public class Prodigium
{
    private String aName;
    
    private int aHealth, aMaxHealth;
    private int aEnergy, aMaxEnergy;

    private int aLevel;
    private int aXP;
    private int aMaxXP;

    private int aID;
    private Type aType;

    /**
     * Create a Prodigium with default properties
     */
    public Prodigium()
    {
        this.aName = "UNKNOW";

        this.aHealth = 5;
        this.aMaxHealth = 5;
        this.aEnergy = 5;
        this.aMaxEnergy = 5;

        this.aLevel = 5;
        this.aXP = 0;
        this.aMaxXP = 63;

        this.aID = 0;
        this.aType = Type.Earth;
    } // Prodigium()

    /**
     * Create a Prodigium with custom properties
     * @param pName   the name of the Prodigium
     * @param pHealth the maximum health of the Prodigium
     * @param pEnergy the maximum amount of energy of the Prodigium
     * @param pID     the ID of the Prodigium
     * @param pType   the element type of the Prodigium
     */
    public Prodigium(final String pName, final int pHealth, final int pEnergy, final int pLevel, final int pID, final Type pType)
    {
        this.aName = pName;

        this.aHealth = pHealth;
        this.aMaxHealth = pHealth;
        this.aEnergy = pEnergy;
        this.aMaxEnergy = pEnergy;

        this.aLevel = pLevel;
        this.aXP = 0;
        this.aMaxXP = 63;

        this.aID = pID;
        this.aType = pType;
    } // Prodigium( params )

    /**
     * Create a Prodigium with custom properties
     * @param pName   the name of the Prodigium
     * @param pHealth the health of the Prodigium
     * @param pHealth the maximum health of the Prodigium
     * @param pEnergy the amount of energy of the Prodigium
     * @param pMaxEnergy the maximum amount of energy of the Prodigium
     * @param pID     the ID of the Prodigium
     * @param pType   the element type of the Prodigium
     */
    public Prodigium(final String pName, final int pHealth, final int pMaxHealth, final int pEnergy,
                    final int pLevel, final int pXP, final int pMaxXP, final int pMaxEnergy, final int pID, final Type pType)
    {
        this.aName = pName;

        this.aHealth = pHealth;
        this.aMaxHealth = pMaxHealth;
        this.aEnergy = pEnergy;
        this.aMaxEnergy = pMaxEnergy;

        this.aLevel = pLevel;
        this.aXP = pXP;
        this.aMaxXP = pMaxXP;

        this.aID = pID;
        this.aType = pType;
    } // Prodigium( params )

    /**
     * Apply an amount of damage to the blocker
     * @param pAmount damage amount
     * @return true if the Prodigium is dead
     */
    public boolean applyDamage(final int pAmount){
        this.aHealth -= pAmount;
        return this.aHealth <= 0;
    } // applyDamage( params )

    /**
     * Send an attack to another blocker
     * @param pTarget   target of the attack
     * @param pAttackID the ID of the attack used
     * @return true if the Prodigium is dead
     */
    public boolean attack(final Prodigium pTarget, final int pAttackID){
        return pTarget.applyDamage(10);
    } // attack( params )

    /**
     * Get the Prodigium's type
     * @return Type
     */
    public Type getType()
    {
        return this.aType;
    }
    
    /**
     * Get the Prodigium as a JSON object
     * @return the Pordigium to JSON object
     */
    public JSONObject toJSONObject(){
        JSONObject vJSONProdigium = new JSONObject();

        vJSONProdigium.put("name", this.aName);
        vJSONProdigium.put("health", this.aHealth);
        vJSONProdigium.put("max_health", this.aMaxHealth);
        vJSONProdigium.put("energy", this.aEnergy);
        vJSONProdigium.put("max_energy", this.aMaxEnergy);
        vJSONProdigium.put("level", this.aLevel);
        vJSONProdigium.put("xp", this.aXP);
        vJSONProdigium.put("max_xp", this.aMaxXP);
        vJSONProdigium.put("id", this.aID);
        vJSONProdigium.put("type", this.aType);

        return vJSONProdigium;
    }
}

/**
* The different types of a Prodigium
*/
public enum Type { Earth, Water, Fire, Wind }