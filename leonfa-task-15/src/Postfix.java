/**
 * The Postfix class implements an evaluator for integer postfix expressions.
 *
 * Postfix notation is a simple way to define and write arithmetic expressions
 * without the need for parentheses or priority rules. For example, the postfix
 * expression "1 2 - 3 4 + *" corresponds to the ordinary infix expression
 * "(1 - 2) * (3 + 4)". The expressions may contain decimal 32-bit integer
 * operands and the four operators +, -, *, and /. Operators and operands must
 * be separated by whitespace.
 *
 * @author  Leon Fällman
 * @version 2022-01-31
 */
public class Postfix extends LinkedList<String>{
    public static class ExpressionException extends Exception {
        public ExpressionException(String message) {
            super(message);
        }
    }

    /**
     * Evaluates the given postfix expression.
     *
     * @param expr  Arithmetic expression in postfix notation
     * @return      The value of the evaluated expression
     * @throws      ExpressionException if the expression is wrong
     */
    public static int evaluate(String expr) throws ExpressionException {
        LinkedList<Integer> stack = new LinkedList<>(); // create a new stack.
        expr = expr.trim(); // get rid of leading and trailing whitespaces.
        String[] characters = expr.split("\\s+");

        if (characters.length == 2 || isOperator(characters[0])) {
            throw new ExpressionException("invalid postfix");
        }
        for (String character : characters) {

            if (isInteger(character)) {
                stack.push(Integer.parseInt(character));
            }

            else if (isOperator(character)) {
                if (stack.size() < 2) {
                    throw new ExpressionException("error");
                }
                int x = stack.pop();
                int y = stack.pop();
                switch (character) {
                    case "+":
                        stack.push(y + x);
                        break;
                    case "-":
                        stack.push(y - x);
                        break;
                    case "*":
                        stack.push(y * x);
                        break;
                    case "/":
                        if (x == 0) {
                            throw new ExpressionException("division by zero");
                        }
                        stack.push(y / x);
                    default:
                        break;
                }
            }
            else {
                throw new ExpressionException("invalid postfix");
            }
        }
        if (stack.size() != 1) {
            throw new ExpressionException("too many operands");
        }
        return stack.top();
    }

    /**
     * Returns true if s is an operator.
     *
     * A word of caution on using the String.matches method: it returns true
     * if and only if the whole given string matches the regex. Therefore
     * using the regex "[0-9]" is equivalent to "^[0-9]$".
     *
     * An operator is one of '+', '-', '*', '/'.
     */
    private static boolean isOperator(String s) {
        return s.matches("[+\\-*\\/]");
    }

    /**
     * Returns true if s is an integer.
     *
     * A word of caution on using the String.matches method: it returns true
     * if and only if the whole given string matches the regex. Therefore
     * using the regex "[0-9]" is equivalent to "^[0-9]$".
     *
     * We accept two types of integers:
     *
     * - the first type consists of an optional '-'
     *   followed by a non-zero digit
     *   followed by zero or more digits,
     *
     * - the second type consists of an optional '-'
     *   followed by a single '0'.
     */
    private static boolean isInteger(String s) {

        boolean nonZero = s.matches("-?[1-9]\\d*");
        boolean zero = s.matches("-?0");

        if (nonZero) {
            return true;
        }
        else if (zero) {
            return true;
        }
        else {
            return false;
        }
    }
}
