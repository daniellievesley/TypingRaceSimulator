# TypingRaceSimulator

Object Oriented Programming Project — ECS414U

## Project Structure

```
TypingRaceSimulator/
├── Part1/    # Textual simulation (Java, command-line)
└── part2/    # GUI simulation (Java, GUI based application)
```

Part 2 contains copies of part 1 files such as `Typist.java` and `TypingRace.java`. These copies are adapted for the GUI implementation and contain new methods and instance variables, as required. Files held in part 1 are for the terminal based version only and are unchanged by the GUI development and any changes that this may brought.

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

Graphical application, as part of the assignment. 

### How to compile
To compile, open a terminal shell and load the project root (TypingRaceSimulator) and use the following command-line commands to compile:

```bash
javac part2/CustomiseTypists.java part2/GameData.java part2/IntroPage.java part2/Leaderboard.java part2/RacePage.java part2/RaceRecord.java part2/TypingRace.java part2/TypingRaceSimulator.java part2/Typist.java part2/TypistRowData.java
```

### How to run
Once in the project root directory (TypingRaceSimulator) and all files have been compiled, as above, you can run the program using the following command in the terminal:
```bash
java part2.TypingRaceSimulator
```
You will then be taken to the intial page - where you will need to choose a passage length (or a custom one, entering this in the box beside), seat count and select modifiers that wished to be used. After continuing, you will be taken to the 'Customising typists' page, where each typist can have their name, style, keyboard type, symbol, colour and accessories customised. By starting the game, the simulation will begin on the following page. You can start a new game at any time by pressing the 'Start new race' button. Press 'View Leaderboard' to view the latest scores. Here, you will also be able to compare typists based on metrics such as words per minute and more.

## Dependencies

- Java Development Kit (JDK) 17.0.18 or higher
- Part 1 doesn't use any external dependencies. It only uses standard Java libraries: java.text.DecimalFormat (for formatting to X decimal place), java.util.Random (for random number generation) and java.util.concurrent.TimeUnit (for time handling).
- Part 2 doesn't use any external dependencies. Only standard Java libraries are used, which are included in the JDK. As part 2 focussed on developing the graphical interface, Java Swing was used for GUI components, while Java AWT was used for layout design control, such as positioning. Other Java utilities and text formatting was used as described in part 1.

## Notes
- This project was tested and created using Java version 17.0.18. Running with earlier Java versions may lead to compatibility issues.
- This project was developed using Visual Studio Code.
- This project is compatible on any operating system, alongside the installation of Java.
- This application does not require an internet connection.
