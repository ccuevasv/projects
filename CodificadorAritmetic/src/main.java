import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aritmetic.ArithmeticCoding;
import com.aritmetic.Probabilities;


public class main {
	
	public static void main(String[] args) {
		testProbabilities();
		testRange();
		testEncode();
	}
	
	private static void testEncode() {
		ArithmeticCoding ac = new ArithmeticCoding();
		System.out.println(ac.encode("cdba"));
		System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
//		HashMap<String, HashMap<String, Object>> encode_output = ac.getEncodeOutput();
//		Iterator <Entry<String, HashMap<String, Object>>> it = encode_output.entrySet().iterator();
//		while(it.hasNext()){
//			
//		}
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
