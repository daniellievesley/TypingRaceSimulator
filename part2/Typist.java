/**
 * Typist is the class of the indvidual players within the simulation game.
 * Each typist has various metrics and score information stored about them and their progress, which is stored here.
 * A typist has several fields such as their progress and acccuracy (which are variable) and some which are fixed such as their name and symbol.
 *
 * @author Daniel Lievesley
 * @version 1.0
 */

package part2;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList; 

public class Typist
{
    final private String name;
    private char symbol;
    private int currentProgress;
    private boolean burnOut;
    private int burnOutTurns;
    private double accuracy;
    private boolean justMistyped;
    final private double originalAccuracy;
    final private double MISTYPE_BASE;
    final private int BURNOUT_DURATION;
    final private boolean NOISE_CANCELLING;
    final private Color colour;
    private int mistypeCount;
    private int totalKeystrokes;
    private int burntOutCount;
    private double bestWPMSoFar;
    private ArrayList<RaceRecord> racesCompleted;
    private int points;
    private int consecBOFreeTurns;
    private int winCount;
    private ArrayList <String> badges;




    // Constructor of class Typist
    /**
     * Constructor for objects of class Typist.
     * Creates a new typist with a given symbol, name, and accuracy rating.
     *
     * @param typistSymbol  a single Unicode character representing this typist (e.g. '①', '②', '③')
     * @param typistName    the name of the typist (e.g. "TURBOFINGERS")
     * @param typistAccuracy the typist's accuracy rating, between 0.0 and 1.0
     * @param mistype this holds the individual mistype base calculated for the typist based on customisations
     * @param duration this holds the duration of burn out if the user is in burn out mode (based on customisations)
     * @param headphones this holds boolean value of whether headphones are requested for an accessory
     * @param colour this holds the colour object of the typist's chosen colour identifier
     */
    public Typist(char typistSymbol, String typistName, double typistAccuracy, double mistype, int duration, boolean headphones, Color colour)
    {
        this.symbol = typistSymbol;
        this.name = typistName;
        this.accuracy = typistAccuracy;
        this.originalAccuracy = typistAccuracy;
        this.burnOut = false;
        this.currentProgress = 0;
        this.justMistyped = false;
        this.MISTYPE_BASE = mistype;
        this.BURNOUT_DURATION = duration;
        this.NOISE_CANCELLING = headphones;
        this.colour = colour;
        this.mistypeCount = 0;
        this.totalKeystrokes = 0;
        this.burntOutCount = 0;
        this.bestWPMSoFar = 0.0;
        this.racesCompleted = new ArrayList<>();
        this.points = 0;
        this.consecBOFreeTurns = 0;
        this.winCount = 0;
        this.badges = new ArrayList<>();
    }


    // Methods of class Typist

    /**
     * Sets this typist into a burnout state for a given number of turns.
     * A burnt-out typist cannot type until their burnout has worn off.
     *
     * @param turns the number of turns the burnout will last
     */
    public void burnOut(int turns)
    {
        this.consecBOFreeTurns = 0;
        this.burnOut = true;
        this.burnOutTurns = turns;
        this.setAccuracy(this.getAccuracy()-this.getAccuracy()* 0.1);
        this.burntOutCount++;
    }

    /**
     * Reduces the remaining burnout counter by one turn.
     * When the counter reaches zero, the typist recovers automatically.
     * Has no effect if the typist is not currently burnt out.
     */
    public void recoverFromBurnout()
    {
        if (this.burnOut){
            if (this.burnOutTurns==1){
                this.burnOut = false;
                this.burnOutTurns = 0;
            }
            else {
                this.burnOutTurns=this.burnOutTurns-1;
            }
        }
    }

    /**
     * Returns the typist's accuracy rating.
     *
     * @return accuracy as a double between 0.0 and 1.0
     */
    public double getAccuracy()
    {
        return this.accuracy;
    }

    public Color getColour(){
        return this.colour;
    }

    /**
     * Returns the typist's current progress through the passage.
     * Progress is measured in characters typed correctly so far.
     * Note: this value can decrease if the typist mistypes.
     *
     * @return progress as a non-negative integer
     */
    public int getProgress()
    {
        return this.currentProgress;
    }

    /**
     * Returns the name of the typist.
     *
     * @return the typist's name as a String
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the character symbol used to represent this typist.
     *
     * @return the typist's symbol as a char
     */
    public char getSymbol()
    {
        return this.symbol; // placeholder - replace with correct implementation
    }

    /**
     * Returns the number of turns of burnout remaining.
     * Returns 0 if the typist is not currently burnt out.
     *
     * @return burnout turns remaining as a non-negative integer
     */
    public int getBurnoutTurnsRemaining()
    {
        if (this.burnOut){
            return burnOutTurns;
        }
        else {
            return 0;
        }
    }

    /**
     * Resets the typist to their initial state, ready for a new race.
     * Progress returns to zero, burnout is cleared entirely.
     */
    public void resetToStart()
    {
        this.currentProgress = 0;
        this.burnOut = false;
        this.burnOutTurns = 0;
    }

    /**
     * Returns true if this typist is currently burnt out, false otherwise.
     *
     * @return true if burnt out
     */
    public boolean isBurntOut()
    {
        return this.burnOut;
    }

    /**
     * Advances the typist forward by one character along the passage.
     * Should only be called when the typist is not burnt out.
     */
    public void typeCharacter()
    {
        if (!(this.burnOut)){
            this.currentProgress= this.currentProgress+1;
        }
    }

