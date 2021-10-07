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
