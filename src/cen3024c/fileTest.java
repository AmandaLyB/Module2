package cen3024c;

import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.junit.Test;

public class fileTest {

	@Test
	public void test() {
		
		InputHandler test = new InputHandler();
		
		// set GUI.file location to test file for InputHandler.inputHandler
		GUI.file = new File("D:\\Valencia\\CEN 3024C SoftDev 1\\storyTest.txt");
		File testCount = new File("D:\\Valencia\\CEN 3024C SoftDev 1\\testCount.txt");
		InputHandler.inputHandler();
		InputHandler.wordCount();
		File count = new File("D:\\Valencia\\CEN 3024C SoftDev 1\\count.txt");
		
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(testCount));
			String line;
			String[] lineSplit;
			
			// read testCount.txt into a HashMap
			HashMap<String, Integer> testMap = new LinkedHashMap<String, Integer>();
			while ((line = in.readLine()) != null) {
				lineSplit = line.split(" ");
				for(int i = 0; i < lineSplit.length; ++i) {
					if(testMap.containsKey(lineSplit[i])) {
						testMap.put(lineSplit[i],testMap.get(lineSplit[i])+1);
					}
					else {
						testMap.put(lineSplit[i],1);
					}
				}	
			}
			// read count.txt into a HashMap
			in = new BufferedReader(new FileReader(count));
			HashMap<String, Integer> outputMap = new LinkedHashMap<String, Integer>();
			while ((line = in.readLine()) != null) {
				lineSplit = line.split(" ");
				for(int i = 0; i < lineSplit.length; ++i) {
					if(outputMap.containsKey(lineSplit[i])) {
						outputMap.put(lineSplit[i],outputMap.get(lineSplit[i])+1);
					}
					else {
						outputMap.put(lineSplit[i],1);
					}
				}
			}

			// test if each map contains the same information
			assertEquals(testMap,outputMap);		
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
	        System.out.println("I/O Error: " + e.getMessage());
	    }
	}
}
