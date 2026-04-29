/**
 * CustomiseTypists is a class which stores the customisations for the game, after being selected by users
 * Getter methods which allow for these settings to be retrieved
 * Makes storing customisations easier - within ONE object
 *
 * @author Daniel Lievesley
 * @version 1.0
 */

package part2;

public class GameData {
    private String len;
    private String passage;
    private Integer seats;
    private boolean auto;
    private boolean caffeine;
    private boolean night;

    /**
     * getLen() getter method
     *
     * @return length mode chosen (short/med etc) - string
     */
    public String getLen(){
        return this.len;
    }

    /**
     * getPassage() getter method
     *
     * @return customised passage - string
     */
    public String getPassage(){
        return this.passage;
    }

    /**
     * getSeats() getter method
     *
     * @return number of seats needed - integer
     */
    public Integer getSeats(){
        return this.seats;
    }

    /**
     * getAuto() getter method
     *
     * @return autocorrect on/off boolean
     */
    public Boolean getAuto(){
        return this.auto;
    }

    /**
     * getCaffeine() getter method
     *
     * @return caffeine status selected boolean
     */
    public Boolean getCaffeine(){
        return this.caffeine;
    }

    /**
     * getNight() getter method
     *
     * @return night shift chosen as boolean
     */
    public Boolean getNight(){
        return this.night;
    }

    /**
     * Constructor for objects of class GameData
     * Creates a new object with grid layout and label set to center.
     *
     * @param len contains the length selected by the user (short, med etc)
     * @param passage contains raw passage, where chosen by user
     * @param seats contains number of seats chosen
     * @param auto contains if auto correct chosen
     * @param caffeine contains if caffeine mode chosen
     * @param night contains if night shift is selected
     */
    public GameData(String len, String passage, Integer seats, boolean auto, boolean caffeine, boolean night) {
        this.len = len;
        this.passage = passage;
        this.seats = seats;
        this.auto = auto;
        this.caffeine = caffeine;
        this.night = night;
    }
}