public class Main {
    public static void main (String[] args){
        Typist t1 = new Typist ('\u2460', "TurboTyper", 0.7);
        Typist t2 = new Typist ('\u2461', "QwertyMaster", 0.4);
        Typist t3 = new Typist ('\u2462', "QuickTyper", 0.3);
        TypingRace game = new TypingRace(10);
        game.addTypist(t1, 1);
        game.addTypist(t2, 2);
        game.addTypist(t3, 3);
        game.startRace();
    }
}
