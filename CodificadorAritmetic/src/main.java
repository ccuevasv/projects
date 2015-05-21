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
	
	public static void main(String[] args) {
		testProbabilities();
		testRange();
		testEncode();
		testDecode();
	}
	
	private static void testEncode() {
		ArithmeticCoding ac = new ArithmeticCoding();
		String msg = "cdba";
		System.out.println(ac.encode(msg));
		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
		HashMap<Integer, HashMap<String, String>> encode_output = ac.getEncodeOutput();
		Iterator <Entry<Integer, HashMap<String, String>>> it = encode_output.entrySet().iterator();
		for (int i = 0; i < msg.length(); i++) {
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
		ac.decode("10001", probs);
	}
	
	private static void testRange() {
		Probabilities probs = new Probabilities("cdba");
		HashMap<String, Double[]> ranges = probs.getRanges();
		Iterator<Entry<String, Double[]>> it = ranges.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Double[]> entry = it.next();
			System.out.println(entry.getKey()+": "+entry.getValue()[0]+", "+entry.getValue()[1]);
		}
	}
	
	private static void testProbabilities() {
		Probabilities pblt = new Probabilities("cdba");
		HashMap<String, Double> probs = pblt.getProbabilities();
		Iterator<Entry<String, Double>> it = probs.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println("KEY: "+pair.getKey()+" Value: "+pair.getValue());
			it.remove();
		}
	}


}
