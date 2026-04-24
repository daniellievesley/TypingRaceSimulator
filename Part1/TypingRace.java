package part1;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import part2.GameData;

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

    /**
     * Constructor for objects of class TypingRace.
     * Sets up the race with a passage of the given length.
     * Initially there are no typists seated.
     *
     * @param PASSAGE_LENGTH the number of characters in the passage to type
     */
    public TypingRace(String passage, GameData info)
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
    public void startRace()
    {
        boolean finished = false;

        // Reset all typists to the start of the passage
        // (Ty was in a hurry here)
        for (int i=0; i<typists.length; i++){
            typists[i].resetToStart();
        }

        while (!finished)
        {
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

            // Print the current state of the race
            printRace();

            


            // Check if any typist has finished the passage
            for (int i=0; i<typists.length; i++){
                if (raceFinishedBy(typists[i])){
                    finished = true;
                    continue;
                }
            }

            // Wait 200ms between turns so the animation is visible
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (Exception e) {}
        }
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
            System.out.println("  And the winner is... " + theTypist.getName() + "!");
            theTypist.setAccuracy(theTypist.getAccuracy()+0.05);
            if (theTypist.getAccuracy()>theTypist.getoriginalAccuracy()){
                System.out.println("  Final accuracy: " + theTypist.getAccuracy() + " (improved from " + theTypist.getoriginalAccuracy() +")");
            }
            else if (theTypist.getAccuracy()==theTypist.getoriginalAccuracy()) {
            System.out.println("  Final accuracy: " + theTypist.getAccuracy() + " (remained same from " + theTypist.getoriginalAccuracy() +")");
            }
            else {
            System.out.println("  Final accuracy: " + theTypist.getAccuracy() + " (decreased from " + theTypist.getoriginalAccuracy() +")");
            }
            // update accuracy with new accuracy 
            for (int i=0; i<typists.length; i++){
                typists[i].setAccuracy(typists[i].getAccuracy());
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
            printSeat(typists[i]);
            System.out.println();
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
    private void printSeat(Typist theTypist)
    {
        int spacesBefore = theTypist.getProgress();
        int spacesAfter  = PASSAGE_LENGTH - theTypist.getProgress();

        System.out.print('|');
        multiplePrint(' ', spacesBefore);

        // Always show the typist's symbol so they can be identified on screen.
        // Append ~ when burnt out so the state is visible without hiding identity.
        System.out.print(theTypist.getSymbol());
        if (theTypist.isBurntOut())
        {
            System.out.print('~');
            spacesAfter--; // symbol + ~ together take two characters
        }

        if (theTypist.mistypeStatus()){
            System.out.print('<');
            spacesAfter--;
            theTypist.resetMistype();
        }


        multiplePrint(' ', spacesAfter);
        System.out.print('|');
        System.out.print(' ');

        // Print name and accuracy
        if (theTypist.isBurntOut())
        {
            System.out.print(theTypist.getName()
                + " (Accuracy: " + theTypist.getAccuracy() + ")"
                + " BURNT OUT (" + theTypist.getBurnoutTurnsRemaining() + " turns)");
        }
        else
        {
            System.out.print(theTypist.getName()
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
