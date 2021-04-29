package by.anton.xxssd.exeption;

public class DrugParserException extends Exception{
    public DrugParserException() {
    }

    public DrugParserException(String message) {
        super(message);
    }

    public DrugParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrugParserException(Throwable cause) {
        super(cause);
    }
}
