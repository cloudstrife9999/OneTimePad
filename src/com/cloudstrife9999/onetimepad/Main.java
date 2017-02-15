package com.cloudstrife9999.onetimepad;

import java.util.Map;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import com.cloudstrife9999.argparser.ArgParser;

public class Main {

    private Main() {}

    public static void main(String[] args) {
	ArgParser parser = new ArgParser("--ptx", "--ptx-format", "--key", "--key-format");
	Map<String, String> parameters = parser.parse(args);
	
	String plaintext = parameters.get("--ptx");
	Format ptxFormat = Format.fromString(parameters.get("--ptx-format"));
	String keyString = parameters.get("--key");
	Format keyFormat = Format.fromString(parameters.get("--key-format"));

	byte[] ptx = parse(plaintext, ptxFormat);
	byte[] key = parse(keyString, keyFormat);
	
	checkOtpConstraints(ptx, key);
	printInputData(plaintext, ptxFormat, keyString, keyFormat);
	byte[] ctx = encrypt(ptx, key);
	printOutputData(ctx);
    }

    private static byte[] encrypt(byte[] ptx, byte[] key) {
	byte[] ctx = new byte[ptx.length];
	
	for(int i = 0; i < ptx.length; i++) {
	    ctx[i] = (byte)(ptx[i] ^ key[i]);
	}
	
	return ctx;
    }

    private static void printOutputData(byte[] ctx) {
	HexBinaryAdapter adapter = new HexBinaryAdapter();
	
	String ciphertext = new String(ctx);
	String hexCtx = adapter.marshal(ctx);
	
	System.out.println("Ciphertext (might be unreadable): " + ciphertext);
	System.out.println("Ciphertext in hex form: " + hexCtx);
    }

    private static void printInputData(String plaintext, Format ptxFormat, String keyString, Format keyFormat) {
	HexBinaryAdapter adapter = new HexBinaryAdapter();
	
	if(Format.STRING.equals(ptxFormat)) {
	    System.out.println("Plaintext: " + plaintext);
	    System.out.println("Plaintext in hex form: " + adapter.marshal(plaintext.getBytes()));
	}
	
	if(Format.HEX.equals(ptxFormat)) {
	    System.out.println("Plaintext (might be unreadable): " + new String(adapter.unmarshal(plaintext)));
	    System.out.println("Plaintext in hex form: " + plaintext);
	}
	
	if(Format.STRING.equals(keyFormat)) {
	    System.out.println("Key: " + keyString);
	    System.out.println("Key in hex form: " + adapter.marshal(keyString.getBytes()));
	}
	
	if(Format.HEX.equals(keyFormat)) {
	    System.out.println("Key (might be unreadable): " + new String(adapter.unmarshal(keyString)));
	    System.out.println("Key in hex form: " + keyString);
	}
    }

    private static void checkOtpConstraints(byte[] ptx, byte[] key) {
	if(ptx.length != key.length) {
	    throw new IllegalArgumentException("Plaintext and key must be equally long!!!");
	}
    }

    private static byte[] parse(String toParse, Format format) {
	if(Format.STRING.equals(format)) {
	    return toParse.getBytes();
	}
	else if(Format.HEX.equals(format)) {
	    HexBinaryAdapter adapter = new HexBinaryAdapter();
	    
	    return adapter.unmarshal(toParse);
	}
	else {
	    throw new IllegalArgumentException("Bad format: " + format + ".");
	}
    }
}