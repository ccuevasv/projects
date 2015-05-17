package com.test;

import org.junit.Test;

import com.aritmetic.Codificador;

public class TestCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Test
	public void testCodifier(){
		Codificador arit = new Codificador();
		//arit.codificate("cdba");
		
		
	}
	@Test
	public void testDecodifier(){
		Codificador arit = new Codificador();
		arit.decodificate("11001");
		
		
	}
	
}
