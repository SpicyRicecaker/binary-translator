import java.io.IOException;
import java.util.Scanner;
import java.io.File;

// Notice how this class does not have a constructor
public class InputWrapper {

    // if your function in a class does not use `this`, then it's a static function
    // these can be called without instantiating the class, because you aren't referencing anything the class owns
    // 
    // imagine a dog, and what it's class might be:
    // it would hold an `arm`, a `leg`, etc.
    // You would first have to make a dog, new Dog(with white arms, brown legs, etc..)
    // A non-static function would be a `moveLeg()` function, where the dog moves itself (e.g. `this.leg += 1`)
    // A static function would be something like `speakingOfDogsHowManyDogsAreThereInTheWorld()`
    // You wouldn't care about the exact properties of the specific instance of this dog, but it's a function
    // that's grouped with this dog for organization and convenience's sake
    // 
    // In rust (a superior programming language) each non-static function has a reference to itself. For example `fn moveleg(&self, etc...)`
    // You can see the &self. If there is no &self, `fn moveleg(etc...)` then you can see how it's basically just a function attached under
    // the class just for organization. Java just abstracts this some more and automatically adds the self reference for non-static functions
    // 
    // Also remember `public static void main()`, the main here is a static function, because when we start up our program obviously
    // we haven't instantiated any classes yet.
    // 
    // nextLine is a general function that takes a number from standard in
    public static String nextLine() {
        String out = "";
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            out = scan.nextLine();
            // make sure there are digits in there
            if (out.length() != 0) {
                // Regex (regular expression) pattern matching
                // Basically, if we see any character that is not a digit, leave!
                if (!out.matches("[^0-9]")) {
                    break;
                }
            } else {
                System.out.println("input is invalid.");
            }
        }
        return out;
    }

    // Reads a file, accepts user input
    public static String readFile() {
        String in = "";
        String out = "";
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            try {
                System.out.println("Please enter your file name");
                in = scan.nextLine();
                Scanner fileScan = new Scanner(new File(in));
                out = fileScan.nextLine();
                fileScan.close();
                break;
            } catch (IOException ex) {
                System.out.println("File not found");
                System.exit(1);
            }
        }
        return out;
    }
}
