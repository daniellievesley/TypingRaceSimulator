package part1;
import java.text.DecimalFormat;
import java.util.Random;
import part2.GameData;
import part2.RacePage;

/**
 * A typing race simulation. Three typists race to complete a passage of text,
 * advancing character by character — or sliding backwards when they mistype.
 *
 * Originally written by Ty Posaurus, who left this project to "focus on his
 * two-finger technique". He assured us the code was "basically done".
 * We have found evidence to the contrary.
 *
 * @author TyPosaurus
 * @version 0.7 (the other 0.3 is left as an exercise for the reader)
 */
public class TypingRace
{
    private final int PASSAGE_LENGTH;   // Total characters in the passage to type
    private final String PASSAGE; // actual passage 
    private Typist[] typists; // typist array 
    private final GameData importedSettings; 
    // Accuracy thresholds for mistype and burnout events
    // (Ty tuned these values "by feel". They may need adjustment.)
    // private static final double MISTYPE_BASE_CHANCE = 0.3;
    private final int  SLIDE_BACK_AMOUNT;
    // private static final int    BURNOUT_DURATION     = 3;
    private int turns = 0;
    private final RacePage display;

    /**
     * Constructor for objects of class TypingRace.
     * Sets up the race with a passage of the given length.
     * Initially there are no typists seated.
     *
     * @param PASSAGE_LENGTH the number of characters in the passage to type
     */
    public TypingRace(String passage, GameData info, RacePage display)
    {
        this.PASSAGE = passage;
        this.PASSAGE_LENGTH = (this.PASSAGE).length();
        this.importedSettings = info;
        this.typists = new Typist[importedSettings.getSeats()]; // setting array for max size of typists provided 
        if (importedSettings.getAuto()){
            SLIDE_BACK_AMOUNT = 1;
        }
        else{
        SLIDE_BACK_AMOUNT   = 2;
        }
        this.display = display;
        
    }

    /**
     * Seats a typist at the given seat number (1, 2, or 3).
     *
     * @param theTypist  the typist to seat
     * @param seatNumber the seat to place them in (1–3)
     */
    public void addTypist(Typist theTypist, int seatNumber)
    {
        if (this.typists[seatNumber-1]==null){
            this.typists[seatNumber-1]=theTypist;
        }
        else if (this.typists[seatNumber-1]!=null){
            System.out.println("Seat taken");
        }
        else
        {
            System.out.println("Cannot seat typist at seat " + seatNumber + " — there is no such seat.");
        }
    }

    /**
     * Starts the typing race.
     * All typists are reset to the beginning, then the simulation runs
     * turn by turn until one typist completes the full passage.
     *
     * Note from Ty: "I didn't bother printing the winner at the end,
     * you can probably figure that out yourself."
     */

    public void resetAll() {
        for (int i=0; i<typists.length; i++){
            typists[i].resetToStart();
        }
    }

    public Typist[] getTypists(){
        return this.typists;
    }

    public String getPassage(){
        return this.PASSAGE;
    }

    public void startTurn(){
        increaseTurn();
        int accuracyIsZero = 0;
            for (int i=0; i<typists.length; i++){
                if (typists[i].getAccuracy()==0){
                    accuracyIsZero++;
                }
            }

            if (accuracyIsZero==typists.length){
                Random r = new Random();
                int index = r.nextInt(1,typists.length);
                Typist toForce = typists[index+1];
                if(!(toForce.isBurntOut())){
                    toForce.typeCharacter();
                }
            }

            else{
            // Advance each typist by one turn
            for (int i=0; i<typists.length; i++){
                advanceTypist(typists[i]);
            }
            }

            printRace();

    }

    public boolean isFinished(){
        for (int i=0; i<typists.length; i++){
                if (raceFinishedBy(typists[i])){
                    return true;
                }
            }
        return false;
    }



    

        // TODO (Task 2a): Print the winner's name here

