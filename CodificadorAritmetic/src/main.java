import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aritmetic.Probabilities;


public class main {
	
	public static void main(String[] args) {
		testProbabilities();
		testRange();
	}
	
	private static void testRange() {
		Probabilities probs = new Probabilities("aaabbbbbbcccc");
		List<Double[]> ranges = probs.getRange();
		for (int i = 0; i < ranges.size(); i++) {
			for (int j = 0; j < ranges.get(i).length; j++) {
				System.out.println("X:"+i+", Y:"+j+" => "+ranges.get(i)[j]);
			}
		}
	}
	
	private static void testProbabilities() {
		Probabilities pblt = new Probabilities("aaabbbbbbcccc");
		HashMap<String, Double> probs = pblt.getProbabilities();
		Iterator<Entry<String, Double>> it = probs.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println("KEY: "+pair.getKey()+" Value: "+pair.getValue());
			it.remove();
		}
	}


}
