package cen3024c;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class WordCountTest {

	@Test
	public void test() {
		InputHandler test = new InputHandler();
		
		// map passed through InputHandler.sortByValue
		HashMap<String, Integer> testMap = new LinkedHashMap<String, Integer>();
		testMap.put("a", 1);
		testMap.put("b", 3);
		testMap.put("c", 2);
		
		// correctly sorted map
		HashMap<String, Integer> correctMap = new LinkedHashMap<String, Integer>();
		correctMap.put("b", 3);
		correctMap.put("c", 2);
		correctMap.put("a", 1);
		
		// pass testMap through sorter and 
		HashMap<String, Integer> output = test.sortByValue(testMap);
				
		// converted maps into list to check sort orders
		List<Map.Entry<String, Integer> > outputList =
	               new LinkedList<Map.Entry<String, Integer> >(output.entrySet());
		
		List<Map.Entry<String, Integer> > correctList =
	               new LinkedList<Map.Entry<String, Integer> >(correctMap.entrySet());
			
		assertEquals(outputList,correctList);
	}
}
