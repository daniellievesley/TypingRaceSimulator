/**
 * Main method to run a typing race with 3 typists. Typist and game object(s) created. Game then ran.
 *
 * @author Daniel Lievesley
 * @version 1.0
 */

public class Main {
    public static void main (String[] args){
        Typist t1 = new Typist ('\u2460', "TurboTyper", 0.6);
        Typist t2 = new Typist ('\u2461', "QwertyMaster", 0.8);
        Typist t3 = new Typist ('\u2462', "QuickTyper", 0.4);
        TypingRace game = new TypingRace(7);
        game.addTypist(t1, 1);
        game.addTypist(t2, 2);
        game.addTypist(t3, 3);
        game.startRace();
    }
}
