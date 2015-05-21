import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aritmetic.ArithmeticCoding;
import com.aritmetic.Probabilities;


public class main {
	
	private static Probabilities probs;
	private static String binary_msg;
	private static String original_msg;
	
	public static void main(String[] args) {
		original_msg = "jaghsdsed";
		
		System.out.println("Probabilidades");
		testProbabilities();
		System.out.println("Rangos");
		testRange();
		System.out.println("Codificacion");
		testEncode();
		System.out.println("Decodificacion");
		testDecode();
	}
	
	private static void testEncode() {
		ArithmeticCoding ac = new ArithmeticCoding();
		binary_msg = ac.encode(original_msg);
		System.out.println(binary_msg);
		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
		HashMap<Integer, HashMap<String, String>> encode_output = ac.getEncodeOutput();
		Iterator <Entry<Integer, HashMap<String, String>>> it = encode_output.entrySet().iterator();
		for (int i = 0; i < original_msg.length(); i++) {
			System.out.print(" Simbolo: "+encode_output.get(i).get("symbol"));
			System.out.print(" Rango: "+encode_output.get(i).get("range"));
			System.out.print(" XY: "+encode_output.get(i).get("xy"));
			System.out.print(" Rangos: "+encode_output.get(i).get("ranges"));
			System.out.println();
		}
		probs = ac.getProbs();
	}
	
	private static void testDecode() {
		ArithmeticCoding ac = new ArithmeticCoding();
		System.out.println("Decodificar: "+ac.decode(binary_msg, probs));
		
		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
		HashMap<Integer, HashMap<String, String>> encode_output = ac.getDecodeOutput();
		Iterator <Entry<Integer, HashMap<String, String>>> it = encode_output.entrySet().iterator();
		for (int i = 0; i < encode_output.size(); i++) {
			System.out.print(" Rangos: "+encode_output.get(i).get("ranges"));
			System.out.print(" Rango: "+encode_output.get(i).get("range"));
			System.out.print(" Z: "+encode_output.get(i).get("Z"));
			System.out.print(" Simbolo: "+encode_output.get(i).get("symbol"));
			System.out.print(" XY: "+encode_output.get(i).get("xy"));			
			
			System.out.println();
		}
	}
	
	private static void testRange() {
		Probabilities probs = new Probabilities(original_msg+"#");
		HashMap<String, Double[]> ranges = probs.getRanges();
		Iterator<Entry<String, Double[]>> it = ranges.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Double[]> entry = it.next();
			System.out.println(entry.getKey()+": "+entry.getValue()[0]+", "+entry.getValue()[1]);
		}
	}
	
	private static void testProbabilities() {
		Probabilities pblt = new Probabilities(original_msg+"#");
		HashMap<String, Double> probs = pblt.getProbabilities();
		Iterator<Entry<String, Double>> it = probs.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println("KEY: "+pair.getKey()+" Value: "+pair.getValue());
			it.remove();
		}
	}


}
