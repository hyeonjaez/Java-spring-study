package com.nhnacademy.jaehyeon.exercise10_7;

import java.util.HashMap;

/*
 * https://math.hws.edu/javanotes/c10/exercises.html
 */
public class SimpleInterpreter {
    private enum Functions {SIN, COS, TAN, ABS, SQRT, LOG}

    ;

    private static class ParseError extends Exception { // 예외처리
        ParseError(String message) {
            super(message);
        }
    }

    private static class StandardFunction {
        Functions functionCode;

        public StandardFunction(Functions functionCode) {
            this.functionCode = functionCode;
        }

        double evaluate(double x) {
            double result;
            switch (functionCode) {
                case SIN:
                    result = Math.sin(x);
                    break;
                case COS:
                    result = Math.cos(x);
                    break;
                case TAN:
                    result = Math.tan(x);
                    break;
                case ABS:
                    result = Math.abs(x);
                    break;
                case SQRT:
                    result = Math.sqrt(x);
                    break;
                default:
                    result = Math.log(x);
                    break;
            }
            return result;
        }
    }


    private static HashMap<String, Object> symbolTable; // 연산 기호와 그 값


    public static void main(String[] args) {

        symbolTable = new HashMap<>();

        symbolTable.put("pi", Math.PI);
        symbolTable.put("e", Math.E);
        symbolTable.put("sin", new StandardFunction(Functions.SIN));
        symbolTable.put("cos", new StandardFunction(Functions.COS));
        symbolTable.put("tan", new StandardFunction(Functions.TAN));
        symbolTable.put("abs", new StandardFunction(Functions.ABS));
        symbolTable.put("sqrt", new StandardFunction(Functions.SQRT));
        symbolTable.put("log", new StandardFunction(Functions.LOG));

        System.out.println("\n\nEnter commands; press return to end.");
        System.out.println("Commands must have the form:\n");
        System.out.println("      print <expression>");
        System.out.println("  or");
        System.out.println("      let <variable> = <expression>");

        while (true) {
            TextIO.put("\n?  ");
            TextIO.skipBlanks();
            if (TextIO.peek() == '\n') {
                break;  // A blank input line ends the while loop and the program.
            }
            try {
                String command = TextIO.getWord();
                if (command.equalsIgnoreCase("print"))
                    doPrintCommand();
                else if (command.equalsIgnoreCase("let"))
                    doLetCommand();
                else
                    throw new ParseError("Command must begin with 'print' or 'let'.");
                TextIO.getln();
            } catch (ParseError e) {
                System.out.println("\n*** Error in input:    " + e.getMessage());
                System.out.println("*** Discarding input:  " + TextIO.getln());
            }
        }

        System.out.println("\n\nDone.");

    } // end main()


    private static void doLetCommand() throws ParseError {
        TextIO.skipBlanks();
        if (!Character.isLetter(TextIO.peek()))
            throw new ParseError("Expected variable name after 'let'.");
        String varName = readWord();  // The name of the variable.
        TextIO.skipBlanks();
        if (TextIO.peek() != '=')
            throw new ParseError("Expected '=' operator for 'let' command.");
        TextIO.getChar();
        double val = expressionValue();  // The value of the variable.
        TextIO.skipBlanks();
        if (TextIO.peek() != '\n')
            throw new ParseError("Extra data after end of expression.");
        symbolTable.put(varName, val);  // Add to symbol table.
        System.out.println("ok");
    }


    private static void doPrintCommand() throws ParseError {
        double val = expressionValue();
        TextIO.skipBlanks();
        if (TextIO.peek() != '\n')
            throw new ParseError("Extra data after end of expression.");
        System.out.println("Value is " + val);
    }


    private static double expressionValue() throws ParseError {
        TextIO.skipBlanks();
        boolean negative;  // True if there is a leading minus sign.
        negative = false;
        if (TextIO.peek() == '-') {
            TextIO.getAnyChar();
            negative = true;
        }
        double val;  // Value of the expression.
        val = termValue();  // An expression must start with a term.
        if (negative)
            val = -val; // Apply the leading minus sign
        TextIO.skipBlanks();
        while (TextIO.peek() == '+' || TextIO.peek() == '-') {
            char op = TextIO.getAnyChar();
            double nextVal = termValue();
            if (op == '+')
                val += nextVal;
            else
                val -= nextVal;
            TextIO.skipBlanks();
        }
        return val;
    } // end expressionValue()


    private static double termValue() throws ParseError {
        TextIO.skipBlanks();
        double val;  // The value of the term.
        val = factorValue();  // A term must start with a factor.
        TextIO.skipBlanks();
        while (TextIO.peek() == '*' || TextIO.peek() == '/') {
            // Read the next factor, and multiply or divide
            // the value-so-far by the value of this factor.
            char op = TextIO.getAnyChar();
            double nextVal = factorValue();
            if (op == '*')
                val *= nextVal;
            else
                val /= nextVal;
            TextIO.skipBlanks();
        }
        return val;
    } // end termValue()


    private static double factorValue() throws ParseError {
        TextIO.skipBlanks();
        double val;  // Value of the factor.
        val = primaryValue();  // A factor must start with a primary.
        TextIO.skipBlanks();
        if (TextIO.peek() == '^') {
            // Read the next factor, and exponentiate
            // the value by the value of that factor.
            TextIO.getChar();
            double nextVal = factorValue();
            val = Math.pow(val, nextVal);
            if (Double.isNaN(val))
                throw new ParseError("Illegal values for ^ operator.");
            TextIO.skipBlanks();
        }
        return val;
    } // end factorValue()


    private static double primaryValue() throws ParseError {
        TextIO.skipBlanks();
        char ch = TextIO.peek();
        if (Character.isDigit(ch)) {
            // The factor is a number.  Read it and
            // return its value.
            return TextIO.getDouble();
        } else if (Character.isLetter(ch)) {

            String name = readWord();
            Object obj = symbolTable.get(name);
            if (obj == null)
                throw new ParseError("Unknown variable \"" + name + "\"");
            assert (obj instanceof Double || obj instanceof StandardFunction);
            if (obj instanceof Double) {

                Double val = (Double) obj;
                return val.doubleValue();
            } else {
                StandardFunction func = (StandardFunction) obj;
                TextIO.skipBlanks();
                if (TextIO.peek() != '(')
                    throw new ParseError("Parenthesis missing after standard function");
                TextIO.getChar(); // discard the '('
                double argument = expressionValue();  // read and evaluate expression
                TextIO.skipBlanks();
                if (TextIO.peek() != ')')
                    throw new ParseError("Missing right parenthesis.");
                TextIO.getChar(); // discard the ')'
                return func.evaluate(argument);
            }
        } else if (ch == '(') {

            TextIO.getAnyChar();
            double val = expressionValue();
            TextIO.skipBlanks();
            if (TextIO.peek() != ')')
                throw new ParseError("Missing right parenthesis.");
            TextIO.getAnyChar();
            return val;
        } else if (ch == '\n')
            throw new ParseError("End-of-line encountered in the middle of an expression.");
        else if (ch == ')')
            throw new ParseError("Extra right parenthesis.");
        else if (ch == '+' || ch == '-' || ch == '*' || ch == '/')
            throw new ParseError("Misplaced operator.");
        else
            throw new ParseError("Unexpected character \"" + ch + "\" encountered.");
    }


    private static String readWord() {
        String word = "";  // The word.
        char ch = TextIO.peek();
        while (Character.isLetter(ch) || Character.isDigit(ch)) {
            word += TextIO.getChar(); // Add the character to the word.
            ch = TextIO.peek();
        }
        return word;
    }

}