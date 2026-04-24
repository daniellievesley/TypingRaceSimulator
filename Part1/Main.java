package part1;
public class Main {
    public static void main (String[] args){
        Typist t1 = new Typist ('\u2460', "TurboTyper", 0.01);
        Typist t2 = new Typist ('\u2461', "QwertyMaster", 0.02);
        Typist t3 = new Typist ('\u2462', "QuickTyper", 0.03);
        TypingRace game = new TypingRace(3);
        game.addTypist(t1, 1);
        game.addTypist(t2, 2);
        game.addTypist(t3, 3);
        game.startRace();
    
}
}
