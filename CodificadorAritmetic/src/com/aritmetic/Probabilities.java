package com.aritmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Probabilities {
	
	private String[] message;
	private String[] uniqueValues;
	
	public Probabilities(String message) {
		this.message = toArray(message);
		initializer();
	}
	
	public Probabilities(String[] message) {
		this.message = message;
		initializer();
	}
	
	public void setMessage(String[] message) {
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
	
	private String[] toArray(String message) {
		return message.split("");
	}
	
	private String[] uniqueValues(String[] message) {
		List<String> unique = new ArrayList<String>();
		for (int i = 0; i < message.length; i++) {
			if (unique.indexOf(message[i]) < 0) {
				unique.add(message[i]);
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
				if (uniqueValues[i].equals(message[j])) {
					count++;
				}
			}
			probabilities.put(uniqueValues[i], (count/size));
		}
		return probabilities;
	}
	
}