    /**
     * Moves the typist backwards by a given number of characters (a mistype).
     * Progress cannot go below zero — the typist cannot slide off the start.
     *
     * @param amount the number of characters to slide back (must be positive)
     */
    public void slideBack(int amount)
    {
        if (this.currentProgress-amount<0){
            this.currentProgress=0;
        }
        else{
            this.currentProgress = this.currentProgress-amount;
        }

        this.justMistyped = true;
    }

    /**
     * Sets the accuracy rating of the typist.
     * Values below 0.0 should be set to 0.0; values above 1.0 should be set to 1.0.
     *
     * @param newAccuracy the new accuracy rating
     */
    public void setAccuracy(double newAccuracy)
    {
        if (newAccuracy>1.0){
            this.accuracy=1.0;
        }
        else if (newAccuracy<0.0){
            this.accuracy = 0.0;
        }
        else {
            DecimalFormat format = new DecimalFormat("#.00");
            this.accuracy = Double.parseDouble(format.format(newAccuracy));
        }
    }

    /**
     * Sets the symbol used to represent this typist.
     *
     * @param newSymbol the new symbol character
     */
    public void setSymbol(char newSymbol)
    {
        this.symbol = newSymbol;
    }

    /**
     * Reset mistype status for a typist - sets attribute to false
     */
    public void resetMistype(){
        this.justMistyped = false;
    }

    /**
     * Returns the contents of the mistype status attribute
     * @return mistype status attribute returned - boolean value
     */
    public boolean mistypeStatus(){
        return this.justMistyped;
    }

    /**
     * Returns the original accuracy of the typist - before the game began and any bonuses were added
     * @return originalAccuracy value double held
     */
    public double getoriginalAccuracy(){
        return originalAccuracy;
    }

    /**
     * Returns the contents of the mistypeBase attribute (the base calculated after accessories)
     * @return double which holds mistype base 
     */
    public double getMistypeBase(){
        return this.MISTYPE_BASE;
    }

    /**
     * Returns the contents of the burn out duration attribute
     * @return burn out duration attribute as integer
     */
    public int getburnOutDuration(){
        return this.BURNOUT_DURATION;
    }

    /**
     * Returns whether the user has headphones accessory
     * @return boolean value of noise cancelling attribute
     */
    public boolean getHeadphones(){
        return this.NOISE_CANCELLING;
    }

    /**
     * Increments mistype count by 1
     */
    public void incrementMistype(){
        this.mistypeCount++;
    }

    /**
     * Returns the number of mistypes the user has had
     * @return mistype count attribute as integer
     */
    public int getMistypeCount(){
        return this.mistypeCount;
    }

    /**
     * Increases keystrokes by 1 to track typist activity
     **/
    public void incrementKeystroke(){
        this.totalKeystrokes++;
    }

    /**
     * Returns the number of keystroke counts of the typist
     * @return int keystroke count value
     */
    public int getKeystrokeCount(){
        return this.totalKeystrokes;
    }

    /**
     * Returns the number of burn outs the typist has had
     * @return burn out count of typist as int
     */
    public int getBurnOutCount(){
        return this.burntOutCount;
    }

    /**
     * Returns the best WPM by the typist so far in the game
     * @return best WPM ever experienced by typist as double
     */
    public double getBestWPMSoFar(){
        return this.bestWPMSoFar;
    }

    /**
     * Checks whether a WPM is a record for the typist by comparing to the record already stored.
     * If it is a record - it updates the typist attribute bestWPMSoFar
     * @param WPMjustNow - is the WPM to be tested
     */
    public void checkBestWPMSoFar(double WPMjustNow){
        if (WPMjustNow > this.getBestWPMSoFar()){
            this.bestWPMSoFar = WPMjustNow;
        }
    }

    /**
     * Holds the race details such as analytics which can be used for later comparison - adds these to a list
     * @param details - is the record object to be added to the list
     */
    public void recordRace(RaceRecord details){
        this.racesCompleted.add(details);
    }

    /**
     * Adds points to a typist's current points value
     * @param pointsToAward- is the points that need to be added to the count
     */
    public void awardPoints(int pointsToAward){
        this.points= this.points+pointsToAward;
    }

    /**
     * Returns the number of points a typist has in the whole game so far
     * @return number of points accrued so far as int
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Increases the number of burn out free turns that the typist has had by 1
     */
    public void increaseBOFree(){
        this.consecBOFreeTurns++;
    }

    /**
     * Returns number of burn out free turns recorded
     * @return int count of turns without a burn out
     */
    public int getBOFreeTurns(){
        return this.consecBOFreeTurns;
    }

    /**
     * Returns the stored number of wins by a typist in a game
     * @return number of wins by the typist in a game as int
     */
    public int getWinCount(){
        return this.winCount;
    }

    /**
     * Increases win count by 1
     */
    public void addWintoCount(){
        this.winCount++;
    }

    /**
     * Adds a badge (string val) to the typist's list of won badges. List so that there can be more badges added in future
     * Only will add a badge if the badge has not already been awarded
     * @param badge - string badge to be added to the list of typist badges
     */
    public void awardBadge(String badge){
        for (int i=0; i<this.badges.size(); i++){
            if (this.badges.get(i).equals(badge)){
               return; 
            }
        }
        this.badges.add(badge);
    }

    /**
     * Returns the string line of badges that have been won by a typist 
     * @return string value of badges won (string is the badges concatenated by ,) (e.g Badge 1, Badge 2,)
     */
    public String getBadgeString(){
        String toRtn = "";
        for (int i=0; i<this.badges.size(); i++){
            toRtn=toRtn + this.badges.get(i) + ", ";
        }
        return toRtn;
    }

}
