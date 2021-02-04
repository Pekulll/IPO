/**
 * A command typed by the player
 * @author  Benjamin LAMBERT
 * @version 2021.01.18
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Create a command
     * @param pCommandWord the command key word
     * @param pSecondWord the arg of the command
     */
    public Command ( final String pCommandWord, final String pSecondWord )
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    } // Command(..)
    
    /**
     * Get the command key word
     * @return String key
     */
    public String getCommandWord()
    {
        return this.aCommandWord;
    } // getCommandWord()
    
    /**
     * Get the command arg
     * @return String command arg
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    } // getSecondWord()
    
    /**
     * Check if the command has an argument
     * @return boolean has an argument
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    } // hasSecondWord()
    
    /**
     * Check if the command key is unknown
     * @return boolean key is unknown
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null;
    } // isUnknown()
} // Command
