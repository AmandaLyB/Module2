package cen3024c;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import java.util.*;
import java.util.Map.Entry;
import java.lang.*;



public class InputHandler {

	public static Object[][] wordList = new Object[20][2];
	
	public static void inputHandler () {
		try {
			
			// Input file is gathered from the GUI class 
			// and uses the directory of the chosen file to write the word count file
			File story = GUI.file;
	        File file = new File(story.getAbsoluteFile().getParent()+"\\"+"text.txt");
	        PrintWriter out = new PrintWriter(new FileOutputStream(file, false));
	        
	        // read text returned by server
	        BufferedReader in = new BufferedReader(new FileReader(story));
	        String line, result;
	        while ((line = in.readLine()) != null) {

	        	result = line.replaceAll("[^A-Za-z ]", " "); // remove all non-word characters excluding space
	        	result = result.replaceAll("&mdash", " ");
	        	result = result.replaceAll("\\s{2,}", " "); 

	        	out.println(result);
	        }
	        in.close();
	        out.close();
	    }
		catch (FileNotFoundException e) {
			e.printStackTrace();		
		}
		catch (IOException e) {
	        System.out.println("I/O Error: " + e.getMessage());
	    }
	}

	// reads and sorts text from file and returns frequency of words
	public static void wordCount() {
		
		File file = new File(GUI.file.getAbsoluteFile().getParent()+"\\"+"text.txt");
		
		try {
			
			File count = new File(GUI.file.getAbsoluteFile().getParent()+"\\" + "count.txt");
			BufferedReader in = new BufferedReader(new FileReader(file));
			PrintWriter out = new PrintWriter(new FileOutputStream(count, false));
			String line;
			HashMap<String,Integer> wordCount = new LinkedHashMap<String,Integer>();
			String[] lineSplit;
			
			// converts text to lower case, splits words by space
			while ((line = in.readLine()) != null) {
				line = line.toLowerCase();
				lineSplit = line.split(" ");

				// creates new key and/or adds to count each instance of a word
				for(int i = 0; i < lineSplit.length; ++i) {
					if(wordCount.containsKey(lineSplit[i])) {
						wordCount.put(lineSplit[i],wordCount.get(lineSplit[i])+1);
					}
					else {
						wordCount.put(lineSplit[i],1);
					}
				}
			}
			
			// remove empty key
			Iterator<Entry<String, Integer>> it = wordCount.entrySet().iterator();
			while (it.hasNext()) {
			    Entry<String, Integer> e = it.next();
			    //String key = e.getKey();
			    Integer value = e.getValue();
			    if (value == 29) {
			        it.remove();
			    }
			}
			
			// sort by most frequent words
			wordCount = sortByValue(wordCount);
			int counts = 0;
			for(String i : wordCount.keySet()) {
				out.println(i + " " + wordCount.get(i));
				System.out.println(i + " " + wordCount.get(i));
				
				// get top 20 words for GUI
				if (counts < 20) {
					wordList[counts][0] = i;
					wordList[counts][1] = wordCount.get(i);
					counts++;
				}
			}

			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();		
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	// sorts HashMap of word-frequency pairs in order of most to least frequent and returns new sorted HashMap
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Collections.reverse(list);
        
        // put data from sorted list to new HashMap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> word : list) {
            temp.put(word.getKey(), word.getValue());
        }
        return temp;
    }
}
