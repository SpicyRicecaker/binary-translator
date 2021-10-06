import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

// The purpose of this class is to 
public class Input<T> {
    HashMap<String, Lexeme<T>> map;

    public Input(ArrayList<Lexeme<T>> objectList) {
        HashMap<String, Lexeme<T>> map = new HashMap<>();
        for (Lexeme<T> object : objectList) {
            map.put(object.getValue(), object);
        }
        this.map = map;
    }

    public Lexeme<T> matchUserInput() {
        System.out.println("HELP:");
        for (Lexeme<T> lexeme : this.map.values()) {
            System.out.println("\"" + lexeme.value + "\" to " + lexeme.description);
        }
        Scanner in = new Scanner(System.in);
        Lexeme<T> out = null;
        System.out.println("123123123");
        while (in.hasNextLine()) {
            String userin = in.nextLine();
            if (this.map.containsKey(userin)) {
                out = this.map.get(userin);
                System.out.println(out);
                in.close();
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

// if string => string (of enum)