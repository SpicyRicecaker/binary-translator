import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class InputWrapper {

    public InputWrapper() {

    }

    public static String nextLine() {
        String out = "";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            out = scanner.nextLine();
            // make sure there are digits in there
            if (out.length() != 0) {
                if (!out.matches("[^0-9]")) {
                    scanner.close();
                    break;
                }
            } else {
                System.out.println("input is invalid.");
            }
        }
        return out;
    }

    public static String readFile() {
        String userin = "";
        String out = "";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                System.out.println("Please enter your file name");
                userin = scanner.nextLine();
                Scanner fileScan = new Scanner(new File(userin));
                out = fileScan.nextLine();
                fileScan.close();
                scanner.close();
                break;
            } catch (IOException ex) {
                System.out.println("File not found");
                System.exit(1);
            }
        }
        return out;
    }
}
