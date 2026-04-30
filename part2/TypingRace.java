package part2;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * A typing race simulation. Three typists race to complete a passage of text,
 * advancing character by character — or sliding backwards when they mistype.
 *
 * This part contains files which will lead to GUI output - not suited to terminal game
 *
 * @author Daniel Lievesley
 * @version 1.0
 */
public class TypingRace
{
    private final int PASSAGE_LENGTH;   // Total characters in the passage to type
    private final String PASSAGE; // actual passage 
    private Typist[] typists; // typist array 
    private final GameData importedSettings; 
    // Accuracy thresholds for slide back
    private final int  SLIDE_BACK_AMOUNT;
    private int turns = 0;
    private final RacePage display;

    /**
     * Constructor for objects of class TypingRace.
     * Sets up the race with a passage of the given length.
     * Initially there are no typists seated.
     *
     * @param PASSAGE_LENGTH the number of characters in the passage to type
     * @param info contains gamedata settings from intropage
     * @param display contains the race display GUI
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
     * Seats a typist at the given seat number .
     *
     * @param theTypist  the typist to seat
     * @param seatNumber the seat to place them in (1-6)
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
     * resetAll()
     * resets all typists to beginning state
     */

    public void resetAll() {
        for (int i=0; i<typists.length; i++){
            typists[i].resetToStart();
        }
    }

    
    /**
     * returns array of typists
     * @return array of typist objects
     */
    public Typist[] getTypists(){
        return this.typists;
    }

    /**
     * returns passage used
     * @return string used in passage
     */
    public String getPassage(){
        return this.PASSAGE;
    }

    /**
     * startTurn
     * starts the turn, advancing typists turn by turn
     */
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

            

    }

    /**
     * checks if race is finished and returns whether this is true 
     * @return boolean whether race is finished
     */
    public boolean isFinished(){
        for (int i=0; i<typists.length; i++){
                if (raceFinishedBy(typists[i])){
                    return true;
                }
            }
        return false;
    }

    /**
     * increases the turns in game
     */

    private void increaseTurn(){
        this.turns++;
    }

    /**
     * returns int of turn count
     * @return int no of turns
     */
    private int getTurns(){
        return this.turns;
    }

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

    checkforBadges();
}

    /**
     * Returns true if the given typist has completed the full passage. Responsible for outputting messages, updating accuracy and calculating stats
     * Handles race end logic - stats, printing results, awarding points
     *
     * @param theTypist the typist to check
     * @return true if their progress has reached or passed the passage length
     */
     private boolean raceFinishedBy(Typist theTypist)
    {
        // Typist progress can now meet or exceed passage length
        if (theTypist.getProgress() >= PASSAGE_LENGTH)
        {
            theTypist.addWintoCount();
            display.printResults("  And the winner is... " + theTypist.getName() + "!");
            System.out.println("first call is made");
            theTypist.setAccuracy(theTypist.getAccuracy()+0.05);
            if (theTypist.getAccuracy()>theTypist.getoriginalAccuracy()){
                display.printResults("  Final accuracy: " + theTypist.getAccuracy() + " (improved from " + theTypist.getoriginalAccuracy() +")");
                System.out.println("Call made");
            }
            else if (theTypist.getAccuracy()==theTypist.getoriginalAccuracy()) {
                display.printResults("  Final accuracy: " + theTypist.getAccuracy() + " (remained same from " + theTypist.getoriginalAccuracy() +")");
                System.out.println("Call made");
            }
            else {
                display.printResults("  Final accuracy: " + theTypist.getAccuracy() + " (decreased from " + theTypist.getoriginalAccuracy() +")");
                System.out.println("Call made");
            }
            // update accuracy with new accuracy 
            for (int i=0; i<typists.length; i++){
                typists[i].setAccuracy(typists[i].getAccuracy());
            }

            // statistics and analytics
            double elapsedTime = (display.getEndTime()-display.getStartTime())/1000000000.0;
            DecimalFormat to1DP = new DecimalFormat("#.0");
            double[] accuracies = new double[typists.length];
            for (int i=0; i<typists.length; i++){
                display.printResults(typists[i].getName() + "'s statistics: " + " WPM: " + String.valueOf(to1DP.format((typists[i].getProgress()*12)/elapsedTime)) + " Mistype count - " + String.valueOf(to1DP.format(((double) typists[i].getMistypeCount()/(double) typists[i].getKeystrokeCount())*100.0)) + " Burnt out " + typists[i].getBurnOutCount() + " times!" + " Accuracy changed from " + typists[i].getoriginalAccuracy() + " to " + typists[i].getAccuracy());
                
                typists[i].checkBestWPMSoFar(Double.parseDouble(to1DP.format((typists[i].getProgress()*12)/elapsedTime)));
            
                accuracies[i] = typists[i].getAccuracy();
            }
            for (int j=0; j<accuracies.length-1; j++){ // sorted accuracies high-low
                for (int k=0; k < accuracies.length-j-1; k++){
                    if (accuracies[j]<accuracies[j+1]){
                        double temp = accuracies[j];
                        accuracies[j] = accuracies[j+1];
                        accuracies[j+1] = temp;
                    }
                }
            }

            for (int i=0; i<typists.length; i++){ // nested for loop which checks to find their accuracy in the rank and store rank in race record
                for (int a = 0; a<accuracies.length; a++){
                    if (accuracies[a]==typists[i].getAccuracy()){
                        if (a==0){
                            typists[i].awardPoints(3);
                        }
                        else if (a==1){
                            typists[i].awardPoints(2);
                        }
                        else if (a==2){
                            typists[i].awardPoints(1);
                        }
                        typists[i].recordRace(new RaceRecord(Double.parseDouble(to1DP.format((PASSAGE_LENGTH*12)/elapsedTime)), typists[i].getAccuracy(), typists[i].getBurnOutCount(), a+1));
                        break;
                    }
                }
            }

            awardRoundPoints(typists, elapsedTime);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Awards the points for the turn depending on wpm and if the typist is burnt out.
     *
     * @param typists array of typists in game
     * @param elapsedTime the time elapsed in this turn
     */
    private void awardRoundPoints(Typist[] typists, double elapsedTime){
        for (int i=0; i<typists.length; i++){
            double wpm = (typists[i].getProgress()*12)/elapsedTime;
            typists[i].awardPoints((int) Math.round(wpm/10.0));
            if (typists[i].isBurntOut()!=false){
                typists[i].awardPoints(5);
                typists[i].increaseBOFree();
            }
        }
    }

    /**
     * Awards the badges by checking if the typist qualifies for any
     *
     */
    private void checkforBadges(){
        for (int i=0; i<typists.length; i++){
            // check for 5 consec Burn Out Free Turns 
            if (typists[i].getBOFreeTurns()==5){
                typists[i].awardBadge("Iron Fingers");
                System.out.println("Awarding iron ");
            }
            // check for 3 win count 
            if (typists[i].getWinCount()==3){
                typists[i].awardBadge("Speed Demon");
            }
        }
    }


}
