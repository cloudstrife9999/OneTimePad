package com.cloudstrife9999.onetimepad;

public class MissingArgumentException extends RuntimeException {
    private static final long serialVersionUID = 7616444011723614945L;
    
    public MissingArgumentException() {
	super();
    }
    
    public MissingArgumentException(String message) {
	super(message);
    }
    
    public MissingArgumentException(Throwable t) {
	super(t);
    }
}