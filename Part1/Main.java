public class Main {
    public static void main (String[] args){
        Typist t = new Typist ('A', "Daniel", 0.7);
        System.out.println("Progress - " + t.getProgress());
        t.typeCharacter();
        System.out.println("Progress - " + t.getProgress());
        t.typeCharacter();
        System.out.println("Progress - " + t.getProgress());
    }
}
