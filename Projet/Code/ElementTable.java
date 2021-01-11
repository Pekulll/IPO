/**
 * An Element table to store type's efficiency
 * @author  Benjamin LAMBERT
 * @version 2021.09.01
 */
public class ElementTable
{

    private Hashtable aElementTable;

    /**
     * Create a default element table
     */
    public ElementTable()
    {
        this.aElementTable = new Hashtable(4);
        this.aElementTable.put(Type.Earth, Type.Water);
        this.aElementTable.put(Type.Water, Type.Fire);
        this.aElementTable.put(Type.Fire, Type.Wind);
        this.aElementTable.put(Type.Wind, Type.Earth);
    } // ElementTable()

    /**
     * Create a custom element table
     * @param pTable the custom element table
     */
    public ElementTable(final Hashtable pTable)
    {
        this.aElementTable = pTable;
    } // ElementTable( params )

    /**
     * Calculates the damage of an attack according to the type of the two Prodigiums involved in the fight
     * @param pAmount   damage amount
     * @param pSender   the sender of the attack
     * @param pReceiver the receiver of the attack
     */
    public int damageAmount(final int pAmount, final Prodigium pSender, final Prodigium pReceiver)
    {
        if(pSender.getType() == pReceiver.getType()){
            return pAmount;
        }


    } // damageAmount( params )
}