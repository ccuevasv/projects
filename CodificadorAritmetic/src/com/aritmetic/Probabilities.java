package com.aritmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Probabilities {
	
	private char[] message;
	private String[] uniqueValues;
	
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
		return unique.toArray(new String[unique.size()]);
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
			probabilities.put(uniqueValues[i], (count/size));
		}
		return probabilities;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Double[]> getRange() {
		List<Double[]> ranges = new ArrayList<Double[]>();
		HashMap<String, Double> probs = getProbabilities();
		Iterator<Entry<String, Double>> it = probs.entrySet().iterator();
		double last = 0.0;
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			ranges.add(new Double[]{last, (last+((double) pair.getValue()))});
			last = (double) pair.getValue();
			it.remove();
		}
		return ranges;
	}
	
}
