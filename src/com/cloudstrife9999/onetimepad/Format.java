package com.cloudstrife9999.onetimepad;

public enum Format {
    STRING, HEX;
    
    public static Format fromString(String name) {
	switch(name.toLowerCase()) {
	case "string":
	    return Format.STRING;
	case "hex":
	    return Format.HEX;
	default:
	    throw new IllegalArgumentException("Bad format: " + name + ".");
	}
    }
    
    @Override
    public String toString() {
        return super.toString().toUpperCase();
    }
}