package com.aritmetic;

import java.awt.List;
import java.util.Arrays;
import java.util.HashMap;

public class Codificador {


private Double rango;
private Double limInferior;
private Double limSuperior;
private Double lastX;
private Double lastY;
private Double[][] xy = new Double[3][3];
private char[] dictionary = {'a','b','c'};


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	
	
	public void codificate(String message){
		calculate();
		for(int i=0;i<message.length();i++){
			
			if(i == 0){
				rango = 1.0;
				for(char in : dictionary){
					if( message.charAt(0) == in){
						limInferior = xy[indexOf(in)][0];
						limSuperior = xy[indexOf(in)][1];
						
					}
				}
				System.out.println("rango "+rango+" limInf "+limInferior+" limsup"+limSuperior); 
			}else{
				rango = redondear(limSuperior - limInferior);
				for(char in : dictionary){
					if( message.charAt(i) == in){
						limSuperior = redondear(limInferior + (rango * xy[indexOf(in)][1]));
						limInferior = redondear(limInferior + (rango * xy[indexOf(in)][0]));
						
					}
				}
				
				System.out.println("rango "+rango+" limInf "+limInferior+" limsup"+limSuperior);
			}
			
		}//for message length
		
		generateBinary(limInferior, limSuperior);
		
	}
	
	private void generateBinary(Double a, Double b) {
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
		
		System.out.println("binaryA "+binaryA+" binaryB "+binaryB);
	}

	
	private double redondear(double num) {
		return Math.rint(num*100)/100;
	}
	
	
	/**
	 * Calcula el valor de los rangos a[0],b[1],c[2]
	 */
	public void calculate(){
	
		xy[0][0] = 0.0;
		xy[0][1] = 0.2;
		xy[1][0] = 0.2;
		xy[1][1] = 0.7;
		xy[2][0] = 0.7;
		xy[2][1] = 1.0;
	
	}

	
	/**
	 * De acuerdo al simbolo que ingresa(a,b,c) regresa su posición(0,1,2) para obtener el valor de su rango
	 */
	public int indexOf(char w){
		for(int i=0;i<dictionary.length;i++){
			if(w == dictionary[i])
				return i;
		}
		
		return 0;
	}
	
	public void decodificate(String binary){
		//1010
		calculate();
		Double z = 1.0;
		binary= reverse(binary);
		for(int i=0;i<binary.length();i++){
			
			 z = (binary.charAt(i)=='1' && z<1) ? (z+1)/2 : z/2;
			 
		}
		System.out.println("final "+z);
		
		generateMessage(z);
	}


	private void generateMessage(Double z) {
		Double index = 1.0;
		rango = 1.0;
		limInferior = 0.0;
		limSuperior = 1.0;
		
		while(rango>0.05){
			if(index != 1.0){
				limSuperior = redondear(limInferior + (rango * lastY));
				limInferior = redondear(limInferior + (rango * lastX));
				rango = limSuperior - limInferior;
			}
			
			index = (z - limInferior)/rango;
			identifyIndex(index);
			
		} 
		
	}

	/**
	 * De acuerdo al index identifica a que simbolo del diccionario pertenece
	 */
	private void identifyIndex(Double index) {
		
		if((index >= xy[0][0]) && (index <= xy[0][1])){
			System.out.println("a");
			lastX = xy[0][0];
			lastY = xy[0][1];
			
		}else if((index >= xy[1][0]) && (index <= xy[1][1])){
			System.out.println("b");
			lastX = xy[1][0];
			lastY = xy[1][1];
		}else if((index >= xy[2][0]) && (index <= xy[2][1])){
			System.out.println("c");
			lastX = xy[2][0];
			lastY = xy[2][1];
		}
	}

	
	private String reverse(String binary) {
		String result ="";
		for(int i=1;i<=binary.length();i++){
			result = result+binary.charAt(binary.length()-i);
		}
		System.out.println(result);
		return result;
	}
}
