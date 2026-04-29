# TypingRaceSimulator

Object Oriented Programming Project — ECS414U

## Project Structure

```
TypingRaceSimulator/
├── Part1/    # Textual simulation (Java, command-line)
└── part2/    # GUI simulation (Java, GUI based application)
```

## Part 1 — Textual Simulation

### How to compile

```bash
cd Part1
javac Typist.java TypingRace.java
```

### How to run

The race is started by calling `startRace()` on a `TypingRace` object.
A simple way to test this is to use the `main` method of the `Main` class, which serves as an entry point to the program, creating 3 typist objects and a race object. This method starts the race. Firstly, 3 typist objects are created by calling the constructor, passing in the character code (in this case circled numbers such as ① via their unicode), their name and accuracy (as a double). The game is then created by initialising a TypingRace object, and calling it's constructor, by passing in an integer as the passage length as an argument. The race object's `addTypist()` method is called, passing in the newly created Typist objects to the race along with their seat number (integer). This means that, now with typists added, the race can be started by calling `startRace()` on the race object.

```java
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
```

Then run:

```bash
java Main
```

## Part 2 — GUI Simulation

To be implemented as part of the coursework. Place all GUI-related source files in this folder. The graphical version is started by calling `startRaceGUI()`.

## Dependencies

- Java Development Kit (JDK) 11 or higher
- No external libraries required for Part 1
- Part 2 may use Java Swing (included in standard JDK) or JavaFX

## Notes

- All code should compile and run using standard command-line tools without any IDE-specific configuration.
- The starter code in Part1 was originally written by Ty Posaurus. It contains known issues — finding and fixing them is part of the coursework.
