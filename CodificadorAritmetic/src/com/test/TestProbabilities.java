package com.test;


import java.util.List;

import org.junit.Test;

import com.aritmetic.Probabilities;

public class TestProbabilities {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	
	private void testRange() {
		Probabilities probs = new Probabilities("aaaaaaabbccbabbabs");
		List<Double[]> ranges = probs.getRange();
		for (int i = 0; i < ranges.size(); i++) {
			for (int j = 0; j < ranges.get(i).length; j++) {
				System.out.println("X:"+i+", Y:"+j+" => "+ranges.get(i)[j]);
			}
		}
	}

}
