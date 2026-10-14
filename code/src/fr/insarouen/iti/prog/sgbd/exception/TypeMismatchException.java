package fr.insarouen.iti.prog.sgbd.exception;


public class TypeMismatchException extends SgbdException {
    
    public TypeMismatchException(String message) {
        super(message);
    }
    
    public TypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
