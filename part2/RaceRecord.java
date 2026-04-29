package part2;

public class RaceRecord {
    private final double WPM;
    private final int position;
    private final double accuracy;
    private final int burnoutCount;
    public RaceRecord(double WPM, double accuracy, int burnoutCount, int pos){
        this.WPM = WPM;
        // this.position = position;
        this.accuracy = accuracy;
        this.burnoutCount = burnoutCount;
        this.position = pos;
    }
}
