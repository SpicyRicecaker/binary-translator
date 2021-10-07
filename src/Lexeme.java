// A lexeme literally just ties a string to a enum
// For example, "input" -> ConversionType.INPUT
// 
// Check `Input.java` for more informations on generics (the `<T>`)

public class Lexeme<T> {
    String value;
    String description;
    T enumeration;

    public Lexeme(String value, String description, T enumeration) {
        this.value = value;
        this.description = description;
        this.enumeration = enumeration;
    }
    public String getDescription() {
        return this.description;
    }
    public String getValue() {
        return this.value;
    }
    public T getEnumuration() {
        return this.enumeration;
    }
}
