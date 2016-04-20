package ua.goit.gojava.expression;

/**
 * @author Alexandr Korneyko, 2016
 * @version 1.1 20.04.2016
 */
public enum ExpressionElementType {

    BIG_INT, BIG_DECIMAL, BIG_FLOAT, PLUS, MINUS, MULTIPLY, DIVIDE, POWER, OPEN_PARENTHESIS, CLOSE_PARENTHESIS;

    @Override
    public String toString() {
        switch (this) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case MULTIPLY:
                return "*";
            case DIVIDE:
                return "/";
            case POWER:
                return "^";
            case OPEN_PARENTHESIS:
                return "(";
            case CLOSE_PARENTHESIS:
                return ")";
        }

        return null;
    }

    public static ExpressionElementType stringToType(String type) {
        switch (type) {
            case "+":
                return PLUS;
            case "-":
                return MINUS;
            case "*":
                return MULTIPLY;
            case "/":
                return DIVIDE;
            case "^":
                return POWER;
            case "(":
                return OPEN_PARENTHESIS;
            case ")":
                return CLOSE_PARENTHESIS;
        }

        return BIG_INT;
    }
}
