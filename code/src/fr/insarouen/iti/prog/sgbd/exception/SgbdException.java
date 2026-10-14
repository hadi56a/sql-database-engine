package fr.insarouen.iti.prog.sgbd.exception;

public class SgbdException extends Exception {
    
    public SgbdException(String message) {
        super(message);
    }
    
    public SgbdException(String message, Throwable cause) {
        super(message, cause);
    }
}
