import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

// The purpose of this class is to enapsulate some grammar rules
// It avoids the dreaded while loop with if checks and a scanner below VVV

/*
Scanner scan = new Scanner(System.in);
String out = "";
while (true) {
    out = scan.nextLine();
    if (out.equals("input")) {
        System.out.println!("POG!");
        break;
    } else if(out.equals("file")) {
        System.out.println!("POG!");
        break;
    } else {
        System.out.println!("No you're bad");
    }
}
*/

// The `<T>` here is a "generic".
// You've used types before: When you need to create an arraylist,
// you do ArrayList<Integer>, for example.
// If this Input class was only used for this project, for one enum,
// Like `ConversionType` (file or input)
// Then it would be Input<ConversionType>
// But because we want this class to be as versatile as possible and 
// support any enum, we create a genertic <T> parameter here
// On runtime, when we insert an enum of type `x` into this class,
// then the T becomes type 'x'
// 
// (Recall reading about enums in `BinaryTranslator`)
public class Input<T> {
    // Dictionary of terminology
    HashMap<String, Lexeme<T>> map;

    // Takes in an arraylist of lexemes and creates a `dictionary`
    public Input(ArrayList<Lexeme<T>> objectList) {
        HashMap<String, Lexeme<T>> map = new HashMap<>();
        for (Lexeme<T> object : objectList) {
            map.put(object.getValue(), object);
        }
        this.map = map;
    }

    // Asks for user input and matches it against the dictionary.
    // If and only if it matches a term from the dictionary,
    // it returns an enum matching the string back. 
    // Otherwise, it keeps on asking the user for input.
    public Lexeme<T> matchUserInput() {
        System.out.println("Type:");
        for (Lexeme<T> lexeme : this.map.values()) {
            System.out.println("\"" + lexeme.value + "\" to " + lexeme.description);
        }
        Scanner scan = new Scanner(System.in);
        Lexeme<T> out = null;
        while (scan.hasNextLine()) {
            String userin = scan.nextLine();
            if (this.map.containsKey(userin)) {
                out = this.map.get(userin);
                break;
            } else {
                System.out.print("Invalid. Please type one of the following: [");
                for (Lexeme<T> lexeme : this.map.values()) {
                    System.out.print(String.format("`%s`", lexeme.value));
                }
                System.out.print("]");
                System.out.println();
            }
        }
        return out;
    }
}