import java.util.Stack;
import java.util.ArrayList;

public class BinaryTranslator {
    public enum Conversion {
        BINARYFIRST, DECIMALFIRST
    }

    public enum InputType {
        FILE, INPUT
    }

    public Conversion getType() {
        ArrayList<Lexeme<Conversion>> conversionType = new ArrayList<>();
        conversionType.add(new Lexeme<>("btd", "convert binary to decimal", Conversion.BINARYFIRST));
        conversionType.add(new Lexeme<>("dtb", "convert decimal to binary", Conversion.DECIMALFIRST));

        return (new Input<Conversion>(conversionType)).matchUserInput().enumeration;
    }

    public String getInput() {
        ArrayList<Lexeme<InputType>> inputTypes = new ArrayList<>();
        inputTypes.add(new Lexeme<>("file", "enter a file", InputType.FILE));
        inputTypes.add(new Lexeme<>("input", "use the console", InputType.INPUT));

        String input = "";
        switch ((new Input<InputType>(inputTypes)).matchUserInput().enumeration) {
            case FILE:
                input = InputWrapper.readFile();
                break;
            case INPUT:
                input = InputWrapper.nextLine();
                break;
        }
        ;
        return input;
    }

    // Given a binary number in a string, converts decimal string
    public Integer BinaryToDecimal(String in) {
        // Since we know binary is just the (number at index) * 2 ^ position...
        // E.g.
        // 111
        // 1 * 2 ^ 0 + 1 * 2 ^ 1 + 1 * 2 ^ 2 = 1 + 2 + 4 = 6
        int out = 0;
        for (int i = 0; i < in.length(); i++) {
            // We only really care about 1s, since 0 * any is 0
            if (in.charAt(i) == '1') {
                out += (int) Math.pow(2, in.length() - 1 - i);
            }
        }
        return out;
    }

    // Given a decimal in a string, converts to binary and outputs string
    public String DecimalToBinary(String in) {
        int dec = (int) Integer.parseInt(in);
        // This will be our stack (Dequeue is the opposite of queue.
        // Think of a queue as a lunchline: whoever goes in first gets
        // the chicken first. A stack however, would take from the last
        // person in line, so the most recent person would get the food
        // first when we call `pop`
        Stack<Integer> stack = new Stack<>();
        // "divide by 2" algorithm
        // we store the remainder of dividing by 2 in a stack,
        // then round down the number
        // e.g. 57 -> binary
        // 57 / 2 R 1
        // 28 / 2 R 0
        // 14 / 2 R 0
        // 7 / 2 R 1
        // 3 / 2 R 1
        // 1 / 2 R 1
        // 0 (ignore)
        // we keep going until we get 0, then we start `pop`in
        // 111001
        // notice how it's not 100111 but 111001, because we take from the most recent
        // remainder
        for (int i = dec; (i != 0);) {
            // so we put remainder (afer dividing by 2) on stack
            stack.push(i % 2);
            // then set number equal to itself over 2, rounded down
            // java scuffed, dividing integers rounds down automatically
            i = i / 2;
        }

        String binary = "";
        while (!stack.isEmpty()) {
            binary += stack.pop();
        }
        return binary;
    }

    public BinaryTranslator() {
        // Get input string using either file or console
        String input = getInput();
        // Get conversion type (either binary to decimal or decimal to binary)
        Conversion conversionType = getType();
        // Get the converted value, in string
        String out = "";
        switch (conversionType) {
            case BINARYFIRST:
                out = Integer.toString(BinaryToDecimal(input));
                break;
            case DECIMALFIRST:
                out = DecimalToBinary(input);
                break;
        }
        System.out.println(String.format("%s of `%s` is `%s`", conversionType.toString(), input, out));
    }

    public static void main(String[] args) {
        new BinaryTranslator();
    }
}