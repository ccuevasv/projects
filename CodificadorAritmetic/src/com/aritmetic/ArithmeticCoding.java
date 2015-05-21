package com.aritmetic;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.naming.LimitExceededException;


public class ArithmeticCoding {
	
	public String original_message;
	private Probabilities probs;
	private char[] message;
	private double binary_message;
	private double lower_limit;
	private double upper_limit;
	private double range;
	private double Z;
	public HashMap<Integer, HashMap<String, String>> encode_output;
	public HashMap<Integer, HashMap<String, String>> decode_output;
	
	public ArithmeticCoding() {
	}
	
	public String encode(String message) {
		original_message = message + "#";
		initializeEncode();
		HashMap<Integer, HashMap<String, String>> output = new HashMap<Integer, HashMap<String, String>>();
		for (int i = 0; i < original_message.length(); i++) {
			HashMap<String, String> iteration = new HashMap<String, String>();

			upper_limit = getNewUpperLimit(probs.getY(original_message.charAt(i)+""));
			lower_limit = getNewLowerLimit(probs.getX(original_message.charAt(i)+""));
			Double[] xy = probs.getRangeOf(original_message.charAt(i)+"");
			double [] r = new double[]{lower_limit, upper_limit};
			
			iteration.put("xy", xy[0]+", "+xy[1]);
			iteration.put("symbol", original_message.charAt(i)+"");
			iteration.put("range", String.valueOf(range));
			iteration.put("ranges", r[0]+", "+r[1]);
			output.put(i, iteration);
			
			range = getNewRange();
		}
		encode_output = output;
		return getBinaryIdentity(lower_limit, upper_limit);
	}
	
	public String decode(String msg, Probabilities probabilities){
		HashMap<Integer, HashMap<String, String>> output = new HashMap<Integer, HashMap<String, String>>();
		String message = "";
		String last_char = "";
		int count = 0;
		Double[] xy;
		probs = probabilities;
		initializeDecode();
		Z = reverse(msg);
		
		while (!last_char.equals("#")) {
			HashMap<String, String> iteration = new HashMap<String, String>();
			
			double otherZ = getZ();
			last_char = probs.getSymbol(otherZ);
			xy = probs.getRangeOf(last_char);
			System.out.println(otherZ);
			System.out.println(last_char);
			
			iteration.put("Z", String.valueOf(otherZ));
			iteration.put("xy", xy[0]+", "+xy[1]);
			iteration.put("symbol", last_char);
			iteration.put("range", String.valueOf(range));
			iteration.put("ranges", lower_limit+", "+upper_limit);
			output.put(count, iteration);
			count++;
			message += last_char;
			
			upper_limit = getNewUpperLimit(probs.getY(last_char));
			lower_limit = getNewLowerLimit(probs.getX(last_char));
			
			range = getNewRange();
		}
		decode_output = output;		
		return message;
	}
	
	private double getZ() {
		return ((probs.redondear(Z)-probs.redondear(lower_limit))/probs.redondear(range));
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
//		double range = probs.redondear(upper_limit) - probs.redondear(lower_limit);
//		return probs.redondear(range);
		return (upper_limit - lower_limit);
	}
	
	private double getNewLowerLimit(double x) {
		return (lower_limit + (range * x));
	}
	
	private double getNewUpperLimit(double y) {
		return (lower_limit + (range * y));
	}
	
	public HashMap<Integer, HashMap<String, String>> getEncodeOutput(){
		return encode_output;
	}
	
	public HashMap<Integer, HashMap<String, String>> getDecodeOutput(){
		return decode_output;
	}
	
	public Probabilities getProbs() {
		return probs;
	}
	
	private void initializeEncode() {
		System.out.println(original_message);
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
