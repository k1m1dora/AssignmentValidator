package assignmentvalidator;
import java.util.Scanner;

public class AssignmentValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for an assignment statement
        System.out.println("Enter an Assignment Statement:");
        String input = scanner.nextLine().trim();
        
        // Process the statement
        processStatement(input);
    }

    // Method to process and validate the assignment statement
    public static void processStatement(String input) {
        // Split the input into terms (handling spaces and semicolon separately)
        String[] terms = Lexicalseperator(input);
        
        // Count the lexemes (terms)
        int lexemeCount = lexemecounter(terms);
        System.out.println("Number of terms: " + lexemeCount);
        
        // Print the terms in the required format
        for (String term : terms) {
            System.out.print("'" + term + "' ");
        }
        System.out.println();

        // Syntax checking
        syntaxChecker(terms);
    }

    // Function to split the input into terms (lexical separation)
    public static String[] Lexicalseperator(String input) {
        // Split the input based on spaces and ensure semicolon is a separate term if spaced
        String[] terms = input.split("(?<=\\s)|(?=\\s)|(?<=;)|(?=;)");
        return cleanTerms(terms);
    }

    // Function to clean up terms (remove empty spaces and trim spaces)
    public static String[] cleanTerms(String[] terms) {
        return java.util.Arrays.stream(terms)
                .map(String::trim)
                .filter(term -> !term.isEmpty())
                .toArray(String[]::new);
    }

    // Function to count lexemes (terms)
    public static int lexemecounter(String[] terms) {
        return terms.length;
    }

    // Function to check overall syntax
public static void syntaxChecker(String[] terms) {

    String variablePart = terms[0].trim();  // Variable name
    String equalSign = terms[1].trim();     // Should be '='
    String valuePart = terms[2].trim();     // Value 
    String lastPart = terms[terms.length - 1].trim(); // Semicolon

    // Validate the first part as a variable name
    if (!varChecker(variablePart)) {
        return;  // Error already printed in varChecker
    }

    // Check if the second term is '='
    if (!equalSign.equals("=")) {
        System.out.println("ERROR: Missing or invalid equal sign");
        return;
    }

    // Validate the value part (it should be a valid number, word, or string)
    if (!valueChecker(valuePart)) {
        return;  // Error already printed in valueChecker
    }

    // Ensure that the statement ends with a semicolon
    if (!lastPart.equals(";")) {
        System.out.println("ERROR: Missing or extra semicolon");
        return;
    }

    // Now, check if there's an additional equals sign or extra terms
    if (terms.length > 4) {
        System.out.println("ERROR: Invalid Variable Name");
        return;
    }

    // If everything is fine, the statement is valid
    System.out.println("Valid Statement");
}



    // Function to check if the variable name is valid
    public static boolean varChecker(String term1) {
        if (!term1.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            System.out.println("ERROR: Illegal Variable");
            return false;
        }
        return true;
    }

    // Function to check the validity of the value (handles different types)
    public static boolean valueChecker(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return true;  // Valid string
        } else if (wordChecker(value)) {
            return true;  // Valid word
        } else if (numChecker(value)) {
            return true;  // Valid integer
        } else if (deciChecker(value)) {
            return true;  // Valid floating point number
        } else {
            System.out.println("ERROR: Invalid Expression");
            return false;
        }
    }

    // Function to check if a term is a valid word (alphabetical only)
    public static boolean wordChecker(String word) {
        return word.matches("[a-zA-Z]+");
    }

    // Function to check if a term is a valid integer
    public static boolean numChecker(String num) {
        try {
            Integer.valueOf(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Function to check if a term is a valid floating point number
    public static boolean deciChecker(String decimal) {
        try {
            Float.parseFloat(decimal);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Function to check for equal sign and semicolon
    public static boolean equalsSemiChecker(String termN) {
        switch (termN) {
            case "=":
                return true;
            case ";":
                return true;
            default:
                if (termN.isEmpty()) {
                    System.out.println("ERROR: Missing equal sign");
                } else {
                    System.out.println("ERROR: Missing or extra semicolon");
                }
                return false;
        }
    }
}