    /**
     * Simulates one turn for a typist.
     *
     * If the typist is burnt out, they recover one turn's worth and skip typing.
     * Otherwise:
     *   - They may type a character (advancing progress) based on their accuracy.
     *   - They may mistype (sliding back) — the chance of a mistype should decrease
     *     for more accurate typists.
     *   - They may burn out — more likely for very high-accuracy typists
     *     who are pushing themselves too hard.
     *
     * @param theTypist the typist to advance
     */

    private void increaseTurn(){
        this.turns++;
    }

    private int getTurns(){
        return this.turns;
    }
    private void advanceTypist(Typist theTypist)
    {

        double multiplierCaffeine = 1.0;
        if (theTypist.isBurntOut())
        {
            // Recovering from burnout — skip this turn
            theTypist.recoverFromBurnout();
            return;
        }

        theTypist.incrementKeystroke();

        if (importedSettings.getCaffeine() && getTurns()<=10){
            multiplierCaffeine=multiplierCaffeine*1.5;
        }
        else if (importedSettings.getCaffeine()&&getTurns()>10){
            multiplierCaffeine=multiplierCaffeine*0.75;
        }

        if (theTypist.getHeadphones()){
            if ((theTypist.getProgress()/PASSAGE_LENGTH)>0.5){
                // Attempt to type a character


                if (Math.random() < theTypist.getAccuracy()*0.7)
                {
                    theTypist.typeCharacter();
                }

                // Mistype check — the probability should reflect the typist's accuracy
                if (Math.random() < (1.0-theTypist.getAccuracy()*0.7*multiplierCaffeine) * theTypist.getMistypeBase())
                {
                theTypist.incrementMistype();
                theTypist.slideBack(SLIDE_BACK_AMOUNT);
                }

                // Burnout check — pushing too hard increases burnout risk
                // (probability scales with accuracy squared, capped at ~0.08)
                if (Math.random() < 0.08 * (theTypist.getAccuracy()*0.7*multiplierCaffeine) * (theTypist.getAccuracy()*multiplierCaffeine*0.7))
                {
                    theTypist.burnOut(theTypist.getburnOutDuration());
                }   
            }

            if ((theTypist.getProgress()/PASSAGE_LENGTH)<=0.5){
                // Attempt to type a character
                if (Math.random() < theTypist.getAccuracy()*1.3*multiplierCaffeine)
                {
                    theTypist.typeCharacter();
                }

                // Mistype check — the probability should reflect the typist's accuracy
                if (Math.random() < (1.0-theTypist.getAccuracy()*1.3*multiplierCaffeine) * theTypist.getMistypeBase())
                {
                theTypist.slideBack(SLIDE_BACK_AMOUNT);
                theTypist.incrementMistype();
                }

                // Burnout check — pushing too hard increases burnout risk
                // (probability scales with accuracy squared, capped at ~0.08)
                if (Math.random() < 0.08 * (theTypist.getAccuracy()*1.3*multiplierCaffeine) * (theTypist.getAccuracy()*1.3*multiplierCaffeine))
                {
                    theTypist.burnOut(theTypist.getburnOutDuration());
                }   
            }
    }
    else {

        // Attempt to type a character
        if (Math.random() < theTypist.getAccuracy()*multiplierCaffeine)
        {
            theTypist.typeCharacter();
        }

        // Mistype check — the probability should reflect the typist's accuracy
        if (Math.random() < (1.0-theTypist.getAccuracy()*multiplierCaffeine) * theTypist.getMistypeBase())
        {
            theTypist.slideBack(SLIDE_BACK_AMOUNT);
            theTypist.incrementMistype();
        }

        // Burnout check — pushing too hard increases burnout risk
        // (probability scales with accuracy squared, capped at ~0.08)
        if (Math.random() < 0.08 * theTypist.getAccuracy()*multiplierCaffeine * theTypist.getAccuracy()*multiplierCaffeine)
        {
            theTypist.burnOut(theTypist.getburnOutDuration());
        }
    }
}

