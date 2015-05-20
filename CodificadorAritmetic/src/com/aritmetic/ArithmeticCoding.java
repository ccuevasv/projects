package com.aritmetic;

import java.util.HashMap;


public class ArithmeticCoding {
	
	public String original_message;
	private Probabilities probs;
	private char[] message;
	private double binary_message;
	private double lower_limit;
	private double upper_limit;
	private double range;
	public HashMap<String, HashMap<String, Object>> encode_output;
	
	public ArithmeticCoding() {
	}
	
	public String encode(String message) {
		original_message = message;
		initializeEncode();
		HashMap<String, HashMap<String, Object>> output = new HashMap<String, HashMap<String, Object>>();
		for (int i = 0; i < message.length(); i++) {
			HashMap<String, Object> iteration = new HashMap<String, Object>();
			
			
			
			upper_limit = getNewUpperLimit(probs.getY(message.charAt(i)+""));
			lower_limit = getNewLowerLimit(probs.getX(message.charAt(i)+""));
			System.out.println("low "+lower_limit+" up "+upper_limit);
			
			iteration.put("xy", probs.getProbabilitie(message.charAt(i)+""));
			iteration.put("symbol", message.charAt(i));
			iteration.put("range", range);			
			iteration.put("ranges", new double[]{lower_limit, upper_limit});
			output.put(message.charAt(i)+"", iteration);
			
			range = getNewRange();
		}
		encode_output = output;
		return getBinaryIdentity(lower_limit, upper_limit);
	}
	
	private String getBinaryIdentity(Double a, Double b){
		String binaryA = "";
		String binaryB = "";
		System.out.println("a "+a+" b "+b);
		System.out.println("binaryA "+binaryA+" binaryB "+binaryB);
		for(int i=0;i<16;i++){
			a = a>1 ? (a -1)*2 : a*2;
			binaryA = binaryA + a.toString().substring(0,1);
			
			b = b>1 ? (b-1)*2 : b*2;
			binaryB = binaryB + b.toString().substring(0,1);
			System.out.println("binaryA "+binaryA+" binaryB "+binaryB);
			if(!binaryA.equals(binaryB))
				break;
		}
		System.out.println("binaryA "+binaryA+" binaryB "+binaryB);
		return binaryB;
	}
	
	private double getNewRange() {
		return (upper_limit - lower_limit);
	}
	
	private double getNewLowerLimit(double x) {
		return lower_limit + (range * x);
	}
	
	private double getNewUpperLimit(double y) {
		return lower_limit + (range * y);
	}
	
	public HashMap<String, HashMap<String, Object>> getEncodeOutput(){
		return encode_output;
	}
	
	private void initializeEncode() {
		probs = new Probabilities(original_message);
		message = probs.getArrayMessage();
		lower_limit = 0.0;
		upper_limit = 0.0;
		range = 1.0;
	}
	
	

}
