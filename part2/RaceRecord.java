/**
 * RaceRecord is a class which stores the data obtained from a race for a typist
 * This allows for the history of races per typist to be recalled
 * Attributes of this class allow for the tracking of a range of metrics such as WPM and accuracy
 * One record object = one typist's race 
 *
 * @author Daniel Lievesley
 * @version 1.0
 */

package part2;

/**
 * Constructor for RaceRecord
 * @param WPM holds the wpm reading for the race as double
 * @param accuracy holds accuracy reading for race as double
 * @param burnoutCount holds integer value of number of burn out occassions within race 
 * @param pos holds integer value of position of typist in race 
 */

public class RaceRecord {
    private final double WPM;
    private final int position;
    private final double accuracy;
    private final int burnoutCount;
    public RaceRecord(double WPM, double accuracy, int burnoutCount, int pos){
        this.WPM = WPM;
        this.accuracy = accuracy;
        this.burnoutCount = burnoutCount;
        this.position = pos;
    }
}
