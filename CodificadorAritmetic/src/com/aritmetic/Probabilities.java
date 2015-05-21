package com.aritmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Probabilities {
	
	private char[] message;
	private int decimals = 10;
	private HashMap<String, Double[]> ranges;
	private String[] uniqueValues;
	private HashMap<String, Double> probabilities;
	
	public Probabilities(String message) {
		this.message = toArray(message);
		initializer();
	}
	
	public Probabilities(char[] message) {
		this.message = message;
		initializer();
	}
	
	public void setMessage(char[] message) {
		this.message = message;
		initializer();
	}
	
	public void setMessage(String message) {
		this.message = toArray(message);
		initializer();
	}
	
	private void initializer() {
		uniqueValues = uniqueValues(message);
		probabilities = getProbabilities();
		ranges = getRanges();
	}
	
	public char[] getArrayMessage() {
		return message;
	}
	
	private char[] toArray(String message) {
		return message.toCharArray();
	}
	
	private String[] uniqueValues(char[] message) {
		List<String> unique = new ArrayList<String>();
		for (int i = 0; i < message.length; i++) {
			if (unique.indexOf(message[i]) < 0) {
				unique.add(message[i]+"");
			}
		}
		System.out.println(unique);
		return unique.toArray(new String[unique.size()]);
	}
	
	public double getProbabilitie(String symbol) {
		return probabilities.get(symbol); // Posible error ya que en teoria debe de entrar un string y estoy pasando como mensaje un char
	}
	
	public Double[] getRangeOf(String symbol){
		return ranges.get(symbol);
	}
	
	public double getX(String symbol) {
		return ranges.get(symbol)[0];
	}
	
	public double getY(String symbol) {
		return ranges.get(symbol)[1];
	}
	
	
	
	public HashMap<String, Double> getProbabilities() {
		HashMap<String, Double> probabilities = new HashMap<String, Double>();
		double size = message.length;
		for (int i = 0; i < uniqueValues.length; i++) {
			double count = 0;
			for (int j = 0; j < message.length; j++) {
				if (uniqueValues[i].equals(message[j]+"")) {
					count++;
				}
			}
			probabilities.put(uniqueValues[i], redondear(count/size));
		}
		return probabilities;
	}
	
	public double redondear( double numero) {
	    return Math.round(numero*Math.pow(10,decimals))/Math.pow(10,decimals);
	  }
	
	public String getSymbol(double z) {
		Iterator<Entry<String, Double[]>> it = ranges.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Double[]> entry = it.next();

			if ((z > entry.getValue()[0]) && ( z <= entry.getValue()[1])) {
				return entry.getKey();
			}
		}
		return "??";
	}
	
	@SuppressWarnings("rawtypes")
	public HashMap<String, Double[]> getRanges() {
		HashMap<String, Double[]> ranges = new HashMap<String, Double[]>();
		HashMap<String, Double> probs = getProbabilities();
		Iterator<Entry<String, Double>> it = probs.entrySet().iterator();
		double last = 0.0;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			ranges.put((String) pair.getKey(), new Double[]{redondear(last), redondear((last+((double) pair.getValue())) ) });
			last += redondear((double) pair.getValue());
			it.remove();
		}
		return ranges;
	}
	
}
