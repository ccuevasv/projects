package com.aritmetic;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.naming.LimitExceededException;


public class ArithmeticCoding {
	
	public String original_message;
	private Probabilities probs;
	private char[] message;
	private double binary_message;
	private BigDecimal lower_limit;
	private BigDecimal upper_limit;
	private BigDecimal range;
	private BigDecimal Z;
	private int decimals = 10;
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
			BigDecimal [] r = new BigDecimal[]{lower_limit, upper_limit};
			
			iteration.put("xy", xy[0]+", "+xy[1]);
			iteration.put("symbol", original_message.charAt(i)+"");
			iteration.put("range", String.valueOf(range));
			iteration.put("ranges", r[0]+", "+r[1]);
			output.put(i, iteration);
			
			range = getNewRange();
		}
		encode_output = output;
		return getBinaryIdentity(lower_limit.floatValue(), upper_limit.floatValue());
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
			
			BigDecimal otherZ = getZ();
			last_char = probs.getSymbol(otherZ.floatValue());
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
	
	private BigDecimal getZ() {
		return (Z.subtract(lower_limit).setScale(decimals, BigDecimal.ROUND_HALF_UP)).divide(range, decimals, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal reverse(String binary) {
		double result = 0.0;
		for (int i = 0; i < binary.length(); i++) {
			double exp = ((-1) * (i+1));
			result += Integer.parseInt(binary.charAt(i)+"") * Math.pow(2, exp);
		}
		return new BigDecimal(result);
	}
	
	private String getBinaryIdentity(Float f, Float g){
		String binaryA = "";
		String binaryB = "";
		
		for(int i=0;i<16;i++){
			f = f>1 ? (f -1)*2 : f*2;
			binaryA = binaryA + f.toString().substring(0,1);
			
			g = g>1 ? (g-1)*2 : g*2;
			binaryB = binaryB + g.toString().substring(0,1);

			if(!binaryA.equals(binaryB))
				break;
		}
		return binaryB;
	}
	
	private BigDecimal getNewRange() {
//		double range = probs.redondear(upper_limit) - probs.redondear(lower_limit);
//		return probs.redondear(range);
		return (upper_limit.subtract(lower_limit)).setScale(decimals, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal getNewLowerLimit(double x) {
//		return (lower_limit + (range * x));
		return (lower_limit.add((range.multiply(new BigDecimal(x))))).setScale(decimals, BigDecimal.ROUND_HALF_UP);
	}
	
	private BigDecimal getNewUpperLimit(double y) {
		return (lower_limit.add((range.multiply(new BigDecimal(y))))).setScale(decimals, BigDecimal.ROUND_HALF_UP);
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
		lower_limit = new BigDecimal(0.0);
		upper_limit = new BigDecimal(0.0);
		range = new BigDecimal(1.0);
	}
	
	private void initializeDecode() {
		message = probs.getArrayMessage();
		lower_limit = new BigDecimal(0.0);
		upper_limit = new BigDecimal(1.0);
		range = new BigDecimal(1.0);
	}
	
	

}
