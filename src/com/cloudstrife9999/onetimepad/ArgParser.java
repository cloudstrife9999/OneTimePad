package com.cloudstrife9999.onetimepad;

import java.util.HashMap;
import java.util.Map;

public class ArgParser {
    private String[] requiredParameters;
    
    public ArgParser(String... required) {
	this.requiredParameters = required;
    }
    
    public Map<String, String> parse(String... args) {
	Map<String, String> parameters = new HashMap<>();
	
	for(String required : this.requiredParameters) {
	    if(!lookForArgument(required, args, parameters)) {
		throw new MissingArgumentException("Argument '" + required + "' is required.");
	    }
	}
	
	return parameters;
    }

    private boolean lookForArgument(String required, String[] args, Map<String, String> parameters) {
	for(int i = 0; i < args.length - 1; i++) {
	    if(required.equals(args[i])) {
		return lookForValue(required, args[i + 1], parameters);
	    }
	}
	
	return false;
    }

    private boolean lookForValue(String required, String arg, Map<String, String> parameters) {
	for(String req : this.requiredParameters) {
	    if(req.equals(arg)) {
		return false;
	    }
	}
	
	parameters.put(required, arg);
	
	return true;
    }
}