    /**
     * Returns true if the given typist has completed the full passage.
     *
     * @param theTypist the typist to check
     * @return true if their progress has reached or passed the passage length
     */
     private boolean raceFinishedBy(Typist theTypist)
    {
        // Typist progress can now meet or exceed passage length
        if (theTypist.getProgress() >= PASSAGE_LENGTH)
        {
            display.printWinner("  And the winner is... " + theTypist.getName() + "!");
            System.out.println("first call is made");
            theTypist.setAccuracy(theTypist.getAccuracy()+0.05);
            if (theTypist.getAccuracy()>theTypist.getoriginalAccuracy()){
                display.printWinner("  Final accuracy: " + theTypist.getAccuracy() + " (improved from " + theTypist.getoriginalAccuracy() +")");
                System.out.println("Call made");
            }
            else if (theTypist.getAccuracy()==theTypist.getoriginalAccuracy()) {
                display.printWinner("  Final accuracy: " + theTypist.getAccuracy() + " (remained same from " + theTypist.getoriginalAccuracy() +")");
                System.out.println("Call made");
            }
            else {
                display.printWinner("  Final accuracy: " + theTypist.getAccuracy() + " (decreased from " + theTypist.getoriginalAccuracy() +")");
                System.out.println("Call made");
            }
            // update accuracy with new accuracy 
            for (int i=0; i<typists.length; i++){
                typists[i].setAccuracy(typists[i].getAccuracy());
            }

            // statistics and analytics
            double elapsedTime = (display.getEndTime()-display.getStartTime())/1000000000.0;
            DecimalFormat to1DP = new DecimalFormat("#.0");
            for (int i=0; i<typists.length; i++){
                display.printWinner(typists[i].getName() + "'s statistics: ");
                display.printWinner(" WPM: " + String.valueOf(to1DP.format((PASSAGE_LENGTH*12)/elapsedTime)));
                display.printWinner(" Mistype count - " + String.valueOf(to1DP.format(((double) typists[i].getMistypeCount()/(double) typists[i].getKeystrokeCount())*100.0)));
                display.printWinner(" Burnt out " + typists[i].getBurnOutCount() + " times!");
                display.printWinner(" Accuracy changed from " + typists[i].getoriginalAccuracy() + " to " + typists[i].getAccuracy());
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Prints the current state of the race to the terminal.
     * Shows each typist's position along the passage, burnout state,
     * and a WPM estimate based on current progress.
     */
    private void printRace()
    {
        System.out.print('\u000C'); // Clear terminal

        System.out.println("  TYPING RACE — passage length: " + PASSAGE_LENGTH + " chars");
        multiplePrint('=', PASSAGE_LENGTH + 3);
        System.out.println();

        for (int i=0; i<typists.length; i++){
            String toOutput = printSeat(typists[i]);
            display.showTypistUpdate(toOutput, i);
        }

        multiplePrint('=', PASSAGE_LENGTH + 3);
        System.out.println();
        System.out.println("  [~] = burnt out    [<] = just mistyped");
    }

    /**
     * Prints a single typist's lane.
     *
     * Examples:
     *   |          ⌨           | TURBOFINGERS (Accuracy: 0.85)
     *   |    [~]              | HUNT_N_PECK  (Accuracy: 0.40) BURNT OUT (2 turns)
     *
     * Note: Ty forgot to show when a typist has just mistyped. That would
     * be a nice improvement — perhaps a [<] marker after their symbol.
     *
     * @param theTypist the typist whose lane to print
     */
    private String printSeat(Typist theTypist)
    {

        // Print name and accuracy
        if (theTypist.isBurntOut())
        {
            
            return(theTypist.getName()
                + " (Accuracy: " + theTypist.getAccuracy() + ")"
                + " BURNT OUT (" + theTypist.getBurnoutTurnsRemaining() + " turns)");
        }
        else
        {
            return(theTypist.getName()
                + " (Accuracy: " + theTypist.getAccuracy() + ")");
        }
    }

    
    /**
     * Prints a character a given number of times.
     *
     * @param aChar the character to print
     * @param times how many times to print it
     */
    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }
}
