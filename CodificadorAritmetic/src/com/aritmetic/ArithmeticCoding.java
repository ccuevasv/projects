package com.aritmetic;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;


public class ArithmeticCoding {
	
	public String original_message;
	private Probabilities probs;
	private char[] message;
	private double binary_message;
	private double lower_limit;
	private double upper_limit;
	private double range;
	public HashMap<Integer, HashMap<String, String>> encode_output;
	public HashMap<Integer, HashMap<String, String>> dencode_output;
	
	public ArithmeticCoding() {
	}
	
	public String encode(String message) {
		original_message = message;
		initializeEncode();
		HashMap<Integer, HashMap<String, String>> output = new HashMap<Integer, HashMap<String, String>>();
		for (int i = 0; i < message.length(); i++) {
			HashMap<String, String> iteration = new HashMap<String, String>();

			upper_limit = getNewUpperLimit(probs.getY(message.charAt(i)+""));
			lower_limit = getNewLowerLimit(probs.getX(message.charAt(i)+""));
			Double[] xy = probs.getRangeOf(message.charAt(i)+"");
			double [] r = new double[]{lower_limit, upper_limit};
			
			iteration.put("xy", xy[0]+", "+xy[1]);
			iteration.put("symbol", message.charAt(i)+"");
			iteration.put("range", String.valueOf(range));
			iteration.put("ranges", r[0]+", "+r[1]);
			output.put(i, iteration);
			
			range = getNewRange();
		}
		encode_output = output;
		return getBinaryIdentity(lower_limit, upper_limit);
	}
	
	public String decode(String msg, Probabilities probs){
		System.out.println(reverse(msg));
		System.out.println(reverse("1011"));		
		return "";
	}
	
	private double reverse(String binary) {
		double result = 0.0;
		for (int i = 0; i < binary.length(); i++) {
			double exp = ((-1) * (i+1));
			result += Integer.parseInt(binary.charAt(i)+"") * Math.pow(2, exp);
		}
		return result;
	}
	
	private String getBinaryIdentity(Double a, Double b){
		String binaryA = "";
		String binaryB = "";
		
		for(int i=0;i<16;i++){
			a = a>1 ? (a -1)*2 : a*2;
			binaryA = binaryA + a.toString().substring(0,1);
			
			b = b>1 ? (b-1)*2 : b*2;
			binaryB = binaryB + b.toString().substring(0,1);

			if(!binaryA.equals(binaryB))
				break;
		}
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
	
	public HashMap<Integer, HashMap<String, String>> getEncodeOutput(){
		return encode_output;
	}
	
	public Probabilities getProbs() {
		return probs;
	}
	
	private void initializeEncode() {
		probs = new Probabilities(original_message);
		message = probs.getArrayMessage();
		lower_limit = 0.0;
		upper_limit = 0.0;
		range = 1.0;
	}
	
	private void initializeDecode() {
		message = probs.getArrayMessage();
		lower_limit = 0.0;
		upper_limit = 1.0;
		range = 1.0;
	}
	
	

}
