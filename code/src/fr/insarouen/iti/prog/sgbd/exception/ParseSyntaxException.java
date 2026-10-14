package fr.insarouen.iti.prog.sgbd.exception;

/**
 * Exception levée en cas d'erreur de syntaxe SQL lors du parsing.
 * Hérite de RuntimeException pour une gestion optionnelle des erreurs.
 */
public class ParseSyntaxException extends RuntimeException {
    
    public ParseSyntaxException(String message) {
        super(message);
    }
    
    public ParseSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ParseSyntaxException(Throwable cause) {
        super(cause);
    }
}
